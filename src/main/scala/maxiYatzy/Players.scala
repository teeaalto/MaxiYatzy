package maxiYatzy

import java.util.NoSuchElementException

import scala.collection.mutable.HashMap

/**
  * A collection of players
  */
class Players {
  private val players = HashMap.empty[Int, Player]
  private var nextPNumber = 1
  private var currentPlayer = 1

  /**
    * Add a player
    * @param name The name of the player
    */
  def addPlayer(name: String) = {
    players += (nextPNumber -> new Player(name))
    nextPNumber += 1
  }

  /**
    * Get the player name
    * @param num The number of the player
    * @return The name of the player
    */
  def playerName(num: Int): String = {
    try {
      players(num).name
    } catch {
      case ex: NoSuchElementException => "unknown player"
    }
  }

  /**
    * Switch to the next player as the current player
    */
  def switchCurrentPlayer(): Unit = {
    currentPlayer += 1
    if (!players.contains(currentPlayer)) currentPlayer = 1
  }

  /**
    * Get the name of the current player
    * @return The current player name
    */
  def currentPlName: String = players(currentPlayer).name

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
    players(currentPlayer).throws
  }

  /**
    * Add throws to the current player
    * @param n Number of throws to add
    */
  def addThrows(n: Int): Unit = {
    players(currentPlayer).addThrows(n)
  }

  /**
    * Use one of the current player's throws.
    * Throws IllegalStateException if this would
    * result in a negative number of throws.
    */
  def useThrow(): Unit = {
    players(currentPlayer).useThrow()
  }
}