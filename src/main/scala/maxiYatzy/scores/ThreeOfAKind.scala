package maxiYatzy.scores

/**
  * Having three of a kind scores the sum of values
  */
object ThreeOfAKind extends Score {
  override val name = "three of a kind"

  def score(dices: Array[Int]): Int =
    3*highestRepetition(dices,3)

}
