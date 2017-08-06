package com.gitmining.datastore.database.cassandra.dbs

import com.gitmining.datastore.database.cassandra.Cassandra
import com.gitmining.datastore.database.cassandra.connectors.TestConnector

object TestCassandra extends Cassandra(TestConnector.connector)
