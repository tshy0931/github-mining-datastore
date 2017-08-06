package com.gitmining.datastore.kafka.connect.source.task

import java.util

import com.gitmining.datastore.database.cassandra.CassandraDatabaseProvider

import collection.JavaConverters._
import com.gitmining.datastore.kafka.connect.source.connector.CassandraSourceConnector
import com.outworkers.phantom.dsl.Table
import org.apache.kafka.connect.source.{SourceRecord, SourceTask}

object CassandraSourceTask {

}

class CassandraSourceTask extends SourceTask with CassandraDatabaseProvider {

  import CassandraSourceConnector._
  import CassandraSourceTask._

  private var topic:String = null
  private var tableName:String = null
  private var sinceId:Long = 0L
  private var pageSize:Int = 1000

  override def start(props: util.Map[String, String]): Unit = {
    val scalaProps = props.asScala
    topic = scalaProps(TOPIC)
    tableName = scalaProps(TABLE_NAME)
    sinceId = scalaProps(SINCE_ID).toLong
    pageSize = scalaProps(PAGE_SIZE).toInt
  }

  override def poll(): util.List[SourceRecord] = ???

  override def stop(): Unit = {
    db.shutdown()
  }

  override def version(): String = ???
}
