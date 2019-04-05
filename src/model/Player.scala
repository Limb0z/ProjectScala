package model

import model.playerstates.PlayerState
import model.playerstates.groundstates.Walking
import gameObjects._

class Player(var hunger:Double,
             var condition:String,
             var locationX:Double,
             var locationY:Double)
  extends living(health = 100.0) {

  //var backpack:Map[String, Int] = Map("bandage" -> 0, "food" -> 0, "ammo" -> 0)
  var backpack = scala.collection.mutable.Map("bandage" -> 0, "food" -> 0 ,"ammo" -> 0)

  def useBandage(player: Player): Unit = {
    player.health += 10.0
    if (player.health > 100.0){
      player.health = 100.0
    }
    player.backpack("bandage") = player.backpack("bandage") - 1
  }

  def useFood(player: Player): Unit = {
    player.health += 10
    if (player.hunger > 100){
      player.hunger = 100
    }
    player.backpack("food") = player.backpack("food") - 1
  }

  def checkDeath(player:Player): Unit = {
    if (player.health <= 0 || player.hunger <= 0){
      player.health = 0
      player.hunger = 0
    }
  }

  var state:PlayerState = new Walking(this)

  var leftKeyHeld = false
  var rightKeyHeld = false
  var upKeyHeld = false
  var downKeyHeld = false

  def leftPressed(): Unit = {
    this.leftKeyHeld = true
    this.state.leftPressed()
  }

  def rightPressed(): Unit = {
    this.rightKeyHeld = true
    this.state.rightPressed()
  }

  def upPressed(): Unit = {
    this.upKeyHeld = true
    this.state.upPressed()
  }

  def downPressed(): Unit = {
    this.downKeyHeld = true
    this.state.downPressed()
  }

  def leftReleased(): Unit = {
    this.leftKeyHeld = false
    this.state.leftReleased()
  }

  def rightReleased(): Unit = {
    this.rightKeyHeld = false
    this.state.rightReleased()
  }

  def upReleased(): Unit = {
    this.upKeyHeld = false
    this.state.upReleased()
  }

  def downReleased(): Unit = {
    this.downKeyHeld = false
    this.state.downReleased()
  }

  def isAlive: Boolean = {
    this.state.isAlive
  }

  //WALKING
  def walkLeft(): Unit = {
    this.locationX -= 7
  }

  def walkRight(): Unit = {
    this.locationX += 7
  }

  def walkUp(): Unit = {
    this.locationY += 7
  }

  def walkDown(): Unit = {
    this.locationY -= 7
  }


  def keepWalkLeft(): Unit = {
    this.doWalkLeft = true
    val t = new java.util.Timer()
    val thissy = this
    val task = new java.util.TimerTask {
      def run() = {
        if (thissy.doWalkLeft) {
          walkLeft()
        }
        else {
          this.cancel()
        }
      }
    }
    t.schedule(task, 0, 5)
  }

  def keepWalkRight(): Unit = {
    this.doWalkRight = true
    val t = new java.util.Timer()
    val thissy = this
    val task = new java.util.TimerTask {
      def run() = {
        if (thissy.doWalkRight) {
          walkRight()
        }
        else {
          this.cancel()
        }
      }
    }
    t.schedule(task, 0, 5)
  }

  def keepWalkUp(): Unit = {
    this.doWalkUp = true
    val t = new java.util.Timer()
    val thissy = this
    val task = new java.util.TimerTask {
      def run() = {
        if (thissy.doWalkUp) {
          walkUp()
        }
        else {
          this.cancel()
        }
      }
    }
    t.schedule(task, 0, 5)
  }

  def keepWalkDown(): Unit = {
    this.doWalkDown = true
    val t = new java.util.Timer()
    val thissy = this
    val task = new java.util.TimerTask {
      def run() = {
        if (thissy.doWalkDown) {
          walkDown()
        }
        else {
          this.cancel()
        }
      }
    }
    t.schedule(task, 0, 5)
  }

  var doWalkLeft : Boolean = false
  var doWalkRight : Boolean = false
  var doWalkUp : Boolean = false
  var doWalkDown : Boolean = false
}
