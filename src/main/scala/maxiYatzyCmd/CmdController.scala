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
      myatzy.addThrows(3)
      updateThrowRequest()
      "All players have been added, starting the game..."
    }
    else {
      myatzy.addPlayer(str)
      "Player " + str + " added"
    }
  }

  private def parseThrow(dices: String): String = {
    if (dices == "all") {
      val diceResult = myatzy.throwAll()
      myatzy.useThrow()
      updateThrowRequest()

      val res = {
        for (i <- 1 to 6) yield {
          s"Dice $i: " + diceResult(i)
        }
      }
      res.mkString("\n")
    }
    else {
      val dicesSplit = dices.split(",")
      val dicesArray = for (d <- dicesSplit) yield {
        d.trim.toInt
      }
      if (dicesArray.exists(num => (num < 1 || num > 6)))
        "Specify the dices to throw as a comma separated sequence, e.g. 'throw 2,4,5'"
      else {
        val diceResult = myatzy.throwGiven(dicesArray)
        myatzy.useThrow()
        updateThrowRequest()

        val res = {
          for (i <- 1 to 6) yield {
            s"Dice $i: " + diceResult(i)
          }
        }
        res.mkString("\n")
      }
    }
  }

  private def parseThrowScore(str: String): String = {
    val strSplit = str.split(" ")

    strSplit(0).toLowerCase match {
      case "throw" => {
        if (myatzy.playersThrows < 1) "No throws left."
        else if (strSplit.length >= 2) parseThrow(strSplit(1))
        else "Specify which dices to throw"
      }
      case "score" => "Soon I know how to handle scores!"
      case _ => s"Unknown command '$str'"
    }
  }

  def parse(str: String): String = currentParser(str)

  private def updateThrowRequest(): Unit = {
    request = myatzy.currentPlName + "'s turn. Throw or score. Throws left: " + myatzy.playersThrows()
  }
}
