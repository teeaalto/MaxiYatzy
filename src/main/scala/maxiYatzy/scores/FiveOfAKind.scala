package maxiYatzy.scores

object FiveOfAKind extends Score {
  override val name = "five of a kind"

  def score(dices: Array[Int]): Int =
    5*highestRepetition(dices,5)

}
