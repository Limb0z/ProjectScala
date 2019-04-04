package gameObjects

import scala.collection.mutable.ListBuffer

class World {

  var playerList:ListBuffer[Player] = ListBuffer()
  var itemList:ListBuffer[items] = ListBuffer()
}
