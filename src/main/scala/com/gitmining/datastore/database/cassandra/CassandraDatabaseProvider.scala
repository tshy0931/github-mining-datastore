package com.gitmining.datastore.database.cassandra

import com.outworkers.phantom.database.DatabaseProvider

trait CassandraDatabaseProvider extends DatabaseProvider[Cassandra]{

  override def database: Cassandra = Cassandra
}
