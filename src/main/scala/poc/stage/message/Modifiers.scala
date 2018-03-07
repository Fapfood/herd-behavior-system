package poc.stage.message

import akka.actor.ActorRef
import poc.actor.Actor
import poc.message.Message
import poc.modifier.Modifier

class Modifiers(val list: List[(ActorRef, Long, Class[_ <: Actor], List[Modifier])]) extends Message
