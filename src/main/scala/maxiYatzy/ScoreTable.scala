package maxiYatzy

import scores._

import scala.collection.mutable.ArrayBuffer

/**
  * Scoring: the combinations and the current situation
  */
class ScoreTable {
  private val combinations = Array(
    (1,Ones),
    (2,Twos),
    (3,Threes),
    (4,Fours),
    (5,Fives),
    (6,Sixes),
    (8,Pair),
    (9,TwoPairs),
    (10,ThreePairs),
    (11,ThreeOfAKind),
    (12,FourOfAKind),
    (13,FiveOfAKind),
    (14,SmallStraight),
    (15,LargeStraight),
    (16,FullStraight),
    (17,Cottage),
    (18,House),
    (19,Tower),
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

  /**
    * Check whether the sought after scoring combination exists
    * @param comb The name of the combination to look for
    * @return True if combination exists
    */
  def hasCombination(comb: String): Boolean = {
    val knownCombs = for (c <- combinations) yield c._2.name
    knownCombs.contains(comb)
  }

  /**
    * Check how many points a set of dice values would score
    * in a specified scoring combination
    * @param comb The name of the combination
    * @param ds The set of dices
    * @return Available points
    */
  def checkScore(comb: String, ds: Array[Int]): Int =
    tryCombination(comb, ds)._1

  /**
    * Add a scoring datum
    * @param plnum The number of the player for whom the score is designated
    * @param comb The name of the combination to which the score corresponds to
    * @param ds The dice roll to score
    * @param setZero True, if the dice roll is to be ignored and score set to zero
    */
  def score(plnum: Int,
            comb: String,
            ds: Array[Int],
            setZero: Boolean): Unit = {
    val (pnts, combnum) = tryCombination(comb, ds)
    scores += ((plnum, combnum, if (setZero) 0 else pnts ))
  }

  /**
    * Show the score table
    * @return Current score table
    */
  def showScoreTable(): String = scores.toString
}
