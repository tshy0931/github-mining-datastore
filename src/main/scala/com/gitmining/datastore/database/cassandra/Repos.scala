package com.gitmining.datastore.database.cassandra

import com.gitmining.datastore.dao.Repo
import com.outworkers.phantom.dsl._
import scala.concurrent.Future

abstract class Repos extends Table[Repos, Repo]{

  override lazy val tableName = "repos"

  object id extends LongColumn with PartitionKey
  object name extends StringColumn with Index
  object owner_id extends LongColumn with Index
  object owner_name extends StringColumn
  object full_name extends StringColumn
  object fork extends BooleanColumn
  object created_at extends DateTimeColumn
  object updated_at extends DateTimeColumn
  object pushed_at  extends OptionalDateTimeColumn
  object homepage extends OptionalStringColumn
  object size extends LongColumn
  object stargazers_count extends IntColumn
  object language extends OptionalStringColumn
  object network_count extends IntColumn
  object forks_count extends IntColumn
  object open_issues_count extends IntColumn
  object subscribers_count extends IntColumn

  def byId(id:Long):Future[Option[Repo]] = {
    select where(_.id eqs id) one
  }

  def byIds(ids:List[Long]):Future[Option[List[Repo]]] = {
    val resultSet = select.where(_.id in ids).future()
    resultSet.mapTo[Option[List[Repo]]]
  }
}

