package maxiYatzyCmd

import maxiYatzy.MaxiYatzy

class NewPlayerParser extends InputParser {
  def parse(str: String, prevParser: InputParser) = {
    if (str == "ok") ("Pian osaan lopettaa pelaajanlisäyksen!", new NewPlayerParser)
    else {
      MaxiYatzy.addPlayer(str)
      ("Pian osaan lopettaa pelaajanlisäyksen!", new NewPlayerParser)
    }

  }
}
