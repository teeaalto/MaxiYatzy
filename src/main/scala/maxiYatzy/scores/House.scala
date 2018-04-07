package maxiYatzy.scores

/**
  * House score; 3 of a kind + 3 of a kind scores
  * the sum of dice values
  */
object House extends Score {
  override val name = "house"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)
    if (occ.length == 2 && occ(0)._2 == 3) 3*occ(0)._1 + 3*occ(1)._1
    else 0
  }

}
