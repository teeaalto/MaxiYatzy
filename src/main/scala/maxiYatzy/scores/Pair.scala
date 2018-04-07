package maxiYatzy.scores

/**
  * Having a pair scores the sum of values
  */
object Pair extends Score {
  override val name = "pair"

  def score(dices: Array[Int]): Int =
    2*highestRepetition(dices,2)

}
