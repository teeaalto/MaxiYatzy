package maxiYatzyCmd

import scala.annotation.tailrec

object MaxiYatzyCmd {
  def main(args: Array[String]) = {

    val ctrl = new CmdController

    @tailrec
    def playTheGame(status: String, request: String): Unit = {
      println(status)
      println(request)
      print(">")
      val input = scala.io.StdIn.readLine()
      if (input == "quit") println("Exiting MaxiYatzy...")
      else {
        val nextStatus = ctrl.parse(input)
        val nextRequest = ctrl.request
        playTheGame(nextStatus, nextRequest)
      }
    }

    playTheGame(ctrl.initialStatus, ctrl.request)
  }
}
