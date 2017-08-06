package com.gitmining.datastore.dao

import org.joda.time.DateTime

object Repo {
  /**
    * Group repos by their stargazer counts.
    * @param repo
    * @return the group id this repo belongs to
    */
  def groupByStars(repo: Repo):Int = {
    repo.stargazers_count/100
  }
}

final case class Repo(
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
                     ) extends Dao



