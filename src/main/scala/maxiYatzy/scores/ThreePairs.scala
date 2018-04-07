package maxiYatzy.scores

/**
  * Having three distinct pairs (as in, not of the
  * same dice value) scores the sum of dice values
  */
object ThreePairs extends Score {
  override val name = "three pairs"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    if (occ.length == 3 && !occ.exists(p => p._2 != 2))
      2*occ(0)._1 + 2*occ(1)._1 + 2*occ(2)._1
    else 0
  }
}
