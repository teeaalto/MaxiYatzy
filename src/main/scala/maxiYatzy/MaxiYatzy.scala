package maxiYatzy

/**
  * A class to pull the system logic of MaxiYatzy together.
  * Handles the communication between the controllers
  * and system components.
  */
class MaxiYatzy {
  private val players = new Players
  private val scoretab = new ScoreTable
  private val ds = new Dices

  /**
    * Add a player
    *
    * @param name Name of the player
    */
  def addPlayer(name: String): Unit = players.addPlayer(name)

  /**
    * Add throws to the current player
    *
    * @param n Number of throws to add
    */
  def addThrows(n: Int): Unit = players.addThrows(n)

  /**
    * Show the current player name
    *
    * @return The name of the current player
    */
  def currentPlName: String = players.currentPlName

  /**
    * Show the number of throws the current player has
    *
    * @return Number of throws
    */
  def playersThrows: Int = players.playersThrows


  /**
    * Consume one of the current player's throws.
    * Throws IllegalStateException if this would result
    * in a negative number of throws.
    */
  def useThrow(): Unit = players.useThrow()

  /**
    * Indicates whether at least one player has been
    * added to the game
    * @return true if there is a player in the game
    */
  def playerAdded: Boolean = players.playerAdded

  // ============================================

  /**
    * Throw all of the dices
    * @return The throw result
    */
  def throwAll(): Array[Int] = ds.throwAll()

  /**
    * Throw only specified dices, leaving the rest
    * as thrown previously
    *
    * @param dNumToThrow Dices to throw as an array of indices
    * @return The whole set of dices with some of them rethrown
    */
  def throwGiven(dNumToThrow: Array[Int]): Array[Int] = ds.throwGiven(dNumToThrow)

  def initialThrowDone: Boolean = ds.initialThrowDone

  def mkInitialThrow(): Unit = {
    ds.initialThrowDone = true
  }

  /**
    * Pretty prints the previous dice result
    * @return Dices from the previous throw
    */
  def showDices: String = ds.showDices

  // ============================================

  /**
    * Check how many points the current set of dices would score
    * in a specified scoring combination
    *
    * @param comb The name of the combination
    * @return Available points
    */
  def checkScore(comb: String): Option[Int] =
    scoretab.checkScore(comb, ds.prevThrow, players.currentPlNum)

  /**
    * The names of the highest scoring players at this point of the
    * game (if no draws, only one will be returned)
    * @return Player names
    */
  def highestScorePlayers: Array[String] =
    for (plnum <- scoretab.highestScorePlNums) yield players.playerName(plnum)

  /**
    * Score the previous throw in given combination
    * or add a zero score to the combination
    *
    * @param comb    Combination to score
    * @param setZero Whether the previous throw should be ignored
    *                and score set to zero
    */
  def score(comb: String, setZero: Boolean): Unit =
    scoretab.score(players.currentPlNum, comb, ds.prevThrow, setZero)

  /**
    * Show the score table
    *
    * @return Current score table
    */
  def showScoreTable: String = scoretab.showScoreTable(players.players)

  /**
    * Check whether the sought after scoring combination exists
    *
    * @param comb The name of the combination to look for
    * @return True if combination exists
    */
  def hasCombination(comb: String): Boolean = scoretab.hasCombination(comb)

  // ============================================

  /**
    * Move on to next player turn
    * @return True, if there is a next player turn
    */
  def nextPlayerTurn(): Boolean = {
    ds.initialThrowDone = false
    players.switchCurrentPlayer()
    players.addThrows(3)
    scoretab.scoreNoBonusCount < players.playerCount*scoretab.combinationCount
  }

}