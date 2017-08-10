package com.gitmining.datastore.kafka.consumer

import akka.actor.ActorRef

trait ConsumerProvider {

  def consumer:ActorRef
}
