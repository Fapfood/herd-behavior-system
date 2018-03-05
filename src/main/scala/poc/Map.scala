package poc

import akka.actor.ActorRef

import scala.collection.mutable.ListBuffer

object Map {

  val buff: ListBuffer[ActorRef] = scala.collection.mutable.ListBuffer.empty[ActorRef]

}

class Map() {

  def add(actor: ActorRef): Unit = Map.buff += actor

}
