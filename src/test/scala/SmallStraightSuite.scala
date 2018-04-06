import org.scalatest.FunSuite
import maxiYatzy.scores.SmallStraight.score

class SmallStraightSuite extends FunSuite {
  test("Having dices 1-5 scores small straight") {
    assert(score(Array(1,2,3,4,5,6)) == 15)
  }

  test("Ordering of the dices shouldn't matter") {
    assert(score(Array(6,2,1,5,4,3)) == 15)
    assert(score(Array(3,5,4,3,2,1)) == 15)
    assert(score(Array(1,5,1,4,3,2)) == 15)
    assert(score(Array(5,5,1,3,4,2)) == 15)
  }

  test("Not having a small straight scores zero points") {
    assert(score(Array(1,1,1,1,1,1)) == 0)
    assert(score(Array(3,2,3,3,3,3)) == 0)
    assert(score(Array(4,4,1,3,3,1)) == 0)
    assert(score(Array(2,2,3,4,5,6)) == 0)
    assert(score(Array(6,5,6,5,5,3)) == 0)
  }
}
