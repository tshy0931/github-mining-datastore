package com.gitmining.datastore.database.cassandra.tables

import com.gitmining.datastore.dao.User
import com.outworkers.phantom.dsl.{ClusteringOrder, Descending, PartitionKey, Table}
import scala.concurrent.Future
import com.outworkers.phantom.dsl.context

object UsersByCompany {

}

abstract class UsersByCompany extends Table[UsersByCompany, User]{

  override lazy val tableName = "users_by_company"

  object company extends StringColumn with PartitionKey
  object `type` extends StringColumn
  object id extends LongColumn
  object login extends StringColumn
  object location extends OptionalStringColumn
  object hireable extends OptionalBooleanColumn
  object created_at extends DateTimeColumn
  object updated_at extends DateTimeColumn
  object followers extends IntColumn with ClusteringOrder with Descending
  object following extends IntColumn
  object public_repos extends IntColumn
  object public_gists extends IntColumn
  object score extends DoubleColumn with ClusteringOrder with Descending

  def getUsersOfCompany(company:String):Future[List[User]] = {
    select where(_.company eqs company) fetch
  }

  def getUsersOfCompanies(companies:List[String]):Future[List[User]] = {
    select where(_.company in companies) fetch
  }

}
