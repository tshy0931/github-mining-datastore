package com.gitmining.datastore.dao

import org.joda.time.DateTime

final case class User(
                       id:Long,
                       login:String,
                       name:Option[String],
                       `type`:String,
                       company:Option[String],
                       location:Option[String],
                       hireable:Option[Boolean],
                       created_at:DateTime,
                       updated_at:DateTime,
                       followers:Int,
                       following:Int,
                       public_repos:Int,
                       public_gists:Int
                     ) extends Dao
{

}
