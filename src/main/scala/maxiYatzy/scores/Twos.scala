package maxiYatzy.scores

/**
  * Twos score; any number of twos
  * scores the sum of dice values
  */
object Twos extends Score {
  override val name = "twos"

  def score(dices: Array[Int]): Int =
    2*dices.count(_ == 2)

}
