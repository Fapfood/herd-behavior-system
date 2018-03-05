import akka.actor.{ActorRef, ActorSystem}
import poc.actor.Ant
import poc.message.{DecisionTime, LeaderRising}


object AkkaQuickstart extends App {

  val system: ActorSystem = ActorSystem("helloAkka")

  val map = new poc.Map

  val ant: ActorRef = system.actorOf(Ant.props(map, 0, 0), "ant-1")
  map.add(ant)


  ant ! new LeaderRising(1000 * 2, classOf[Ant])

  ant ! new DecisionTime

}

