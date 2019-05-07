package gameObjects

class items (var locationX:Double, var locationY:Double){

  var destroy1 = false

  def destroy: Unit = {
    destroy1 = true
  }
}
