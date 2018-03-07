package poc

import akka.actor.{ActorRef, ActorSystem}
import poc.stage.actor.Map

object Config {

  val system: ActorSystem = ActorSystem("helloAkka")

  val map: ActorRef = system.actorOf(Map.props, "map")

}
