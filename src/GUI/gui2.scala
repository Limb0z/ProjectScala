package GUI


//import GUI.gui.{keyPressed, player}
import gameObjects._
import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import model.Player
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene
import scalafx.scene.Scene
import scalafx.scene.canvas.{Canvas, GraphicsContext}
import scalafx.scene.image.Image
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color.Green
import scala.collection.mutable.ListBuffer
import gameFunctions.functions

object gui2 extends JFXApp{
  var MainScene: Scene = new Scene {
    fill = Green
   //this.addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

  }
  MainScene.addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

  //W and S inverted controls
  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => player.walkDown()
      case "A" => player.walkLeft()
      case "S" => player.walkUp()
      case "D" => player.walkRight()
      case "Up" => player.walkDown()
      case "Left" => player.walkLeft()
      case "Down" => player.walkUp()
      case "Right" => player.walkRight()
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }
  var MainStage: JFXApp.PrimaryStage = new JFXApp.PrimaryStage {
    scene = MainScene
    height = 720
    width = 1280
  }
  //this player will be set by a json string on game boot
  val player: Player = new Player( 100.0,"alive",230,100)

  var gameCanvas:Canvas = new Canvas(){
    layoutY=0
    layoutX=0
    height=720
    width=1280
  }
  var gameImage:Image = new Image("https://i2.wp.com/unluckystudio.com/wp-content/uploads/2015/03/preview_idle.gif")
  var ammoImage:Image = new Image("https://gamepedia.cursecdn.com/battlegrounds_gamepedia_en/9/93/Icon_ammo_762mm.png")

  var gc:GraphicsContext = gameCanvas.graphicsContext2D
  MainScene.root = new Pane(){
    children=List(gameCanvas)
  }

  //var a:Long = 0
  val world: World = new World
  world.itemList = ListBuffer(new ammo(600,50),new ammo(600,300))

  val animateTimer = AnimationTimer(t => {
    gc.clearRect(0,0,1280,720)
    gc.drawImage(gameImage,player.locationX,player.locationY,125,125)
    for (y <- world.itemList) {
      if (y.isInstanceOf[ammo]) {
        gc.drawImage(ammoImage,y.locationX,y.locationY,125,125)
      }
    }
    gc.fillText(player.backpack("ammo").toString, player.locationX,player.locationY-20)
    gameFunctions.functions.pickUpItem(player, world)

  })

  animateTimer.start()
}
