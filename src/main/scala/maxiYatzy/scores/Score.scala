package maxiYatzy.scores

import java.util.NoSuchElementException

import scala.collection.mutable.HashMap

abstract class Score {
  def name

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

  def score(dices: Array[Int]): Int
}
