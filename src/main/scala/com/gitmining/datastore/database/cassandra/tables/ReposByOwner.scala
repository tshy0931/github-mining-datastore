package com.gitmining.datastore.database.cassandra.tables

import com.gitmining.datastore.dao.Repo
import com.outworkers.phantom.dsl.{ClusteringOrder, Descending, Index, PartitionKey, Table}
import org.joda.time.DateTime

import scala.concurrent.Future

object ReposByOwner {
  case class RepoByOwner(
                          id:Long,
                          owner_id:Long,
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
                        )
}

abstract class ReposByOwner extends Table[ReposByOwner, Repo]{

  override lazy val tableName = "repos_by_owner"

  object id extends LongColumn
  object owner_id extends LongColumn with PartitionKey
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

  def getReposOfOwner(id:Long):Future[List[Repo]] = {
    select.where(_.owner_id eqs id).fetch()
  }

  def getReposOfOwners(ids:List[Long]):Future[List[Repo]] = {
    select.where(_.owner_id in ids).fetch()
  }

}
