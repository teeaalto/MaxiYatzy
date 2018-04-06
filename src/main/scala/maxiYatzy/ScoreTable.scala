package maxiYatzy

import scores._

import scala.collection.mutable.ArrayBuffer

class ScoreTable {
  private val combinations = Array(
    (8,Pair),
    (9,TwoPairs),
    (10,ThreePairs),
    (11,ThreeOfAKind),
    (12,FourOfAKind),
    (13,FiveOfAKind),
    (14,SmallStraight),
    (15,LargeStraight),
    (16,FullStraight),
    (20,Chance),
    (21,Yatzy)
  )

  private val scores = new ArrayBuffer[(Int,  // Player name
                                        Int,  // Combination number
                                        Int)] // Points

                                                      // (points, combination
  private def tryCombination(comb: String, ds: Array[Int]): (Int, Int) = {
    var pnts = -1
    var combinationMatch = -1

    for (c <- combinations) {
      if (c._2.name == comb) {
        combinationMatch = c._1
        pnts = c._2.score(ds)
      }
    }

    if (pnts < 0) throw new IllegalArgumentException

    (pnts, combinationMatch)
  }

  def hasCombination(comb: String): Boolean = {
    val knownCombs = for (c <- combinations) yield c._2.name
    knownCombs.contains(comb)
  }

  def checkScore(comb: String, ds: Array[Int]): Int =
    tryCombination(comb, ds)._1

  def score(plnum: Int,
            comb: String,
            ds: Array[Int],
            setZero: Boolean): Unit = {
    val (pnts, combnum) = tryCombination(comb, ds)
    scores += ((plnum, combnum, if (setZero) 0 else pnts ))
  }

  def showScoreTable(): String = scores.toString
}
