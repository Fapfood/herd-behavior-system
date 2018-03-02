package poc

import akka.actor.{Actor, Props}
import poc.modifier.Modifier

object Ant {

  def props(map: Map, x: Int, y: Int): Props = Props(new Ant(map, x, y))

}


class Ant(val map: Map, var x: Int, var y: Int) extends Actor {

  private val modifiers = scala.collection.mutable.ListBuffer.empty[Modifier]

  override def receive: Receive = {
    case l: List[Int] => System.out.println(l.head)
  }
}
