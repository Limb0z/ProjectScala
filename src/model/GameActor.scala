package model

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import gameObjects._

class GameActor extends Actor{

  var game = new Game()

  override def receive: Receive = {
    case message: AddPlayer => game.addPlayers2(message.name)
    case message: RemovePlayer => game.removePlayer2(message.name)

    case updateGame =>
      game.update

    case sendGameState => sender() ! GameState(game.gameState())

    case bullets: addBullet =>
      val speed:Double = 10
      val locX:Double = 0
      val locY: Double = 0
      val eX: Double = 0
      val eY: Double = 0
      val velocityX: Double = 0
      val velocityY: Double = 0
      val whoShot: String = ""
      game.addBullet(new bullet(speed, locX, locY, eX, eY, velocityX, velocityY, whoShot))
  }
}
