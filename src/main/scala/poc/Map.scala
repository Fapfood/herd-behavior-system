package poc

import java.util.Calendar

import akka.actor.ActorRef
import poc.modifier.Modifier

import scala.collection.mutable.ListBuffer

object Map {

  val buff: ListBuffer[(ActorRef, Long, List[Modifier])] = scala.collection.mutable.ListBuffer.empty[(ActorRef, Long, List[Modifier])]

}

class Map() {

  def add(actor: ActorRef): Unit = Map.buff += Tuple3(actor, Calendar.getInstance().getTime.getTime, List.empty[Modifier])

}
