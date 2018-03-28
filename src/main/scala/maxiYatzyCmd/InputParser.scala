package maxiYatzyCmd

abstract class InputParser {
  def parse(str: String, prevParser: InputParser): (String, InputParser)
}
