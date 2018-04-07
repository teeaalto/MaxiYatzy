package maxiYatzy.scores

/**
  * Tower score; a pair + 4 of a kind scores
  * the sum of dice values
  */
object Tower extends Score {
  override val name = "tower"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)
    if (occ.length == 2) {
      if (occ(0)._2 == 2) 2*occ(0)._1 + 4*occ(1)._1
      else if (occ(0)._2 == 4) 4*occ(0)._1 + 2*occ(1)._1
      else 0
    }
    else 0
  }

}
