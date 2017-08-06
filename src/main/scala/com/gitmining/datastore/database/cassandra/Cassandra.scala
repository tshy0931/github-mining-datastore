package com.gitmining.datastore.database.cassandra

import com.gitmining.datastore.ec
import com.datastax.driver.core.{ConsistencyLevel, HostDistance, PoolingOptions}
import com.gitmining.datastore.dao.{Dao, Repo, User}
import com.gitmining.datastore.database.cassandra.connectors.TestConnector
import com.gitmining.datastore.database.cassandra.tables.{ReposById, Users}
import com.outworkers.phantom.ResultSet
import com.outworkers.phantom.connectors.{CassandraConnection, ContactPoint}
import com.outworkers.phantom.database.Database
import org.slf4j.LoggerFactory

import scala.concurrent.Future

object Cassandra {

}

class Cassandra(
                 override val connector: CassandraConnection
               ) extends Database[Cassandra](connector) {

  object Users extends Users with Connector
  object Repos extends ReposById with Connector

  lazy val log = LoggerFactory.getLogger(Cassandra.getClass)

  def save[T<:Dao](dao:T): Either[String,Future[ResultSet]] = dao match {
    case repo: Repo =>
      Right(Repos.store(repo).consistencyLevel_=(ConsistencyLevel.QUORUM).future())
    case user: User =>
      Right(Users.store(user).consistencyLevel_=(ConsistencyLevel.QUORUM).future())
    case _ =>
      log.error(s"Unsupported DAO type")
      Left(s"Unsupported DAO type")
  }
}

