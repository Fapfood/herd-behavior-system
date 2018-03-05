package poc.actor

import java.util.Calendar

import akka.actor.Props
import poc.Map
import poc.decision.broadcastLeadership
import poc.message.{DecisionTime, LeaderOccurrence, LeaderRising, ShareModifiers}
import poc.modifier.{Follower, Leader}

object Ant {

  def props(map: Map, x: Int, y: Int): Props = Props(new Ant(map, x, y))

}

class Ant(val map: Map, var x: Int, var y: Int) extends Actor with broadcastLeadership {

  override def receive: Receive = {
    case leaderRising: LeaderRising =>
      val leader = new Leader(leaderRising.expirationTime, leaderRising.actorClass)
      leader.lastBroadcastTime = Calendar.getInstance().getTime.getTime
      modifiers += leader
      print("leaderRising")
    case leaderOccurrence: LeaderOccurrence =>
      val follower = new Follower(leaderOccurrence.expirationTime, leaderOccurrence.leader)
      follower.lastBroadcastTime = Calendar.getInstance().getTime.getTime
      modifiers += follower
      print("leaderOccurrence")
    case decisionTime: DecisionTime =>
      this.decide
    //      print("decisionTime")
    case shareModifiers: ShareModifiers =>
      sender() !
  }

  def decide: Unit = {
    this.broadcastLeadership
    self ! new DecisionTime
  }

}
