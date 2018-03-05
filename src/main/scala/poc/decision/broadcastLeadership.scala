package poc.decision

import java.util.Calendar

import poc.modifier.{Leader, Modifier}

import scala.collection.mutable.ListBuffer

trait broadcastLeadership {
  protected val modifiers: ListBuffer[Modifier]

  def broadcastLeadership = {
    val leader = modifiers.collect { case a: Leader => a }.head
    if (leader.lastBroadcastTime + leader.expirationTime < Calendar.getInstance().getTime.getTime) {
      for (actor <- poc.Map.buff) {
        actor match {
          case leader.actorClass => 1
        }
//        if (actor.isInstanceOf[leader.actorClass]) {}
      }
    }
  }

}
