package maxiYatzy

import scores._

import scala.collection.mutable.HashSet
import scala.collection.mutable.ArrayBuffer

/**
  * Scoring: the combinations and the current situation
  */
class ScoreTable {
  private val combinations = Map(
// comb num -> combination
    1 -> Ones,
    2 -> Twos,
    3 -> Threes,
    4 -> Fours,
    5 -> Fives,
    6 -> Sixes,
    8 -> Pair,
    9 -> TwoPairs,
    10 -> ThreePairs,
    11 -> ThreeOfAKind,
    12 -> FourOfAKind,
    13 -> FiveOfAKind,
    14 -> SmallStraight,
    15 -> LargeStraight,
    16 -> FullStraight,
    17 -> Cottage,
    18 -> House,
    19 -> Tower,
    20 -> Chance,
    21 -> Yatzy,
  )

  private val scores = new HashSet[(
    Int,     // Player number
    Int,     // Combination number
    Int,     // Points
    Boolean  // Is of upper section
  )]

  private def combNumber(comb: String): Option[Int] = {
    val knownCombs = for ((k,v) <- combinations if v.name == comb) yield k
    knownCombs.headOption

  }

                                                  // (points, combination, is upper sec
  private def tryCombination(comb: String, ds: Array[Int]): (Int, Int, Boolean) = {
    combNumber(comb) match {
      case Some(combnum) => {
        val foundComb = combinations(combnum)
        (foundComb.score(ds),combnum, foundComb.isUpperSec)
      }
      case None => throw new IllegalArgumentException
    }
  }


  /**
    * Check whether the sought after scoring combination exists
    * @param comb The name of the combination to look for
    * @return True if combination exists
    */
  def hasCombination(comb: String): Boolean = {
    val knownCombs = for (c <- combinations.values) yield c.name
    knownCombs.toArray.contains(comb)
  }

  /**
    * Check how many points a set of dice values would score
    * in a specified scoring combination or imply that
    * the current player already has scored the combination
    * @param comb The name of the combination
    * @param ds The set of dices
    * @param plnum Current player number
    * @return Available points
    */
  def checkScore(comb: String, ds: Array[Int], plnum: Int): Option[Int] = {
    combNumber(comb) match {
      case Some(combnum) => {
        if (!playerHasScore(combnum, plnum)) Some(combinations(combnum).score(ds))
        else None
      }
      case None => None
    }
  }


  private def playerHasScore(combnum: Int, plnum: Int) =
    scores.exists(p => p._1 == plnum && p._2 == combnum)

  
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
    val (pnts, combnum, isUpperSec) = tryCombination(comb, ds)
    if (isUpperSec) {
      val plUScSum = (for (
          s <- scores.toArray
          if s._1 == plnum
          if s._4) yield s._3).sum
      if (plUScSum >= 84) scores += ((plnum, 7, 50, false))
    }
    scores += ((plnum, combnum, if (setZero) 0 else pnts, isUpperSec))
  }

  private val maxCombNameLen = (for (v <- combinations.values) yield v.name.length).max

  /**
    * Show the score table
    * @return Current score table
    */
  def showScoreTable(players: Map[Int, String]): String = {
    val columnpadding = 1
    val plKeys = players.keys.toSeq.sorted
    val linerow = "-" * (
      maxCombNameLen +
      (2*columnpadding + 1)*players.size +
      (for (plname <- players.values) yield plname.length).sum
    ) + "\n"
    val rows = new StringBuilder

    def pointpad(pnt: Int, plname: String): String = {
      val lpad = (plname.length - pnt.toString.length) / 2
      val rpad = plname.length - lpad - pnt.toString.length
      " " * columnpadding +
          "|" +
          " " * columnpadding +
          " " * lpad +
          pnt +
          " " * rpad
    }

    def emptypad(plname: String): String = {
      " " * columnpadding +
          "|" +
          " " * (columnpadding + plname.length)
    }

    def pointrow(combNum: Int, combName: String) = {
      val thisrow = new StringBuilder
      val thisrowpnts = scores.filter(_._2 == combNum)
      thisrow append (
        combName +
          " " * (maxCombNameLen - combName.length)
        )
      for (k <- plKeys) {
        val pnts = thisrowpnts.filter(_._1 == k)
        pnts.headOption match {
          case Some((_, _, pnt, _)) =>
            thisrow append pointpad(pnt, players(k))
          case None =>
            thisrow append emptypad(players(k))
        }
      }

      thisrow + "\n"
    }

    rows append (" " * maxCombNameLen)
    for (k <- plKeys) rows append {
      val pl = players(k)
      s" | $pl"
    }
    rows append "\n"
    rows append linerow
    for (i <- (
        for ((k,v) <- combinations
        if v.isUpperSec) yield k
        ).toSeq.sorted)
      rows append pointrow(i, combinations(i).name)
    rows append linerow
    rows append (
        "sum" +
        " " * (maxCombNameLen - 3)
    )
    for (k <- plKeys) {
      val usecsum = (
        for (usecscore <- scores.toArray
            if usecscore._1 == k
            if usecscore._4)
        yield usecscore._3).sum
      rows append pointpad(usecsum, players(k))
    }
    rows append "\n"
    rows append pointrow(7, "bonus")
    rows append linerow
    for (i <- (
      for ((k,v) <- combinations
           if !v.isUpperSec) yield k
      ).toSeq.sorted)
      rows append pointrow(i, combinations(i).name)
    rows append linerow
    rows append (
      "total" +
        " " * (maxCombNameLen - 5)
      )
    for (k <- plKeys) {
      val totalsum = (
        for (plscore <- scores.toArray
             if plscore._1 == k
        ) yield plscore._3).sum

      rows append pointpad(totalsum, players(k))
    }
    rows append "\n"
    rows.toString
  }
}