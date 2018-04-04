package maxiYatzy

import java.util.NoSuchElementException

import scala.collection.mutable.HashMap

class Players {
  private val players = HashMap.empty[Int, Player]
  private var nextPNumber = 1
  private var currentPlayer = 1

  def addPlayer(name: String) = {
    players += (nextPNumber -> new Player(name))
    nextPNumber += 1
  }

  def playerName(num: Int): String = {
    try {
      players(num).name
    } catch {
      case ex: NoSuchElementException => "unknown player"
    }
  }

  def switchCurrentPlayer(): Unit = {
    currentPlayer += 1
    if (!players.contains(currentPlayer)) currentPlayer = 1
  }

  def currentPlName(): String = players(currentPlayer).name

  def playersThrows(): Int = {
    players(currentPlayer).throws
  }

  def addThrows(n: Int): Unit = {
    players(currentPlayer).addThrows(n)
  }

  def useThrow(): Unit = {
    players(currentPlayer).useThrow()
  }
}