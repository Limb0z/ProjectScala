package tests

import gameFunctions._
import gameObjects.World
import org.scalatest.FunSuite
import scala.collection.mutable.ListBuffer
import model.Player

class TestPickUpItem extends FunSuite {

  def addPlayers(player:Player, world:World):Unit = {
    world.playerList = world.playerList :+ player
  }

  test("pick up items and put it into backpack"){
    var p1:Player = new Player(Map("bandage" -> 0, "food" -> 0), 100.0, "alive", 100, 100)
    var p2:Player = new Player(Map("bandage" -> 1, "food" -> 2), 100.0, "alive", 14, 17)
    var p3:Player = new Player(Map("bandage" -> 6, "food" -> 9), 100.0, "alive", 91, 3)
    var p4:Player = new Player(Map("bandage" -> 5, "food" -> 2), 100.0, "alive", 34, 84)
    var p5:Player = new Player(Map("bandage" -> 3, "food" -> 4), 100.0, "alive", 2, 372)
    var p6:Player = new Player(Map("bandage" -> 7, "food" -> 8), 100.0, "alive", 352, 869)

    var w1:World = new World

    addPlayers(p1, w1)
    addPlayers(p2, w1)
    addPlayers(p3, w1)
    addPlayers(p4, w1)
    addPlayers(p5, w1)

    println(w1)

    assert(w1.contains(p1), true)
  }
}
