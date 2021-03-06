package GUI

import gameObjects._
import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import model.Player
import model.playerstates.GameOver
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.image.Image
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color.Green
import scalafx.scene.paint.Color.Black
import scalafx.scene.paint.Color.Red

import scala.collection.mutable.ListBuffer
import model.Game
import scalafx.scene.paint.Color

object gui2 extends JFXApp{
  var MainScene: Scene = new Scene {
    fill = Green
   //this.addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

  }
  MainScene.addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
  MainScene.addEventHandler(KeyEvent.KEY_RELEASED, (event: KeyEvent) => keyReleased(event.getCode))
  MainScene.addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => mouseClicked(event.getSceneX,event.getSceneY))
  def mouseClicked(xpos: Double, ypos: Double): Unit = {
    game.shootBullet(player,xpos,ypos)
    if (ypos > 660) {
      if (xpos < 125) {
        player.useBandage(player)
      }
      else if (xpos < 275) {
        player.useFood(player)
      }
    }


  }
  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      /*case "S" => player.keepWalkUp()
      case "A" => player.keepWalkLeft()
      case "W" => player.keepWalkDown()
      case "D" => player.keepWalkRight()
      case "Down" => player.keepWalkUp()
      case "Left" => player.keepWalkLeft()
      case "Up" => player.keepWalkDown()
      case "Right" => player.keepWalkRight()*/
      case "S" => player.walkUp()
      case "A" => player.walkLeft()
      case "W" => player.walkDown()
      case "D" => player.walkRight()
      case "Down" => player.walkUp()
      case "Left" => player.walkLeft()
      case "Up" => player.walkDown()
      case "Right" => player.walkRight()
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }


  def keyReleased(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "S" => player.doWalkUp = false
      case "A" => player.doWalkLeft = false
      case "W" => player.doWalkDown = false
      case "D" => player.doWalkRight = false
      case "Down" => player.doWalkDown = false
      case "Left" => player.doWalkLeft = false
      case "Up" => player.doWalkUp = false
      case "Right" => player.doWalkRight = false
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }
  var MainStage: JFXApp.PrimaryStage = new JFXApp.PrimaryStage {
    scene = MainScene
    height = 840
    width = 1280
  }
  //this player will be set by a json string on game boot
  val player: Player = new Player( 100.0,"alive",120,450, "test")
  player.health = 1
  var gameCanvas:Canvas = new Canvas() {
    layoutY = 0
    layoutX = 0
    height = 840
    width = 1280
  }
  //var backgroundImage:Image = new Image("https://image.freepik.com/free-photo/artificial-grass-field-top-view-texture_3248-2762.jpg")
  var backgroundImage:Image = new Image("https://steemitimages.com/p/2bP4pJr4wVimqCWjYimXJe2cnCgnHt3BWLDAbP22Hmx")
  var gameImage:Image = new Image("https://i2.wp.com/unluckystudio.com/wp-content/uploads/2015/03/preview_idle.gif")
  var ammoImage:Image = new Image("https://gamepedia.cursecdn.com/battlegrounds_gamepedia_en/9/93/Icon_ammo_762mm.png")
  var bandageImage:Image = new Image("https://gamepedia.cursecdn.com/battlegrounds_gamepedia_en/f/f2/FirstAidKit.png")
  var foodImage:Image = new Image("https://gamepedia.cursecdn.com/minecraft_gamepedia/6/66/Steak.png")
  var enemy:Image = new Image("https://gamepedia.cursecdn.com/minecraft_gamepedia/c/c3/Zombie.png")

  var gc:GraphicsContext = gameCanvas.graphicsContext2D
  MainScene.root = new Pane(){
    children=List(gameCanvas)
  }

  //var a:Long = 0
  //val world: World = new World
  val game: Game = new Game
  game.world.playerList += player
  game.world.itemList = ListBuffer(
    new ammo(400,50),
    new ammo(1100,150),
    new ammo(1100,300),
    new food(400,300),
    new food(200,300),
    new bandage(500,450),
    new bandage(800,450))

  val animateTimer = AnimationTimer(t => {
    gc.clearRect(0,0,1280,840)
    gc.drawImage(backgroundImage,0,0,1280,840)
    gc.drawImage(gameImage,player.locationX,player.locationY,62,62)
    for (y <- game.world.itemList) {
      if (y.isInstanceOf[ammo]) {
        gc.drawImage(ammoImage,y.locationX,y.locationY,62,62)
      }
      if (y.isInstanceOf[bandage]) {
        gc.drawImage(bandageImage,y.locationX,y.locationY,62,62)
      }
      if (y.isInstanceOf[food]) {
        gc.drawImage(foodImage,y.locationX,y.locationY,62,62)
      }
    }
    for (y <- game.bulletList) {
      if (y.isInstanceOf[bullet]) {
        game.updateBullet(y,y.whoShot)
        gc.drawImage(ammoImage,y.locationX,y.locationY,15,15)
      }
    }
    gc.drawImage(enemy,900,350,62,75)
    gc.drawImage(enemy,960,400,62,75)
    gc.drawImage(enemy,1020,450,62,75)
    gc.drawImage(enemy,960,450,62,75)
    gc.drawImage(gameImage,1100,50,62,62)
    gc.fillText("Ammo: " + player.backpack("ammo").toString, player.locationX,player.locationY+0)
    gc.fillText("Bandages: " + player.backpack("bandage").toString, player.locationX,player.locationY+10)
    gc.fillText("Food: " + player.backpack("food").toString, player.locationX,player.locationY+20)
    gc.fillText("Health: " + player.health.toString, player.locationX,player.locationY-20)
    gc.fillText("Score: 10000", 50,600)
    gc.fillText("Zombies Killed: 100", 50,615)
    gc.fillText("Players Killed: 0", 50,630)
    gc.fillText("Bullets Shot: "+ player.bulletsShot.toString, 50,645)
    game.pickUpItem(player, game.world)
    //gc.fill = Black
    gc.fillRect(0,660,1280,840)
    gc.drawImage(bandageImage,0,660,125,125)
    gc.drawImage(foodImage,150,660,125,125)
    //gc.fill = Red
   // if (player.locationX > 1000) {
   //   model.playerstates.GameOver.gameOver(player)
  //  }
  })

  animateTimer.start()
}
