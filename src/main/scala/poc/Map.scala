package poc

import java.util.Calendar

import akka.actor.{ActorRef, Props}
import poc.actor.Actor
import poc.message.{Loop, Modifiers, ShareModifiers}
import poc.modifier.Modifier

import scala.collection.mutable.ListBuffer

object Map {

  def props: Props = Props[Map]

}

class Map() extends Actor {

  private val buff: ListBuffer[(ActorRef, Long, List[Modifier])] = scala.collection.mutable.ListBuffer.empty[(ActorRef, Long, List[Modifier])]

  //  def add(actor: ActorRef): Unit = Map.buff += Tuple3(actor, Calendar.getInstance().getTime.getTime, List.empty[Modifier])

  override def receive: Receive = {
    case modifiers: Modifiers =>
      val maybeFound = buff.find(m => m._1 == sender())
      if (maybeFound.isDefined)
        buff -= maybeFound.get
      buff += Tuple3(sender(), Calendar.getInstance().getTime.getTime, modifiers.modifiers)
    case loop: Loop =>
      buff
        .filter(elem => elem._2 + 1000 * 5 < Calendar.getInstance().getTime.getTime)
        .foreach(elem => elem._1 ! new ShareModifiers)
      wait(1000 * 5)
      self ! new Loop
  }
}
