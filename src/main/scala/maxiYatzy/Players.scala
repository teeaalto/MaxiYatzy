package maxiYatzy

import scala.collection.mutable.ArrayBuffer

class Players {
  val players = new ArrayBuffer[String]

  def addPlayer(name: String) = {
    players += name
    println("Lisäsin pelaajan!")
    println(players.toString())
  }
}