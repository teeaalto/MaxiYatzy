package maxiYatzyCmd

import scala.annotation.tailrec

object MaxiYatzyCmd {
  def main(args: Array[String]) = {

    val ctrl = new CmdController

    @tailrec
    def playTheGame(message: String): Unit = {
      println(message)
      print(">")
      val input = scala.io.StdIn.readLine()
      if (input == "quit") println("Exiting MaxiYatzy...")
      else {
        val nextMessage = ctrl.parse(input)
        playTheGame(nextMessage)
      }
    }

    playTheGame(ctrl.initialMessage)
  }
}
