package poc.actor

import java.util.Calendar

import akka.actor.Props
import poc.decision.broadcastLeadership
import poc.message.{DecisionTime, LeaderOccurrence, LeaderRising}
import poc.modifier.{Follower, Leader}
import poc.stage.actor.Map
import poc.stage.message.ShareModifiers

object Ant {

  def props(x: Int, y: Int): Props = Props(new Ant(x, y))

}

class Ant(var x: Int, var y: Int) extends Actor with broadcastLeadership {

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
  }

  def decide: Unit = {
    this.broadcastLeadership
    self ! new DecisionTime
  }

}
