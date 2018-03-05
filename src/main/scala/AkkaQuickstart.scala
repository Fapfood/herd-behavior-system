import java.util.Calendar

import akka.actor.{ActorRef, ActorSystem}
import poc.actor.Ant


object AkkaQuickstart extends App {

  val system: ActorSystem = ActorSystem("helloAkka")

  val map = new poc.Map

  val ant: ActorRef = system.actorOf(Ant.props(map, 0, 0), "ant-1")

  ant ! List(2, 3)

  println(Calendar.getInstance().getTime.getTime)
}

