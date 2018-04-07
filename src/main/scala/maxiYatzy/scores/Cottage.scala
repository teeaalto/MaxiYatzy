package maxiYatzy.scores

/**
  * Cottage score; a pair + 3 of a kind scores
  * the sum of dice values
  */
object Cottage extends Score {
  override val name = "cottage"

  def score(dices: Array[Int]): Int = {
    val occSortedByOcc = occurrances(dices).sortBy(-_._2)
    if (occSortedByOcc(0)._2 == 3 || occSortedByOcc(0)._2 == 4)
      occSortedByOcc(1)._2 match {
        case 2 => 3 * occSortedByOcc(0)._1 + 2 * occSortedByOcc(1)._1
        case 3 => {
          if (occSortedByOcc(0)._1 > occSortedByOcc(1)._1)
            3 * occSortedByOcc(0)._1 + 2 * occSortedByOcc(1)._1
          else
            3 * occSortedByOcc(1)._1 + 2 * occSortedByOcc(0)._1
        }
        case _ => 0
      }
    else 0

    }
}
