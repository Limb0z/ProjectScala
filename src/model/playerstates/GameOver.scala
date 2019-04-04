package model.playerstates

import model.Player

class GameOver(player: Player) extends PlayerState(player) {

  override def leftPressed(): Unit = {}

  override def rightPressed(): Unit = {}

  override def upPressed(): Unit = {}

  override def downPressed(): Unit = {}

  override def leftReleased(): Unit = {}

  override def rightReleased(): Unit = {}

  override def upReleased(): Unit = {}

  override def downReleased(): Unit = {}


  override def isAlive: Boolean = {
    false
  }
}
