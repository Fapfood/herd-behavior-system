package poc.message

import poc.actor.Actor

class LeaderRising(val expirationTime: Long, val actorClass: Class[_ <: Actor]) extends Message
