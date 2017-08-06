package com.gitmining.datastore.kafka.connect.source.connector

import java.util
import collection.JavaConverters._
import com.gitmining.datastore.database.cassandra.CassandraDatabaseProvider
import com.gitmining.datastore.kafka.connect.source.task.CassandraSourceTask
import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.connect.connector.Task
import org.apache.kafka.connect.source.SourceConnector

object CassandraSourceConnector {

  val TOPIC:String = "topic"
  val TABLE_NAME:String = "table.name"
  val SINCE_ID = "since.id"
  val PAGE_SIZE = "page.size"

}

class CassandraSourceConnector extends SourceConnector with CassandraDatabaseProvider {

  import CassandraSourceConnector._

  private var tableName:String = null
  private var topic:String = null

  override def taskClass(): Class[_ <: Task] = classOf[CassandraSourceTask]

  override def start(props: util.Map[String, String]): Unit = {
    val scalaProps = props.asScala
    tableName = scalaProps(TABLE_NAME)
    topic = scalaProps(TOPIC)
  }

  override def stop(): Unit = {

  }

  override def taskConfigs(maxTasks: Int): util.List[util.Map[String, String]] = {
    List.fill(maxTasks)(
      Map[String,String](
        TOPIC -> topic,
        TABLE_NAME -> TABLE_NAME
      ).asJava
    ).asJava
  }

  override def version(): String = "1"

  override def config(): ConfigDef = ???
}
