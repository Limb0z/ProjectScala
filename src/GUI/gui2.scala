package GUI


import GUI.gui.{keyPressed, player}
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

object gui2 extends JFXApp{
  var MainScene: Scene = new Scene {
    fill = Green
   //this.addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

  }
  MainScene.addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))

  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => player.walkUp()
      case "A" => player.walkLeft()
      case "S" => player.walkDown()
      case "D" => player.walkRight()
      case "Up" => player.walkUp()
      case "Left" => player.walkLeft()
      case "Down" => player.walkDown()
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
  val player: Player = new Player(scala.collection.mutable.Map("bandage" -> 0, "food" -> 0), 100.0,"alive",0,100)

  var gameCanvas:Canvas = new Canvas(){
    layoutY=0
    layoutX=0
    height=720
    width=1280
  }
  var gameImage:Image = new Image("https://i2.wp.com/unluckystudio.com/wp-content/uploads/2015/03/preview_idle.gif")

  var gc:GraphicsContext = gameCanvas.graphicsContext2D
  MainScene.root = new Pane(){
    children=List(gameCanvas)
  }

  //var a:Long = 0

  val animateTimer = AnimationTimer(t => {
    gc.clearRect(0,0,1280,720)
    gc.drawImage(gameImage,player.locationX,player.locationY,125,125)

  })

  animateTimer.start()
}