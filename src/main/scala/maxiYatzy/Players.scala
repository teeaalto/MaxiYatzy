package maxiYatzy

import java.util.NoSuchElementException

import scala.collection.mutable.HashMap

class Players {
  private val players = HashMap.empty[Int, String]
  private var nextPNumber = 1
  private var currentPlayer = 1

  def addPlayer(name: String) = {
    players += (nextPNumber -> name)
    nextPNumber += 1
  }

  def playerName(num: Int): String = {
    try {
      players(num)
    } catch {
      case ex: NoSuchElementException => "unknown player"
    }
  }

  def switchCurrentPlayer(): Unit = {
    currentPlayer += 1
    if (!players.contains(currentPlayer)) currentPlayer = 1
  }

  def currentPlName = players(currentPlayer)
}