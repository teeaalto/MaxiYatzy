package maxiYatzyCmd

import maxiYatzy.MaxiYatzy

/**
  * A controller for the command line interface
  * of the game
  */
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
  private var currentCombination = ""
  private var playerAdded = false
  private var initialThrowDone = false

  private def parseNewPlayer(str: String): String = {
    if (str.toLowerCase.trim == "ok") {
      if (playerAdded) {
        currentParser = parseThrowScore
        myatzy.addThrows(3)
        updateThrowRequest()
        "All players have been added, starting the game..."
      }
      else "Add at least one player"
    }
    else {
      myatzy.addPlayer(str)
      playerAdded = true
      "Player " + str + " added"
    }
  }

  private def parseThrow(dices: String): String = {
    if (dices == "all") {
      myatzy.mkInitialThrow()
      val diceResult = myatzy.throwAll()
      myatzy.useThrow()
      updateThrowRequest()

      val res = {
        for (i <- 1 to 6) yield {
          s"Dice $i: " + diceResult(i-1)
        }
      }
      res.mkString("\n")
    }
    else {
      if (!myatzy.initialThrowDone) "Make an initial throw with 'throw all'"
      else {
        try {
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
                s"Dice $i: " + diceResult(i - 1)
              }
            }
            res.mkString("\n")
          }
        } catch {
          case _: NumberFormatException => "Specify the dices to throw as either 'all' or e.g. '2,4,5'"
        }
      }
    }
  }


  private def parseScore(comb: String): String = {
    if (myatzy.hasCombination(comb)) {
      myatzy.checkScore(comb) match {
        case Some(initpnts) => {
          val pnts = if (myatzy.initialThrowDone) initpnts else 0
          currentCombination = comb
          currentParser = parseScoreConfirmation
          request = s"Score $pnts points in $comb (y/n)?"
          "Checking points..."
        }
        case None => myatzy.currentPlName + s" has already scored $comb"
      }
    }
    else s"Unknown combination '$comb'"
  }


  private def parseThrowScore(str: String): String = {
    val strSplit = str.toLowerCase.split(" ")

    strSplit(0) match {
      case "throw" => {
        if (myatzy.playersThrows < 1) "No throws left."
        else if (strSplit.length >= 2) parseThrow(strSplit.tail.mkString)
        else "Specify which dices to throw"
      }
      case "score" => {
        if (strSplit.length < 2) "Specify in which combination points should be scored"
        else parseScore(strSplit.tail.mkString(" "))
      }
      case _ => s"Unknown command '$str'"
    }
  }


  private def parseScoreConfirmation(resp: String): String = {
    resp match {
      case "y" => {
        myatzy.score(currentCombination, !myatzy.initialThrowDone)
        val message = "Points set for " + myatzy.currentPlName()
        val hasNextTurn = myatzy.nextPlayerTurn()
        if (hasNextTurn) {
          currentCombination = ""
          currentParser = parseThrowScore
          updateThrowRequest()
          message
        }
        else "Pelin pitäs päättyä tähän?"
      }
      case "n" => {
        currentParser = parseThrowScore
        currentCombination = ""
        updateThrowRequest()
        "No points set."
      }
      case _ => s"Reply either 'y' or 'n'"
    }
  }


  /**
    * Parse user input
    * @param str The input to parse
    * @return The response to the input
    */
  def parse(str: String): String = {
    str.trim.toLowerCase match {
      case "scores" => myatzy.showScoreTable
      case _ => currentParser(str)
    }
  }

  private def updateThrowRequest(): Unit = {
    request = myatzy.currentPlName + "'s turn. Throw or score. Throws left: " + myatzy.playersThrows()
  }
}
