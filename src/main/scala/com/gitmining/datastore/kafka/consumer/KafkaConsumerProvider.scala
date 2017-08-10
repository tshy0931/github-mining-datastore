package com.gitmining.datastore.kafka.consumer
import akka.actor.{ActorRef, ActorSystem}

trait KafkaConsumerProvider extends ConsumerProvider {

  lazy val actorSystem = ActorSystem("kafka-consumer")
  override def consumer: ActorRef = actorSystem.actorOf(DataConsumer.props)
}
