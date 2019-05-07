package gameObjects

class bullet(var speed: Double,
             var locX: Double,
             var locY: Double,
             var eX: Double,
             var eY: Double,
             var velocityX: Double,
             var velocityY: Double,
             var damage: Double,
             var whoShot: String) extends items(locX, locY){

}
