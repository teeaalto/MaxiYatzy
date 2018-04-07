package maxiYatzy

/**
  * A single player has a name and a number of throws
  * @param name The name of the player
  */
class Player(val name:String) {
  var throws = 0

  /**
    * Add throws to the player
    * @param n Number of throws to add
    */
  def addThrows(n: Int): Unit = {
    throws += n
  }

  /**
    * Uses one of the player's throws.
    * Throws IllegalStateException if the number of throws
    * would become negative.
    */
  def useThrow(): Unit = {
    if (throws > 0)
      throws -= 1
    else throw new IllegalStateException
  }
}
