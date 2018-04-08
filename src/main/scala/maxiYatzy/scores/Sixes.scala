package maxiYatzy.scores

/**
  * Sixes score; any number of sixes
  * scores the sum of dice values
  */
object Sixes extends Score {
  override val name = "sixes"
  override val isUpperSec = true

  def score(dices: Array[Int]): Int =
    6*dices.count(_ == 6)

}
