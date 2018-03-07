package poc.stage.message

import poc.actor.Actor
import poc.message.Message

class ActorRegistration(val actorClass: Class[_ <: Actor]) extends Message
