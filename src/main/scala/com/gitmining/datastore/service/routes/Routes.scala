package com.gitmining.datastore.service.routes

import akka.http.scaladsl.server.Route

trait Routes {
  def routes:Route
}
