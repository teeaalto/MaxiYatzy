package maxiYatzy

class MaxiYatzy {
  private val players = new Players
  private val scoretab = new ScoreTable
  private val ds = new Dices

  def addPlayer(name: String) = players.addPlayer(name)
  def addThrows(n: Int): Unit = players.addThrows(n)
  def currentPlName(): String = players.currentPlName
  def playersThrows(): Int = players.playersThrows
  def switchCurrentPlayer(): Unit = players.switchCurrentPlayer()
  def useThrow(): Unit = players.useThrow()

  def throwAll(): Array[Int] = ds.throwAll()
  def throwGiven(dices: Array[Int]) = ds.throwGiven(dices)

  def checkScore(comb: String): Int =
    scoretab.checkScore(comb, ds.prevThrow)

  def score(comb: String, setZero: Boolean): Unit =
    scoretab.score(players.currentPlNum, comb, ds.prevThrow, setZero)

  def showScoreTable: String = scoretab.showScoreTable()
  def hasCombination(comb: String): Boolean = scoretab.hasCombination(comb)
}
