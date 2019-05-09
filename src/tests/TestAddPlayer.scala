package tests

import org.scalatest.FunSuite
import model.Game
import gameObjects._
import model.Player

import scala.collection.mutable.ListBuffer

class TestAddPlayer extends FunSuite {

  def addPlayers(player:Player, world:World):Unit = {
    world.playerList = world.playerList :+ player
  }

  test("add player") {
    var p1:Player = new Player(100.0, "alive", 100, 100, "oof3")
    var p2:Player = new Player(100.0, "alive", 14, 17, "oof3")
    var p3:Player = new Player(100.0, "alive", 91, 3, "oof3")
    var p4:Player = new Player(100.0, "alive", 34, 84, "oof3")
    var p5:Player = new Player(100.0, "alive", 2, 372, "oof3")
    var p6:Player = new Player(100.0, "alive", 352, 869, "oof3")

    val l1:ListBuffer[Player] = new ListBuffer[Player]
    val w1:World = new World
    w1.playerList = l1

    addPlayers(p1, w1)
    addPlayers(p2, w1)
    addPlayers(p3, w1)
    addPlayers(p4, w1)
    addPlayers(p5, w1)

    assert(w1.playerList.contains(p1), true)
    assert(w1.playerList.contains(p2), true)
    assert(w1.playerList.contains(p3), true)
    assert(w1.playerList.contains(p4), true)
    assert(w1.playerList.contains(p5), true)

  }
}
