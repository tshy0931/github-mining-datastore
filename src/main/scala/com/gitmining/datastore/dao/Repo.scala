package com.gitmining.datastore.dao

import org.joda.time.DateTime

final case class Repo(
                       id:Long,
                       name:String,
                       full_name:String,
                       fork:Boolean,
                       created_at:DateTime,
                       updated_at:DateTime,
                       pushed_at:DateTime,
                       homepage:Option[String],
                       size:Long,
                       stargazers_count:Int,
                       language:Option[String],
                       network_count:Int,
                       forks_count:Int,
                       open_issues_count:Int,
                       subscribers_count:Int
                     ) extends Dao
{

}
