package maxiYatzy.scores

/**
  * Fours score; any number of fours
  * scores the sum of dice values
  */
object Fours extends Score {
  override val name = "fours"
  override val isUpperSec = true

  def score(dices: Array[Int]): Int =
    4*dices.count(_ == 4)

}
