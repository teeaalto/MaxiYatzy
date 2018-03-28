package maxiYatzyCmd

object MaxiYatzyCmd {
  def main(args: Array[String]) = {

    def playTheGame(message: String, parser: InputParser): Unit = {
      println(message)
      print(">")
      val input = scala.io.StdIn.readLine()
      if (input == "quit") println("Exiting MaxiYatzy...")
      else {
        val (nextMessage, nextParser) = parser.parse(input, parser)
        playTheGame(nextMessage, nextParser)
      }
    }

    playTheGame("Aloitusviesti tähän", new NewPlayerParser)
  }
}
