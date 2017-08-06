package com.gitmining.datastore.database.cassandra.connectors

import com.outworkers.phantom.connectors.CassandraConnection

trait Connector {
  val connector:CassandraConnection
}
