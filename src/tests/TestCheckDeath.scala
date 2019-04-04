package tests

import gameFunctions.functions
import gameObjects._
import org.scalatest.FunSuite
import scala.collection.mutable.ListBuffer
import model.Player

class TestCheckDeath extends FunSuite {

  def checkDeath(list:World): Unit = {
    for (currentPlayer <- list.playerList){
      if(currentPlayer.condition == "dead"){
        list.playerList -= currentPlayer
      }
    }
  }

  test("remove player from current list of players"){

    var p1:Player = new Player(100.0,"alive",100,100)
    var p2:Player = new Player(100.0,"dead",14,17)
    var p3:Player = new Player(100.0,"dead",91,3)
    var p4:Player = new Player(100.0,"alive",34,84)

    var w1:World = new World
    w1.playerList = ListBuffer(p1,p2,p3,p4)

    checkDeath(w1)

    assert(w1.playerList.contains(p1), true)
    assert(w1.playerList.contains(p2), false)
    assert(w1.playerList.contains(p3), false)
    assert(w1.playerList.contains(p4), true)

  }
}
