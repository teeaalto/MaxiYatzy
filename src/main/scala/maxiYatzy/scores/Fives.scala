package maxiYatzy.scores

/**
  * Fives score; any number of fives
  * scores the sum of dice values
  */
object Fives extends Score {
  override val name = "fives"

  def score(dices: Array[Int]): Int =
    5*dices.count(_ == 5)

}
