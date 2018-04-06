import maxiYatzy.scores.FullStraight.score
import org.scalatest.FunSuite

class FullStraightSuite extends FunSuite {
  test("Having dices 1-6 scores full straight") {
    assert(score(Array(1,2,3,4,5,6)) == 21)
  }

  test("Ordering of the dices shouldn't matter") {
    assert(score(Array(6,2,1,5,4,3)) == 21)
    assert(score(Array(6,5,4,3,2,1)) == 21)
  }

  test("Not having a full straight scores zero points") {
    assert(score(Array(6,5,6,4,3,2)) == 0)
    assert(score(Array(5,5,6,3,4,2)) == 0)
    assert(score(Array(1,1,1,1,1,1)) == 0)
    assert(score(Array(3,2,3,3,3,3)) == 0)
    assert(score(Array(4,4,1,3,3,1)) == 0)
    assert(score(Array(2,2,3,4,5,1)) == 0)
    assert(score(Array(6,5,6,5,5,3)) == 0)
    assert(score(Array(1,5,1,2,4,3)) == 0)
  }
}
