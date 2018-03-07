package poc.stage.message

import poc.actor.Actor
import poc.message.Message

class ShareModifiers(val actorClass: Class[_ <: Actor]) extends Message
