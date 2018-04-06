package maxiYatzy.scores

object SmallStraight extends Score {
  override val name = "small straight"

  def score(dices: Array[Int]): Int = {
    val smstr = Array(1,2,3,4,5)
    if (dices.intersect(smstr).length == 5) 15
    else 0
  }

}
