package com.gitmining.datastore.database.cassandra

import com.gitmining.datastore.database.cassandra.dbs.TestCassandra
import com.outworkers.phantom.database.DatabaseProvider

trait CassandraDatabaseProvider extends DatabaseProvider[Cassandra]{

  override def database: Cassandra = TestCassandra
}
