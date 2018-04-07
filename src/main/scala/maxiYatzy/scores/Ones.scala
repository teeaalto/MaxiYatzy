package maxiYatzy.scores

/**
  * Ones score; any number of ones
  * scores the sum of dice values
  */
object Ones extends Score {
  override val name = "ones"

  def score(dices: Array[Int]): Int =
    dices.count(_ == 1)

}
