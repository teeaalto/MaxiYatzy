package maxiYatzyCmd

import scala.annotation.tailrec

/**
  * The main program of the command line MaxiYatzy
  *
  * @author Tuomas Aalto
  */
object MaxiYatzyCmd {
  def main(args: Array[String]) = {

    val ctrl = new CmdController

    /**
      * A recursive function that makes the game play
      * @param status The status of the game
      * @param request The current request for user input
      */
    @tailrec
    def playTheGame(status: String, request: String): Unit = {
      println(status)
      println(request)
      print(">")
      val input = scala.io.StdIn.readLine()
      if (input.trim.toLowerCase == "quit") println("Exiting MaxiYatzy...")
      else {
        val nextStatus = ctrl.parse(input)
        val nextRequest = ctrl.request
        playTheGame(nextStatus, nextRequest)
      }
    }

    playTheGame(ctrl.initialStatus, ctrl.request)
  }
}
