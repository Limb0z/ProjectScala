package GUI
import javafx.scene.input.{KeyCode, KeyEvent, MouseEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
import scalafx.scene.{Group, Scene}
object gui extends JFXApp{
  val windowWidth: Double = 800
  val windowHeight: Double = 800

  val playerCircleRadius:Double = 20
  val playerSpeed: Double = 10

  var sceneGraphics: Group = new Group {}

  val player: Circle = new Circle {
    centerX = Math.random() * windowWidth
    centerY = Math.random() * windowHeight
    radius = playerCircleRadius
    fill = Color.Green
  }
  sceneGraphics.children.add(player)

  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => player.translateY.value -= playerSpeed
      case "A" => player.translateX.value -= playerSpeed
      case "S" => player.translateY.value += playerSpeed
      case "D" => player.translateX.value += playerSpeed
      case "Up" => player.translateY.value -= playerSpeed
      case "Left" => player.translateX.value -= playerSpeed
      case "Down" => player.translateY.value += playerSpeed
      case "Right" => player.translateX.value += playerSpeed
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }

  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
    }
  }
}
