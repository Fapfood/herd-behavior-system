import akka.actor.{ActorRef, ActorSystem}
import poc.actor.Ant
import poc.message.{DecisionTime, LeaderRising, Modifiers}
import poc.Map
import poc.modifier.Modifier


object AkkaQuickstart extends App {

  val system: ActorSystem = ActorSystem("helloAkka")

  val map: ActorRef = system.actorOf(Map.props, "map")

  val ant: ActorRef = system.actorOf(Ant.props(0, 0), "ant-1")

  map ! new Modifiers(List.empty[Modifier])

  ant ! new LeaderRising(1000 * 2, classOf[Ant])

  ant ! new DecisionTime

}

