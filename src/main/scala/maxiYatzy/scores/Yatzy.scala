package maxiYatzy.scores

/**
  * Having the same number on all dices scores 50 points
  */
object Yatzy extends Score {
  override val name = "yatzy"

  def score(dices: Array[Int]): Int =
    if (occurrances(dices).length == 1) 50 else 0

}
