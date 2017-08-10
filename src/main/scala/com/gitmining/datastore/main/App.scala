package com.gitmining.datastore.main

import com.gitmining.datastore.config
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.gitmining.datastore.database.cassandra.{Cassandra, CassandraDatabaseProvider}
import com.gitmining.datastore.kafka.consumer.DataConsumer
import com.gitmining.datastore.service.DataStoreService
import com.gitmining.datastore.service.routes.DataStoreServiceRoutes

object App extends App with DataStoreService with DataStoreServiceRoutes {

  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  Http().bindAndHandle(routes, config.getString("http-server.host"),config.getInt("http-server.port"))
}
