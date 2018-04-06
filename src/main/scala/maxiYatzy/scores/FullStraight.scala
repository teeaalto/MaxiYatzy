package maxiYatzy.scores

object FullStraight extends Score {
  override val name = "full straight"

  def score(dices: Array[Int]): Int = {
    val flstr = Array(1,2,3,4,5,6)
    if (dices.intersect(flstr).length == 6) 21
    else 0
  }

}
