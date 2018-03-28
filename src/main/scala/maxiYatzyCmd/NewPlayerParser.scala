package maxiYatzyCmd

class NewPlayerParser extends InputParser {
  def parse(str: String, prevParser: InputParser) = {
    ("Pian osaan parsia pelaajanlisäyksen!", new NewPlayerParser)
  }
}
