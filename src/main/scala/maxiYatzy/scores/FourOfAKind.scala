package maxiYatzy.scores

object FourOfAKind extends Score {
  override val name = "four of a kind"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    var quadr = 0
    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 >= 4) quadr = elem._1
    }

    4*quadr
  }
}
