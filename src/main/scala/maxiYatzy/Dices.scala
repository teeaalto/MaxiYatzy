package maxiYatzy

import math.random
import scala.collection.mutable.ArrayBuffer

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
    def diceStrArray(dindex: Int, dnum: Int): Array[String] = {
      def diceCont(i: Int): ArrayBuffer[String] = {
        val dcont = new ArrayBuffer[String]
        val l = "|o    |"
        val r = "|    o|"
        val b = "|o   o|"
        val m = "|  o  |"
        val e = "|     |"
        dcont += {i match {
          case 2 | 3 => l
          case 4 | 5 | 6 => b
          case _ => e
        }}
        dcont += {i match {
          case 1 | 3 | 5 => m
          case 6 => b
          case _ => e
        }}
        dcont += {i match {
          case 2 | 3 => r
          case 4 | 5 | 6 => b
          case _ => e
        }}
      }

      val dstrb = new ArrayBuffer[String]
      dstrb += s"Dice $dindex:"
      dstrb += "       "
      val tAndBRow = "+-----+"
      dstrb += tAndBRow
      dstrb appendAll diceCont(dnum)
      dstrb += tAndBRow
      dstrb.toArray
    }

    val dstringArrs = for (i <- 1 to 6) yield diceStrArray(i, result(i-1))

    val rows = dstringArrs(0).length

    (for (i <- 0 until rows) yield (for (arr <- dstringArrs) yield arr(i)).mkString("", "   ", "\n")).mkString

    /* val res = {
      for (i <- 1 to 6) yield {
        s"Dice $i: " + result(i - 1)
      }
    }
    res.mkString("\n") */
  }
}
