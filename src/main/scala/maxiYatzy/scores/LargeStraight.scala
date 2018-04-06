package maxiYatzy.scores

object LargeStraight extends Score {
  override val name = "large straight"

  def score(dices: Array[Int]): Int = {
    val lrgstr = Array(2,3,4,5,6)
    if (dices.intersect(lrgstr).length == 5) 20
    else 0
  }

}
