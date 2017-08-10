package com.gitmining.datastore.dao

import org.joda.time.DateTime

object User {
  /**
    * Group repos by their stargazer counts.
    * @param user
    * @return the group id this repo belongs to
    */
  def groupByFollowers(user: User):Int = {
    user.followers/100
  }
}

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

