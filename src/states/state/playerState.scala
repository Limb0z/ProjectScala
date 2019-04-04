package states.state

abstract class playerState (player:Player){

  def leftPressed(): Unit = {}

  def rightPressed(): Unit = {}

  def upPressed(): Unit = {}

  def downPressed(): Unit = {}

  def leftReleased(): Unit = {}

  def rightReleased(): Unit = {}

  def jumpReleased(): Unit = {}

  def downReleased(): Unit = {}
}
