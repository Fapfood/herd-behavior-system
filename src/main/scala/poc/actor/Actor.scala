package poc.actor

import poc.modifier.Modifier

import scala.collection.mutable.ListBuffer

abstract class Actor extends akka.actor.Actor {
  protected val modifiers: ListBuffer[Modifier] = scala.collection.mutable.ListBuffer.empty[Modifier]
}
