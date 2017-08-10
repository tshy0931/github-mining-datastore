package com.gitmining.datastore.kafka.consumer

import java.util.Properties

import akka.actor.{Actor, ActorLogging, Props}
import com.gitmining.datastore.dao.{Repo, User}
import com.gitmining.datastore.database.cassandra.CassandraDatabaseProvider
import com.gitmining.datastore.database.cassandra.tables.Links.Link
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.consumer.{ConsumerRecord, KafkaConsumer}

import scala.collection.JavaConverters._
import scala.concurrent.duration._

object DataConsumer {
  case class Subscribe(topic:String)
  case object Poll
  case object Stop

  def props = Props[DataConsumer]

  lazy val conf = ConfigFactory.load
  lazy val properties = new Properties()
  properties.put("bootstrap.servers", conf.getString("kafka.server"))
  properties.put("group.id", conf.getString("kafka.group-id"))
  properties.put("key.deserializer", conf.getString("kafka.key-deserializer"))
  properties.put("value.deserializer", conf.getString("kafka.value-deserializer"))

  lazy val pollInterval = conf.getInt("poll-interval-second")
}

class DataConsumer extends Actor with ActorLogging with CassandraDatabaseProvider {

  import DataConsumer._
  implicit val ec = context.system.dispatcher

  private lazy val consumer = new KafkaConsumer[Long, String](properties)

  private lazy val scheduler = context.system.scheduler
  private lazy val schedule = scheduler.schedule(Duration.Zero, pollInterval seconds, self, Poll)

  override def receive: Receive = idle

  private def idle: Receive = {
    case Subscribe(topic) =>
      consumer.subscribe(Seq(topic).asJava)
      context.become(running)

    case _ => log.error(s"Unexpected message")
  }

  private def running: Receive = {
    case Poll =>
      val records = consumer.poll(5000).asScala.toSeq
      processRecords(records)

    case Stop =>
      schedule.cancel()
      if(schedule.isCancelled){
        log.info(s"Cancelled schedule $schedule")
      }else{
        log.error(s"Failed to cancelled schedule $schedule")
      }
      context.become(idle)

    case _ => log.error(s"Unexpected message")
  }

  private def processRecords(records: Seq[ConsumerRecord[Long, String]]) = {
    records foreach { record => log.info(s"${record.key()} : ${record.value()}")}
  }
}
