package poc.actor

import java.util.Calendar

import akka.actor.Props
import poc.Map
import poc.modifier.{Leader, Modifier}

import scala.collection.mutable.ListBuffer

object Ant {

  def props(map: Map, x: Int, y: Int): Props = Props(new Ant(map, x, y))

}


class Ant(val map: Map, var x: Int, var y: Int) extends Actor {

  protected val modifiers: ListBuffer[Modifier] = scala.collection.mutable.ListBuffer.empty[Modifier]

  override def receive: Receive = {
    case l: List[Int] => System.out.println(l.head)
  }

}
