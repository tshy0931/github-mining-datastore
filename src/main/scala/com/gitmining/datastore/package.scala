package com.gitmining

import com.typesafe.config.ConfigFactory

package object datastore {

  val config = ConfigFactory.load()
  implicit val ec = scala.concurrent.ExecutionContext.global

}
