package poc.message

import poc.actor.Actor

class LeaderOccurrence(val leader: Actor, val broadcastTime: Long, val expirationTime: Long) extends Message
