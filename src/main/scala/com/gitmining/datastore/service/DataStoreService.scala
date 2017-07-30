package com.gitmining.datastore.service

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import com.gitmining.datastore.service.routes.{DataStoreServiceRoutes, Routes}

import scala.concurrent.ExecutionContextExecutor

trait DataStoreService { this:Routes =>

  implicit val system: ActorSystem
  implicit val materializer: Materializer
  // needed for the future flatMap/onComplete in the end
  implicit val executor: ExecutionContextExecutor


}
