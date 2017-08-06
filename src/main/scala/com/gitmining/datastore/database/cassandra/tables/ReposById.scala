package com.gitmining.datastore.database.cassandra.tables

import com.gitmining.datastore.dao.Repo
import com.gitmining.datastore.database.cassandra.tables.ReposById.RepoById
import com.outworkers.phantom.dsl._
import org.joda.time.DateTime

import scala.concurrent.Future

object ReposById {

  import com.gitmining.datastore.dao.Repo._

  case class RepoById(
                       group:Int,
                       id:Long,
                       name:String,
                       owner_id:Long,
                       owner_name:String,
                       fork:Boolean,
                       created_at:DateTime,
                       updated_at:DateTime,
                       pushed_at:DateTime,
                       homepage:Option[String],
                       size:Long,
                       stargazers_count:Int,
                       language:String,
                       network_count:Int,
                       forks_count:Int,
                       open_issues_count:Int,
                       subscribers_count:Int
                     ){
    def of(repo: Repo) = {
      RepoById(
        groupByStars(repo),
        repo.id,
        repo.name,
        repo.owner_id,
        repo.owner_name,
        repo.fork,
        repo.created_at,
        repo.updated_at,
        repo.pushed_at,
        repo.homepage,
        repo.size,
        repo.stargazers_count,
        repo.language,
        repo.network_count,
        repo.forks_count,
        repo.open_issues_count,
        repo.subscribers_count
      )
    }
  }

}

abstract class ReposById extends Table[ReposById, RepoById]{

  override lazy val tableName = "repos"

  object group extends IntColumn with PartitionKey
  object id extends LongColumn with Index
  object name extends StringColumn with Index
  object owner_id extends LongColumn
  object owner_name extends StringColumn
  object fork extends BooleanColumn
  object created_at extends DateTimeColumn
  object updated_at extends DateTimeColumn
  object pushed_at  extends DateTimeColumn
  object homepage extends OptionalStringColumn
  object size extends LongColumn
  object stargazers_count extends IntColumn with ClusteringOrder with Descending
  object language extends StringColumn
  object network_count extends IntColumn
  object forks_count extends IntColumn
  object open_issues_count extends IntColumn
  object subscribers_count extends IntColumn

  def byId(id:Long):Future[Option[RepoById]] = {
    select where(_.id eqs id) one
  }

  def byName(name:String):Future[Option[RepoById]] = {
    select where(_.name eqs name) one
  }

  def byIds(ids:List[Long]):Future[List[RepoById]] = {
    select where(_.id in ids) fetch
  }

  def getReposOfGroup(group:Int):Future[List[RepoById]] = {
    select where(_.group eqs group) fetch
  }

  def getReposOfGroups(groups:List[Int]):Future[List[RepoById]] = {
    select where(_.group in groups) fetch
  }
}

