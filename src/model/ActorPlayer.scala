package model

import gameObjects._
import akka.actor._
import scala.util.Random

/*
//Recieved By Player Actor
case class Move()
case class Shoot()
case class Kill()
case class AddPlayer(name:String)
case class RemovePlayer(name:String)*/

class ActorPlayer(gameActor: ActorRef, var name:String, var posX:Double, var posY:Double) extends Actor {

  val max = 1000
  val player = new Player(100, "alive", Math.random() * max, Math.random() * max,"oof2")
  var backpack = scala.collection.mutable.Map("bandage" -> 0, "food" -> 0 ,"ammo" -> 0)
  var alive = true

  var game = new Game()
  val gameState = ""

  override def receive: Receive = {
    case Shoot => {
      if (gameState != "") {
        //val projectiles = player.fire(gameState)
        //        val projectiles = tower.aimFire(gameState)

        //projectiles.foreach(proj => gameActor ! AddProjectile(proj.location.x, proj.location.y, proj.location.z, proj.velocity.x, proj.velocity.y, proj.velocity.z))
      }
    }

    case Kill(name) =>{
      alive = false
    }
  }


}
