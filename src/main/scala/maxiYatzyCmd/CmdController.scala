package maxiYatzyCmd

import maxiYatzy.MaxiYatzy

class CmdController {
  private val myatzy = new MaxiYatzy
  val initialStatus: String =
    """|MaxiYatzy version n.n
       |Made by Tuomas Aalto
       |*********************
       |Commands:
       |'info' for instructions
       |'rules' for the rules of this MaxiYatzy
       |'scores' show score table
       |'quit' if you wish to leave the game
       |*********************
       |""".stripMargin

  private var currentParser = parseNewPlayer _
  var request: String = "Give player name or 'ok' to start playing"

  private def parseNewPlayer(str: String): String = {
    if (str == "ok") {
      currentParser = parseThrowScore
      request = "Throw or score"
      "All players have been added, starting the game..."
    }
    else {
      myatzy.addPlayer(str)
      "Player " + str + " added"
    }
  }

  private def parseThrowScore(str: String): String = {
    if (str == "throw") "Pian osaan käsitellä heittoja!"
    else if (str == "score") "Pian osaan käsitellä pisteitä!"
    else {
      "Pian osaan käsitellä tämänkin!"
    }
  }

  def parse(str: String): String = currentParser(str)
}
