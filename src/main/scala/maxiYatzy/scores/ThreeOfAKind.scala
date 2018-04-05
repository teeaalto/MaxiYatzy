package maxiYatzy.scores

object ThreeOfAKind extends Score {
  override val name = "three of a kind"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    var tripl = 0
    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 >= 3) tripl = elem._1
    }

    3*tripl
  }
}
