package maxiYatzy.scores

object TwoPairs extends Score {
  override val name = "two pairs"

  def score(dices: Array[Int]): Int = {
    val occ = occurrances(dices)

    var fstPairDice = 0
    var sndPairDice = 0

    for (i <- occ.indices) {
      val elem = occ(i)
      if (elem._2 >= 2) {
        if (fstPairDice == 0) fstPairDice = elem._1
        else if (sndPairDice == 0) sndPairDice = elem._1
        else fstPairDice = elem._1
      }
    }

    if (sndPairDice != 0) 2*fstPairDice + 2*sndPairDice else 0
  }
}
