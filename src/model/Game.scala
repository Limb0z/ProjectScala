package model

import gameObjects._
import play.api.libs.json.{JsValue, Json}

class Game {

  val world = new World

  var players: Map[String,Player] = Map()
  var itemList: List[items] = List()
  var bulletList: List[bullet] = List()

  var playerSize:Double = 0.5

  var lastUpdateTime: Long = System.nanoTime()

  //removes a player if their state is dead
  def checkDeath(world:World): Unit = {
    for (currentPlayer <- world.playerList){
      if(currentPlayer.condition == "dead"){
        world.playerList -= currentPlayer
      }
    }
  }

  //add new player to current list of all players
  def addPlayers(player:Player, world:World):Unit = {
    world.playerList = world.playerList :+ player
  }

  //TRYING DIFFERENT WAY OF ADDING AND REMOVING PLAYERS
  def addPlayers2(name:String): Unit = {
    val player = new Player(100, "alive", 1,1,"oof")
    players += (name -> player)
    world.playerList2 = player :: world.playerList2
  }

  def removePlayer2(name:String): Unit = {
    players(name).removePlayer()
    players -= name
  }

  def checkDeath2():Unit ={
    for(player <- players){
      if(player._2.health == 0){
        removePlayer2(player._1)
      }
    }
  }

  def distance(player: Player, bullet:bullet): Double = {
    Math.sqrt(Math.pow(player.locationX - bullet.locationX, 2.0) + Math.pow(player.locationY - bullet.locationY, 2.0))
  }

  def addBullet(bullet : bullet) : Unit = {
    bulletList = bullet :: bulletList
  }

  def updateBullet(bullet:bullet,player:String):Unit = {
    var whoDid = new Player(100, "alive", 0,0,"oof3")
    for (zz <- world.playerList) {
      if (zz.name == player) {
        whoDid = zz
      }
    }
    var dx = bullet.eX-whoDid.locationX
    var dy = bullet.eY-whoDid.locationY
    var mag = Math.sqrt(dx*dx+dy*dy)
    bullet.locationX = bullet.locationX + (dx/mag)*bullet.speed
    bullet.locationY = bullet.locationY + (dy/mag)*bullet.speed
  }

  def shootBullet(player: Player, xcoord:Double,ycoord:Double): Unit = {
    if (player.backpack("ammo") > 0) {
      val bullet = new bullet(16, player.locationX + 50, player.locationY + 50, xcoord - 50, ycoord - 50, player.name)
      addBullet(bullet)
      player.backpack("ammo") -= 1
      player.bulletsShot += 1
    }
  }

  //HIT DETECTION
  def hitDetection():Unit = {
    for(player <- players){
      for(bullet<- bulletList){
        if(distance(player._2, bullet) <= playerSize){
          bullet.destroy
          player._2.health -= 25.0
          checkDeath2()
        }
      }
    }
  }

  //adds an item into a players backpack
  def pickUpItem(player:Player, world:World): Unit = {
    for (currentItem <- world.itemList){
      if (checkHitbox(player,currentItem)){ //Added some hitboxes so that a player doesnt have to be centered above an item to pick it up.
        currentItem match {
          case _ : ammo =>
            player.backpack("ammo") += 30
            world.itemList -= currentItem
          case _ : bandage =>
            player.backpack("bandage") += 1
            world.itemList -= currentItem
          case _ : food =>
            player.backpack("food") += 1
            world.itemList -= currentItem
          case _ =>
            world.itemList -= currentItem
        }
      }
    }
  }/*
  def updateBullet(world:World): Unit = {
    for (bullet <- world.bulletList) {

    }
  }*/
  def checkHitbox(player: Player, item: items): Boolean = {//15 pixel hitbox
    if (player.locationX < item.locationX + 50 && player.locationX + 50 > item.locationX && player.locationY < item.locationY + 50 && player.locationY + 50 > item.locationY) {
      true
    }
    else {
      false
    }
  }


  def update: Unit = {
    val time: Long = System.nanoTime()
    val changeInTime = (time - this.lastUpdateTime) / 99999999999.0
    hitDetection()
    checkDeath2()
    this.lastUpdateTime = time
  }

  def gameState(): String = {
    val gameState:Map[String, JsValue] = Map(
      //"walls" -> Json.toJson(),
      "players" -> Json.toJson(this.players.map({ case (a, b) => Json.toJson(Map(
      "x" -> Json.toJson(b.locationX),
      "y" -> Json.toJson(b.locationY),
      "id" -> Json.toJson(a))) })),
    )
    Json.stringify(Json.toJson(gameState))
  }
}
