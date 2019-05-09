package model

//Player Actor
case class Move()
case class Shoot()
case class Kill()

//Game Actor
case class AddPlayer(name:String)
case class RemovePlayer(name:String)
case object sendGameUpdate
case object updateGame
case class addBullet()

case object SendGameState
case class GameState(gameState: String)