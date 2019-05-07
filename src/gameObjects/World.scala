package gameObjects

import scala.collection.mutable.ListBuffer
import model.Player
import model.Game

class World {

  var playerList:ListBuffer[Player] = ListBuffer()
  var itemList:ListBuffer[items] = ListBuffer()
  var ammoList: ListBuffer[bullet] = ListBuffer()

  var playerList2:List[Player] = List()
}
