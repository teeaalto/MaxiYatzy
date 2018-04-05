package maxiYatzy.scores

object FiveOfAKind extends Score {
  override val name = "four of a kind"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    var quadr = 0
    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 >= 5) quadr = elem._1
    }

    5*quadr
  }
}
