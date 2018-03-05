package poc.modifier

import poc.actor.Actor

class Leader(val expirationTime: Long, val actorClass: Class[_ <: Actor]) extends Modifier {
  var lastBroadcastTime: Long = 0
}
