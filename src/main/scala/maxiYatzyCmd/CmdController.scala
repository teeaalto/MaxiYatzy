package maxiYatzyCmd

import maxiYatzy.MaxiYatzy

class CmdController {
  private val myatzy = new MaxiYatzy
  val initialMessage: String = "Alkuun tämä viestinpätkyläinen."
  private var currentParser = parseNewPlayer _

  private def parseNewPlayer(str: String): String = {
    if (str == "ok") {
      currentParser = parseThrowScore
      "Siirrytään heittojen parsintaan!"
    }
    else {
      myatzy.addPlayer(str)
      "Lisäsin pelaajan!"
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
