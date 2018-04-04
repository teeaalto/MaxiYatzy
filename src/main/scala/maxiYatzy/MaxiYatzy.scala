package maxiYatzy

class MaxiYatzy {
  private val players = new Players
  private val scoretab = new ScoreTable
  private val ds = new Dices

  def addPlayer(name: String) = players.addPlayer(name)
  def currentPlName(): String = players.currentPlName
  def playersThrows(): Int = players.playersThrows
  def addThrows(n: Int): Unit = players.addThrows(n)
  def useThrow(): Unit = players.useThrow()

  def throwAll(): Array[Int] = ds.throwAll()
  def throwGiven(dices: Array[Int]) = ds.throwGiven(dices)
}
