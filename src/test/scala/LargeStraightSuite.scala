import org.scalatest.FunSuite
import maxiYatzy.scores.LargeStraight.score

class LargeStraightSuite extends FunSuite {
  test("Having dices 2-6 scores large straight") {
    assert(score(Array(1,2,3,4,5,6)) == 20)
  }

  test("Ordering of the dices shouldn't matter") {
    assert(score(Array(6,2,1,5,4,3)) == 20)
    assert(score(Array(3,5,4,3,2,6)) == 20)
    assert(score(Array(6,5,6,4,3,2)) == 20)
    assert(score(Array(5,5,6,3,4,2)) == 20)
  }

  test("Not having a large straight scores zero points") {
    assert(score(Array(1,1,1,1,1,1)) == 0)
    assert(score(Array(3,2,3,3,3,3)) == 0)
    assert(score(Array(4,4,1,3,3,1)) == 0)
    assert(score(Array(2,2,3,4,5,1)) == 0)
    assert(score(Array(6,5,6,5,5,3)) == 0)
  }
}
