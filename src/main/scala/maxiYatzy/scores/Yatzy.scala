package maxiYatzy.scores

object Yatzy extends Score {
  override val name = "yatzy"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    var all = 0
    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 == 6) all = elem._1
    }

    if (all > 0) 50 else 0
  }
}
