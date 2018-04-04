package maxiYatzy

class Player(val name:String) {
  var throws = 0

  def addThrows(n: Int): Unit = {
    throws += n
  }

  def useThrow(): Unit = {
    if (throws > 0)
      throws -= 1
    else throw new IllegalArgumentException
  }
}
