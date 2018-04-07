package maxiYatzy.scores

/**
  * Large straight score; having all dice values
  * between two and six scores the sum of values (20)
  */
object LargeStraight extends Score {
  override val name = "large straight"

  def score(dices: Array[Int]): Int = {
    val lrgstr = Array(2,3,4,5,6)
    if (dices.intersect(lrgstr).length == 5) 20
    else 0
  }

}
