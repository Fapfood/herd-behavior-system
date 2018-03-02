package poc

object Map

class Map() {
  private val buf = scala.collection.mutable.ListBuffer.empty[Ant]

  def addAnt(ant: Ant): Unit = buf += ant

}
