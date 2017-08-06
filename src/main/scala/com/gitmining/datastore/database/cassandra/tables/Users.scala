package com.gitmining.datastore.database.cassandra.tables

import com.gitmining.datastore.dao.User
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

abstract class Users extends Table[Users, User]{

  override lazy val tableName = "users"

  object group extends IntColumn with PartitionKey
  object `type` extends StringColumn
  object id extends LongColumn with Index
  object login extends StringColumn with Index
  object name extends OptionalStringColumn
  object company extends OptionalStringColumn
  object location extends OptionalStringColumn
  object hireable extends OptionalBooleanColumn
  object created_at extends DateTimeColumn
  object updated_at extends DateTimeColumn
  object followers extends IntColumn with ClusteringOrder with Descending
  object following extends IntColumn
  object public_repos extends IntColumn
  object public_gists extends IntColumn

  def byId(id:Long):Future[Option[User]] = {
    select where(_.id eqs id) one
  }

  def byIds(ids:List[Long]):Future[Option[List[User]]] = {
    val resultSet = select.where(_.id in ids).future()
    resultSet.mapTo[Option[List[User]]]
  }


}
