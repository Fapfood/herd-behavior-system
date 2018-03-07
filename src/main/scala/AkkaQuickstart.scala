import akka.actor.ActorRef
import poc.Config
import poc.actor.Ant
import poc.message.{DecisionTime, LeaderRising}
import poc.modifier.Modifier
import poc.stage.message.ModifiersChange


object AkkaQuickstart extends App {

  val system = Config.system

  val ant: ActorRef = system.actorOf(Ant.props(0, 0), "ant-1")

  map ! new ModifiersChange(List.empty[Modifier])

  ant ! new LeaderRising(1000 * 2, classOf[Ant])

  ant ! new DecisionTime

}

