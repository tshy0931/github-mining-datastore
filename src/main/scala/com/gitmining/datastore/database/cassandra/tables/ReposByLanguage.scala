package com.gitmining.datastore.database.cassandra.tables

import com.gitmining.datastore.database.cassandra.tables.ReposByLanguage.RepoByLanguage
import com.outworkers.phantom.dsl.{ClusteringOrder, Descending, PartitionKey, Table}

object ReposByLanguage {

  final case class RepoByLanguage(
                                   language:String,
                                   group:Int,
                                   languages:Map[String,Long],
                                   id:Long,
                                   name:String,
                                   owner_id:Long,
                                   owner_name:String,
                                   fork:Boolean,
                                   size:Long,
                                   stargazers_count:Int,
                                   network_count:Int,
                                   forks_count:Int,
                                   open_issues_count:Int,
                                   subscribers_count:Int
                                 )
}

abstract class ReposByLanguage extends Table[ReposByLanguage, RepoByLanguage]{

  override lazy val tableName = "repos_by_language"

  object group extends IntColumn with PartitionKey
  object language extends StringColumn with PartitionKey
  //to split repos of the same language into multiple partitions, in order to avoid huge partition
  object stargazers_count extends IntColumn with ClusteringOrder with Descending
  object subscribers_count extends IntColumn
  object id extends LongColumn
  object name extends StringColumn
  object owner_id extends LongColumn
  object owner_name extends StringColumn
  object languages extends MapColumn[String, Long]
  object fork extends BooleanColumn
  object size extends LongColumn
  object network_count extends IntColumn
  object forks_count extends IntColumn
  object open_issues_count extends IntColumn

}
