package model

import gameObjects._
import akka.actor._

/*
//Recieved By Player Actor
case class Move()
case class Shoot()
case class Kill()
case class AddPlayer(name:String)
case class RemovePlayer(name:String)*/

class ActorPlayer(var name:String, var posX:Double, var posY:Double) extends Actor {

  var backpack = scala.collection.mutable.Map("bandage" -> 0, "food" -> 0 ,"ammo" -> 0)
  var alive = true
  var game = new Game()

  override def receive: Receive = {
    case Shoot => {
      val bullet = new bullet()
    }

    case Kill =>{
      alive = false
    }
  }


}
