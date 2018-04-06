package maxiYatzy.scores

object Chance extends Score {
  override val name = "chance"

  def score(dices: Array[Int]): Int = {
    var sum = 0
    for (d <- dices) sum += d
    sum
  }

}
