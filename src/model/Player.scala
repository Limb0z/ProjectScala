package model

import model.playerstates.PlayerState
import model.playerstates.groundstates.Walking
import gameObjects._

class Player(var backpack: Map[String, Int] = Map("bandage" -> 0, "food" -> 0, "ammo" -> 0),
             var hunger:Double,
             var locationX:Double,
             var locationY:Double)
  extends living(health = 100.0) {


  def useBandage(player: Player): Unit = {
    player.health += 10.0
    if (player.health > 100.0){
      player.health = 100.0
    }
    player.backpack("bandage") -= 1
  }

  def useFood(player: Player): Unit = {
    player.health += 10
    if (player.hunger > 100){
      player.hunger = 100
    }
    player.backpack("food") -= 1
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
    this.locationX -= 1
  }

  def walkRight(): Unit = {
    this.locationX += 1
  }

  def walkUp(): Unit = {
    this.locationY += 1
  }

  def walkDown(): Unit = {
    this.locationY -= 1
  }
}
