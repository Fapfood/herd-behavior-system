package poc.stage.actor

import java.util.Calendar

import akka.actor.{ActorRef, Props}
import poc.actor.Actor
import poc.modifier.Modifier
import poc.stage.message.{ActorRegistration, Modifiers, ModifiersChange, ShareModifiers}

import scala.collection.mutable.ListBuffer

object Map {

  def props: Props = Props[Map]

}

class Map() extends Actor {

  private val buff: ListBuffer[(ActorRef, Long, Class[_ <: Actor], List[Modifier])] = scala.collection.mutable.ListBuffer.empty[(ActorRef, Long, Class[_ <: Actor], List[Modifier])]

  override def receive: Receive = {
    case actorRegistration: ActorRegistration =>
      buff += Tuple4(sender(), Calendar.getInstance().getTime.getTime, actorRegistration.actorClass, List.empty[Modifier])
    case modifiersChange: ModifiersChange =>
      val maybeFound = buff.find(m => m._1 == sender())
      if (maybeFound.isDefined) {
        buff -= maybeFound.get
        buff += Tuple4(sender(), Calendar.getInstance().getTime.getTime, maybeFound.get._3, modifiersChange.modifiers)
      }
    case shareModifiers: ShareModifiers =>
      sender() ! new Modifiers(buff.filter(elem => shareModifiers.actorClass.isAssignableFrom(elem._3)).toList)
  }
}
