package maxiYatzy

import java.util.NoSuchElementException

import scala.collection.mutable.HashMap

/**
  * A collection of players
  */
class Players {
  private val players_ = HashMap.empty[Int, Player]
  private var nextPNumber = 1
  private var currentPlayer = 1
  private var playerAdded_ = false

  /**
    * Add a player
    * @param name The name of the player
    */
  def addPlayer(name: String) = {
    players_ += (nextPNumber -> new Player(name))
    nextPNumber += 1
    playerAdded_ = true
  }

  /**
    * Indicates whether at least one player has been
    * added to the game
    * @return true if there is a player in the game
    */
  def playerAdded = playerAdded_

  /**
    * Get the player name
    * @param num The number of the player
    * @return The name of the player
    */
  def playerName(num: Int): String = {
    try {
      players_(num).name
    } catch {
      case ex: NoSuchElementException => "unknown player"
    }
  }

  /**
    * Get all player names and corresponding player numbers
    * @return Player numbers and names
    */
  def players: Map[Int,String] =
    (for ((k,v) <- players_) yield (k,v.name)).toMap[Int, String]

  /**
    * Switch to the next player as the current player
    */
  def switchCurrentPlayer(): Unit = {
    currentPlayer += 1
    if (!players_.contains(currentPlayer)) currentPlayer = 1
  }

  /**
    * Get the name of the current player
    * @return The current player name
    */
  def currentPlName: String = players_(currentPlayer).name

  /**
    * Get the number of the current player
    * @return The number of the current player
    */
  def currentPlNum: Int = currentPlayer

  /**
    * Get the number of throws the current player has
    * @return Number of throws
    */
  def playersThrows: Int = {
    players_(currentPlayer).throws
  }

  /**
    * Add throws to the current player
    * @param n Number of throws to add
    */
  def addThrows(n: Int): Unit = {
    players_(currentPlayer).addThrows(n)
  }

  /**
    * Use one of the current player's throws.
    * Throws IllegalStateException if this would
    * result in a negative number of throws.
    */
  def useThrow(): Unit = {
    players_(currentPlayer).useThrow()
  }
}