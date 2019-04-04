package gameFunctions

import gameObjects._

object functions {

  //adds an item into a players backpack
  def pickUpItem(player:Player, world:World): Unit = {
    for (currentItem <- world.itemList){
      if (player.locationX == currentItem.locationX && player.locationY == currentItem.locationY){
        if (currentItem.isInstanceOf[bandage]){
          player.backpack("bandage") += 1
        }
        if (currentItem.isInstanceOf[food]){
          player.backpack("food") += 1
        }
        if (currentItem.isInstanceOf[ammo]){
          player.backpack("ammo") += 1
        }
      }
    }
  }

  //removes a player if their state is dead
  def checkDeath(list:World): Unit = {
    for (currentPlayer <- list.playerList){
      if(currentPlayer.state == "dead"){
        list.playerList -= currentPlayer
      }
    }
  }

  //add new player to current list of all players
  def addPlayers(player:Player, world:World):Unit = {
    world.playerList = world.playerList :+ player
  }
}
