package gameObjects

import scala.collection.mutable.ListBuffer
import model.Player

class World {

  var playerList:ListBuffer[Player] = ListBuffer()
  var itemList:ListBuffer[items] = ListBuffer()
}
