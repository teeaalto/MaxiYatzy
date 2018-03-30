package maxiYatzyCmd

import maxiYatzy.MaxiYatzy

class CmdController {
  private abstract class InputParser {
    def parse(str: String): (String, InputParser)
  }

  private class NewPlayerParser(myatzy: MaxiYatzy) extends InputParser {
    var tsparser: InputParser = null

    def setTSParser(tsp: InputParser) = { tsparser = tsp }

    def parse(str: String): (String, InputParser) = {
      if (str == "ok") ("Siirrytään heittojen parsintaan!", tsparser)
      else {
        myatzy.addPlayer(str)
        ("Pian osaan lopettaa pelaajanlisäyksen!", this)
      }
    }
  }

  private class ThrowScoreParser(myatzy: MaxiYatzy) extends InputParser {
    def parse(str: String): (String, InputParser) = {
      if (str == "throw") ("Pian osaan käsitellä heittoja!", this)
      else if (str == "score") ("Pian osaan käsitellä pisteitä!", this)
      else {
        ("Pian osaan käsitellä tämänkin!", this)
      }
    }
  }

  private val myatzy = new MaxiYatzy
  private val tsparser = new ThrowScoreParser(myatzy)
  private val npparser = new NewPlayerParser(myatzy)

  val initialMessage: String = "Alkuun tämä viestinpätkyläinen."
  private var currentParser: InputParser = npparser

  def parse(str: String): String = {
    val (response, nextParser) = currentParser.parse(str)
    currentParser = nextParser
    response
  }

  npparser.setTSParser(tsparser)
}
