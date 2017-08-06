package com.gitmining.datastore.database.cassandra.connectors

import com.datastax.driver.core.{HostDistance, PoolingOptions}
import com.outworkers.phantom.connectors.{CassandraConnection, ContactPoint}

object TestConnector extends Connector {
  override val connector:CassandraConnection = ContactPoint.local.withClusterBuilder(
    _.withPoolingOptions(
      new PoolingOptions()
        .setMaxConnectionsPerHost(HostDistance.LOCAL, Int.MaxValue)
        .setCoreConnectionsPerHost(HostDistance.LOCAL, 100)
        .setMaxRequestsPerConnection(HostDistance.LOCAL, 32767)
        .setPoolTimeoutMillis(600000)
    )
  ).keySpace("github-mining")
}
