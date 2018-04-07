package maxiYatzy.scores

/**
  * Four of a kind scores the sum of dice values
  */
object FourOfAKind extends Score {
  override val name = "four of a kind"

  def score(dices: Array[Int]): Int =
    4*highestRepetition(dices,4)

}
