package model.playerstates

import model.Player

abstract class PlayerState(player: Player) {

  /*
  var timeInState: Double = 0.0

  def update(dt: Double): Unit = {
    timeInState += dt

    if(player.leftKeyHeld){
      this.leftPressed()
    }
    if(player.rightKeyHeld){
      this.rightPressed()
    }
  }
  */

  // API methods. Most methods do nothing by default. Only override methods that are needed for each state
  def leftPressed(): Unit = {}

  def rightPressed(): Unit = {}

  def upPressed(): Unit = {}

  def downPressed(): Unit = {}

  def leftReleased(): Unit = {}

  def rightReleased(): Unit = {}

  def upReleased(): Unit = {}

  def downReleased(): Unit = {}

  def walkLeft(): Unit = {}
  def walkRight(): Unit = {}
  def walkUp(): Unit = {}
  def walkDown(): Unit = {}

  def isAlive: Boolean = {
    true
  }
}
