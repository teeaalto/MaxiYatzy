package maxiYatzy.scores

/**
  * Full straight score; having all dice values
  * 1-6 scores the sum of values (21)
  */
object FullStraight extends Score {
  override val name = "full straight"

  def score(dices: Array[Int]): Int = {
    val flstr = Array(1,2,3,4,5,6)
    if (dices.intersect(flstr).length == 6) 21
    else 0
  }

}
