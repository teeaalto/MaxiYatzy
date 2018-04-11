package maxiYatzy

import math.random

/**
  * Dices and the rolling of them
  */
class Dices {
  private val result: Array[Int] = Array(0,0,0,0,0,0)

  private def throwOne(): Int =
    math.round(6*random + 0.5).toInt

  var initialThrowDone = false

  /**
    * Throw only specified dices, leaving the rest
    * as thrown previously
    * @param dNumToThrow Dices to throw as an array of indices
    * @return The whole set of dices with some of them rethrown
    */
  def throwGiven(dNumToThrow: Array[Int]): Array[Int] = {
    for (i <- dNumToThrow) {
      try {
        result(i - 1) = throwOne
      } catch {
        case ex: IndexOutOfBoundsException => ()
      }
    }
    result
  }

  /**
    * Throw all of the dices
    * @return The result of the dice throw
    */
  def throwAll(): Array[Int] = {
    for (i <- 0 to 5) result(i) = throwOne
    result
  }

  /**
    * Shows the result of the most recent dice roll
    * @return The previous dice roll result
    */
  def prevThrow = result

  /**
    * Pretty prints the previous dice result
    * @return Dices from the previous throw
    */
  def showDices: String = {
    val res = {
      for (i <- 1 to 6) yield {
        s"Dice $i: " + result(i - 1)
      }
    }
    res.mkString("\n")
  }
}
