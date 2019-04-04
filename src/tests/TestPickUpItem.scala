package tests

import gameFunctions._
import gameObjects._
import org.scalatest.FunSuite
import scala.collection.mutable.ListBuffer
import model.Player

class TestPickUpItem extends FunSuite {

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

  test("pick up items and put it into backpack"){
    var p1:Player = new Player(Map("bandage" -> 0, "food" -> 0), 100.0, "alive", 100, 100)
    var p2:Player = new Player(Map("bandage" -> 1, "food" -> 2), 100.0, "alive", 14, 17)
    var p3:Player = new Player(Map("bandage" -> 6, "food" -> 9), 100.0, "alive", 91, 3)
    var p4:Player = new Player(Map("bandage" -> 5, "food" -> 2), 100.0, "alive", 34, 84)
    var p5:Player = new Player(Map("bandage" -> 3, "food" -> 4), 100.0, "alive", 2, 372)
    var p6:Player = new Player(Map("bandage" -> 7, "food" -> 8), 100.0, "alive", 352, 869)

    val i1:bandage = new bandage(100, 100)
    val i2:bandage = new bandage(234, 567)
    val i3:bandage = new bandage(0, 0)
    val i4:food = new food(2, 372)
    val i5:food = new food(1000, 1000)
    val i6:food = new food(134, 987)

    val l1:ListBuffer[items] = ListBuffer(i1, i3, i5)
    val l2:ListBuffer[items] = ListBuffer(i2, i4, i6)
    val l3:ListBuffer[items] = ListBuffer(i1, i2, i3)
    val l4:ListBuffer[items] = ListBuffer(i4, i5, i6)

    val w1:World = new World
    w1.itemList = l1
    val w2:World = new World
    w2.itemList = l2
    val w3:World = new World
    w3.itemList = l3
    val w4:World = new World
    w4.itemList = l4

    pickUpItem(p1, w1)
    pickUpItem(p2, w2)
    pickUpItem(p3, w3)
    pickUpItem(p4, w4)
    pickUpItem(p5, w4)
    pickUpItem(p6, w3)

    assert(p1.backpack("bandage") == 1,true)
    assert(p2.backpack("bandage") == 1,true)
    assert(p3.backpack("bandage") == 6,true)
    assert(p4.backpack("food") == 2,true)
    assert(p5.backpack("food") == 5,true)
    assert(p6.backpack("food") == 8,true)
  }
}
