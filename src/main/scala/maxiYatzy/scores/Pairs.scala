package maxiYatzy.scores

object Pairs extends Score {
  val name = "pairs"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    var dupl = 0
    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 >= 2) dupl = elem._1
    }

    2*dupl
  }
}
