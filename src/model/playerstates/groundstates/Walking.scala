package model.playerstates.groundstates

import model.Player

class Walking(player: Player) extends OnGround(player) {

  override def leftPressed(): Unit = {
    player.walkLeft()
  }

  override def rightPressed(): Unit = {
    player.walkRight()
  }

  override def upPressed(): Unit = {
    player.walkUp()
    //player.state = new Rising(player)
  }

  override def downPressed(): Unit = {
    player.walkDown()
    //player.state = new Rising(player)
  }


  override def leftReleased(): Unit = {
    super.leftReleased()
    player.state = new Standing(player)
  }

  override def rightReleased(): Unit = {
    super.rightReleased()
    player.state = new Standing(player)
  }

}
