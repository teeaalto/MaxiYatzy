package maxiYatzy

class MaxiYatzy {
  val players = new Players
  val scoretab = new ScoreTable

  def addPlayer(name: String) = players.addPlayer(name)
}
