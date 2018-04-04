package maxiYatzy

import math.random

class Dices {
  private val result: Array[Int] = Array(0,0,0,0,0,0)

  private def throwOne(): Int =
    math.round(6*random + 0.5).toInt

  def throwGiven(ds: Array[Int]): Array[Int] = {
    for (i <- ds) {
      try {
        result(i - 1) = throwOne
      } catch {
        case ex: IndexOutOfBoundsException => ()
      }
    }
    result
  }

  def throwAll(): Array[Int] = {
    for (i <- 0 to 5) result(i) = throwOne
    result
  }
}
