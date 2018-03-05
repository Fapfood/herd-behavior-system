package poc.decision

import java.util.Calendar

import poc.actor.Actor
import poc.message.LeaderOccurrence
import poc.modifier.Leader

trait broadcastLeadership extends Actor {

  def broadcastLeadership: Boolean = {
    val leader = modifiers.collect { case a: Leader => a }.head
    if (leader.lastBroadcastTime + leader.expirationTime < Calendar.getInstance().getTime.getTime) {
      for (actor <- poc.Map.buff) {
        print(actor.getClass, leader.actorClass)
        actor.getClass match {
          case leader.actorClass =>
            //            print("ala")
            actor ! new LeaderOccurrence(this, leader.lastBroadcastTime, leader.expirationTime)
          case _ => ""
        }
      }
      true
    } else {
      false
    }
  }

}
