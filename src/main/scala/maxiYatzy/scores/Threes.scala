package maxiYatzy.scores

/**
  * Threes score; any number of threes
  * scores the sum of dice values
  */
object Threes extends Score {
  override val name = "threes"

  def score(dices: Array[Int]): Int =
    3*dices.count(_ == 3)

}
