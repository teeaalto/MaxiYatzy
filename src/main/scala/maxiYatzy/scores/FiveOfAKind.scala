package maxiYatzy.scores

object FiveOfAKind extends Score {
  override val name = "five of a kind"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    var quint = 0
    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 >= 5) quint = elem._1
    }

    5*quint
  }
}
