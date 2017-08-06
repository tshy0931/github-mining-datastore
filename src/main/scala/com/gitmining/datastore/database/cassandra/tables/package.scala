package com.gitmining.datastore.database.cassandra

import com.outworkers.phantom.dsl.Table
import com.outworkers.phantom.keys.{ClusteringOrder, Descending, PartitionKey}

package object tables {

  case class Link(from:Long, to:Long, weight:Double)

  abstract class Follows extends Table[Follows, Link]{

    override def tableName: String = "follows"

    object from extends LongColumn
    object to extends LongColumn with PartitionKey
    object weight extends DoubleColumn with ClusteringOrder with Descending

  }

  abstract class Starred extends Table[Starred, Link]{

    override def tableName: String = "starred"

    object from extends LongColumn
    object to extends LongColumn with PartitionKey
    object weight extends DoubleColumn with ClusteringOrder with Descending

  }

  abstract class Subscriptions extends Table[Subscriptions, Link]{

    override def tableName: String = "subscribes"

    object from extends LongColumn
    object to extends LongColumn with PartitionKey
    object weight extends DoubleColumn with ClusteringOrder with Descending

  }

  abstract class Organizations extends Table[Organizations, Link]{

    override def tableName: String = "orgs"

    object from extends LongColumn // user
    object to extends LongColumn with PartitionKey// org
    object weight extends DoubleColumn with ClusteringOrder with Descending
  }

  abstract class Collaborators extends Table[Collaborators, Link]{
    // TODO: looks require authentication on github api to get this data
    override def tableName: String = "collaborates"

    object from extends LongColumn // user
    object to extends LongColumn with PartitionKey // repo
    object weight extends DoubleColumn with ClusteringOrder with Descending

  }

  abstract class Forks extends Table[Forks, Link]{

    override def tableName: String = "forks"

    object from extends LongColumn // fork repo
    object to extends LongColumn with PartitionKey// origin repo
    object weight extends DoubleColumn with ClusteringOrder with Descending

  }

  abstract class Assignees extends Table[Assignees, Link]{

    override def tableName: String = "assignees"

    object from extends LongColumn // user
    object to extends LongColumn with PartitionKey // repo
    object weight extends DoubleColumn with ClusteringOrder with Descending

  }

  abstract class Contributors extends Table[Contributors, Link]{

    override def tableName: String = "contributes"

    object from extends LongColumn // user
    object to extends LongColumn with PartitionKey // repo
    object weight extends DoubleColumn with ClusteringOrder with Descending

  }

}
