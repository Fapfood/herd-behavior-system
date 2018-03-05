package poc.modifier

import poc.actor.Actor

class Follower(val expirationTime: Long, val actor: Actor) extends Modifier {
  var lastBroadcastTime: Long = 0
}