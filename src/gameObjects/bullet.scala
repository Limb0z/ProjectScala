package gameObjects

import model.Player

class bullet(var speed: Double,
             var locX: Double,
             var locY: Double,
             var eX: Double,
             var eY: Double,
             var whoShot: String) extends items(locX, locY){


  def shootBullet(player:Player):Unit  = {
  }
}
