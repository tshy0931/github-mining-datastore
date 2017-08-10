package com.gitmining.datastore.database.cassandra

import com.gitmining.datastore.dao.{Repo, User}
import com.gitmining.datastore.database.cassandra.tables.Links._
import com.gitmining.datastore.database.cassandra.tables._
import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.database.Database
import org.slf4j.LoggerFactory

object Cassandra {

}

class Cassandra(
                 override val connector: CassandraConnection
               ) extends Database[Cassandra](connector) {

  object Users extends Users with Connector
  object UsersByCompany extends UsersByCompany with Connector
  object ReposById extends ReposById with Connector
  object ReposByLanguage extends ReposByLanguage with Connector
  object ReposByOwner extends ReposByOwner with Connector
  object Follows extends Follows with Connector
  object Starred extends Starred with Connector
  object Subscriptions extends Subscriptions with Connector
  object Organizations extends Organizations with Connector
  object Collaborators extends Collaborators with Connector
  object Forks extends Forks with Connector
  object Assignees extends Assignees with Connector
  object Contributors extends Contributors with Connector

  lazy val log = LoggerFactory.getLogger(Cassandra.getClass)

  def saveUsers(users:Seq[User]) = users foreach { user =>
    Users store user
    UsersByCompany store user
  }

  def saveRepos(repos:Seq[Repo]) = repos foreach { repo =>
    ReposById store repo
    ReposByLanguage store repo
    ReposByOwner store repo
  }

  def saveLinks(`type`:String, links:Seq[Link]) = `type` match {
    case "follows" =>
      Follows store links
    case "starred" =>
      Starred store links
    case "subscribes" =>
      Subscriptions store links
    case "orgs" =>
      Organizations store links
    case "collaborates" =>
      Collaborators store links
    case "forks" =>
      Forks store links
    case "assignees" =>
      Assignees store links
    case "contributes" =>
      Contributors store links
  }

}

