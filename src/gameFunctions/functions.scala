package gameFunctions

import gameObjects._
import model.Player
import tests.TestCheckDeath

object functions {

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
}
