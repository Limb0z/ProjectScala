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
    val player = new Player(100, "alive", 1,1)
    players += (name -> player)
    world.playerList2 = player :: world.playerList2
  }

  def removePlayer2(name:String): Unit = {
    players(name).remove()
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

  def addBullet(bullet:bullet) = {
    bulletList = bullet :: bulletList
  }

  //HIT DETECTION
  def hitDetection():Unit = {
    for(player <- players){
      for(bullet<- bulletList){
        if(distance(player._2, bullet) <= playerSize){
          bullet.destroy1
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
            player.backpack("ammo") += 1
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
    if (player.locationX < item.locationX + 100 && player.locationX + 100 > item.locationX && player.locationY < item.locationY + 100 && player.locationY + 100 > item.locationY) {
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
      "walls" -> Json.toJson(),
      "players" -> Json.toJson(this.players.map({ case (a, b) => Json.toJson(Map(
      "x" -> Json.toJson(b.locationX),
      "y" -> Json.toJson(b.locationY),
      "id" -> Json.toJson(a))) })),

    )
  }
}
