package maxiYatzyCmd

import maxiYatzy.MaxiYatzy

/**
  * A controller for the command line interface
  * of the game
  */
class CmdController {
  private var myatzy = new MaxiYatzy
  val initialStatus: String =
    """|MaxiYatzy version n.n
       |Made by Tuomas Aalto
       |*********************
       |Commands:
       |'help' for help
       |'rules' for the rules of this MaxiYatzy
       |'scores' show score table
       |'quit' if you wish to leave the game
       |*********************
       |""".stripMargin

  private val rules: String =
    """|MaxiYatzy is a dice game for one to 10 players.
       |In the game, players take turns to throw six
       |dices. The goal is to gain points by scoring
       |different combinations of dices. A combination
       |can be scored even if the criteria of the
       |combination are not met to yield zero points.
       |Once all players have scored all of their
       |combinations, the game ends and the player
       |with most points is the winner.
       |
       |At the start of each turn, player receives
       |three throws to use. On a throw, the player
       |can choose to throw all dices or only some of
       |them. On the first throw of the turn all dices
       |must be thrown. Unused throws can be spared to
       |next turns.
       |
       |The dice combinations:
       |pair              - two dices with the same number
       |two pairs         - two different pairs of dices
       |three pairs       - three different pairs of dices
       |three of a kind   - three dices with the same number
       |four of a kind    - four dices with the same number
       |five of a kind    - five dices with the same number
       |small straight    - all dice values from 1 to 5
       |large straight    - all dice values from 2 to 6
       |full straight     - all dice values from 1 to 6
       |cottage           - a pair and three of a kind with
       |                    different numbers
       |house             - two three of a kinds with
       |                    different numbers
       |tower             - a pair and four of a kind with
       |                    different numbers
       |chance            - any combination of dices
       |yatzy             - six dices with the same number
       |All combinations yield points equal to the sum
       |of the dice values, e.g. three fours in three
       |of a kind gives 3 x 4 = 12 points. An exception
       |to this rule is yatzy, which always scores 50 points.
       |
       |In addition there is a dedicated combination
       |for each of the dice values, called ones, twos etc..
       |Any number of given dice value can be
       |scored in respective combination, eg.
       |five twos giving ten points. If the score sum
       |in combinations from ones to sixes is or
       |exceeds 84, the player receives 50 points extra.
       |The number 84 can be gained by having four
       |of a kind in all combinations from ones to
       |sixes, and hence the score table displays
       |scores as differences from four of a kind.
       |The previous example of five twos would
       |therefore be shown as +2, three fives
       |would be -5 and so forth.
    """.stripMargin

  private val help =
    """|General commands:
       |'help' for help
       |'rules' for the rules of this MaxiYatzy
       |'scores' show score table
       |'quit' if you wish to leave the game
       |
       |Adding players:
       |'ok' to end adding players
       |any other string of characters is interpreted
       |as a player name
       |
       |Throwing and scoring:
       |'throw all' to throw all dices
       |'throw 1,3' to throw dices 1 and 3
       |'score pair' to score pair
    """.stripMargin

  private var currentParser = parseNewPlayer _
  private val newPlayerReq = "Give player name or 'ok' to start playing"
  var request: String = newPlayerReq
  private var currentCombination = ""

  private def parseNewPlayer(str: String): String = {
    if (str.toLowerCase.trim == "ok") {
      if (myatzy.playerAdded) {
        currentParser = parseThrowScore
        myatzy.addThrows(3)
        updateThrowRequest()
        myatzy.showScoreTable
      }
      else "Add at least one player"
    }
    else {
      myatzy.addPlayer(str)
      "Player " + str + " added"
    }
  }

  private def parseThrow(dices: String): String = {
    if (dices == "all") {
      myatzy.mkInitialThrow()
      myatzy.throwAll()
      myatzy.useThrow()
      updateThrowRequest()
      myatzy.showDices
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
            myatzy.showDices
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
        val message = myatzy.showScoreTable // "Points set for " + myatzy.currentPlName()
        val hasNextTurn = myatzy.nextPlayerTurn()
        if (hasNextTurn) {
          currentCombination = ""
          currentParser = parseThrowScore
          updateThrowRequest()
          message
        }
        else {
          request = "Start a new game (y/n)?"
          currentParser = endGameParser
          val winners = myatzy.highestScorePlayers
          if (winners.length <= 1) winners(0) + " wins the game!"
          else
            "It's a draw between " +
                winners.init.mkString(", ") +
                " and " +
                winners.last + "!"
        }
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

  private def endGameParser(str: String): String = {
    str match {
      case "y" => {
        myatzy = new MaxiYatzy
        currentParser = parseNewPlayer
        request = newPlayerReq
        currentCombination = ""
        "Starting a new game..."
      }
      case _  => "Tell me if you change your mind."
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
      case "rules" => rules
      case "help" => help
      case _ => currentParser(str)
    }
  }

  private def updateThrowRequest(): Unit = {
    request = myatzy.currentPlName + "'s turn. Throw or score. Throws left: " + myatzy.playersThrows
  }
}
