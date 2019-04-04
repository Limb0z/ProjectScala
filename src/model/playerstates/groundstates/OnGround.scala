package model.playerstates.groundstates

import model.Player
import model.playerstates.PlayerState


abstract class OnGround(player: Player) extends PlayerState(player) {

  override def leftPressed(): Unit = {
    player.walkLeft()
    player.state = new Walking(player)
  }

  override def rightPressed(): Unit = {
    player.walkRight()
    player.state = new Walking(player)
  }

  override def upPressed(): Unit = {
    player.walkUp()
    player.state = new Walking(player)
  }

  override def downPressed(): Unit = {
    player.walkDown()
    player.state = new Walking(player)

    /*
  override def noPlatformCollision(): Unit = {
    if(this.timeInState > 0.2) {
      player.state = new Falling(player)
    }
  }
  */
  }
}
