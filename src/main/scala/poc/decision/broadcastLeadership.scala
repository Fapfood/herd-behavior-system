package poc.decision

import java.util.Calendar

import akka.actor.ActorRef
import akka.pattern.ask
import poc.Config
import poc.actor.Actor
import poc.message.{LeaderOccurrence, Loop}
import poc.modifier.{Leader, Modifier}

import scala.concurrent.Await
import scala.concurrent.duration._

trait broadcastLeadership extends Actor {

  def broadcastLeadership: Boolean = {
    val leader = modifiers.collect { case a: Leader => a }.head
    if (leader.lastBroadcastTime + leader.expirationTime < Calendar.getInstance().getTime.getTime) {
      val future = Config.map ? new Loop
      val result = Await.result(future, 5 seconds).asInstanceOf[List[(ActorRef, Long, Class[_ <: Actor], List[Modifier])]]
      for (actor <- result) {
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
