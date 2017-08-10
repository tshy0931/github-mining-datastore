package com.gitmining.datastore.service.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, completeWith, delete, entity, get, instanceOf, path, pathPrefix, post}
import com.gitmining.datastore.dao.{JsonFormats, Repo, User}
import akka.http.scaladsl.server.Directives._
import com.gitmining.datastore.database.cassandra.CassandraDatabaseProvider
import com.gitmining.datastore.kafka.consumer.DataConsumer.Subscribe
import com.gitmining.datastore.kafka.consumer.KafkaConsumerProvider
import com.typesafe.config.ConfigFactory
import scala.collection.JavaConverters._

trait DataStoreServiceRoutes extends Routes with JsonFormats with KafkaConsumerProvider with CassandraDatabaseProvider {

  private val conf = ConfigFactory.load
  private val topics = Map(
    "users" -> "users",
    "repos" -> "repos",
    "follows" -> "follows",
    "starred" -> "starred",
    "subscribes" -> "subscribes",
    "orgs" -> "orgs",
    "collaborates" -> "collaborates",
    "forks" -> "forks",
    "assignees" -> "assignees",
    "contributes" -> "contributes"
  )

  override def routes =
    path("topics") {
      get {
        val topics = conf.getStringList("kafka.topics")
        complete(s"Available topics: \n${topics.asScala mkString "\n"}")
      }
    } ~
    path("topics" / topics) { topic =>
      post {
        consumer ! Subscribe(topic)
        complete(s"Started consumer on topic $topic")
      }
    } ~
    pathPrefix("users") {
      (get & path(LongNumber)) { userId =>
        complete("TODO")
      } ~
      (post & entity(as[List[User]])) { users =>
        //TODO: save users
//        database.save(users.head)
        complete {
          """Users saved"""
        }
      } ~
      (delete & path(LongNumber)) { userId =>
        complete {
          //TODO: delete user with given id

          "Delete user not implemented yet"
        }
      }
    } ~
    pathPrefix("repos") {
      (get & path(LongNumber)) { repoId =>
        complete("TODO")
      } ~
      (post & entity(as[List[Repo]])) { repos =>
        //TODO: save repos
        complete {
          """Repos saved"""
        }
      }
    }
}
