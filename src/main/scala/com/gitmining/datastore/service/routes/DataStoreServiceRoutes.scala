package com.gitmining.datastore.service.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives.{as, complete, completeWith, delete, entity, get, instanceOf, path, pathPrefix, post}
import com.gitmining.datastore.dao.{JsonFormats, Repo, User}
import akka.http.scaladsl.server.Directives._
import com.gitmining.datastore.database.cassandra.CassandraDatabaseProvider

trait DataStoreServiceRoutes extends Routes with JsonFormats with CassandraDatabaseProvider {
  override val routes =
    pathPrefix("users") {
      (get & path(LongNumber)) { userId =>
        complete(db.Users.byId(userId))
      } ~
      (post & entity(as[List[User]])) { users =>
        //TODO: save users
        database.save(users.head)
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
        complete(db.Repos.byId(repoId))
      } ~
      (post & entity(as[List[Repo]])) { repos =>
        //TODO: save repos
        complete {
          """Repos saved"""
        }
      }
    }
}
