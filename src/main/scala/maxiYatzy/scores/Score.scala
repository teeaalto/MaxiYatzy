package maxiYatzy.scores

import java.util.NoSuchElementException

import scala.collection.mutable.HashMap

/**
  * Abstract score class. Provides the interface
  * all scores offer.
  */
abstract class Score {
  def name = "default"
  def isUpperSec = false

  protected def occurrances(dices: Array[Int]): Array[(Int,Int)] = {
    val occ = new HashMap[Int, Int]()

    for (dice <- dices) {
      try {
        occ(dice) += 1
      } catch {
        case ex: NoSuchElementException => occ += (dice -> 1)
      }
    }

    occ.toArray.sortBy(_._1)
  }

  protected def highestRepetition(dices: Array[Int], minrep: Int): Int = {
    val occ = occurrances(dices)

    var hirep = 0
    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 >= minrep) hirep = elem._1
    }

    hirep
  }

  /**
    * Check the score of a given set of dices;
    * zero if requirements of the combination are not met
    * @param dices The combination of dices to score
    * @return Points on offer for the dices by this scoring combination
    */
  def score(dices: Array[Int]): Int
}
