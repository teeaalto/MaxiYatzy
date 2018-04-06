import org.scalatest.FunSuite
import maxiYatzy.scores.TwoPairs.score

class TwoPairsSuite extends FunSuite {
  test("Having two pairs scores the sum of numbers") {
    assert(score(Array(2,3,2,3,5,6)) == 10)
  }

  test("Placing of the dices shouldn't matter") {
    assert(score(Array(4,4,2,3,1,1)) == 10)
    assert(score(Array(5,4,6,6,1,5)) == 22)
    assert(score(Array(3,4,2,6,3,6)) == 18)
  }

  test("If there are multiple choices, the highest should be scored") {
    assert(score(Array(1,4,1,4,2,2)) == 12)
    assert(score(Array(1,3,6,6,1,3)) == 18)
    assert(score(Array(4,4,5,6,5,6)) == 22)
  }

  test("Having an excessive number of the same value shouldn't matter") {
    assert(score(Array(1,4,1,4,1,4)) == 10)
    assert(score(Array(1,3,6,6,6,3)) == 18)
    assert(score(Array(4,2,2,2,4,2)) == 12)
    assert(score(Array(1,1,3,6,3,3)) == 8)
  }

  test("Not having two pairs scores zero points") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(1,3,6,6,4,5)) == 0)
    assert(score(Array(5,2,2,2,1,6)) == 0)
    assert(score(Array(1,6,3,4,2,3)) == 0)
  }

  test("Having four (or more) of a kind doesn't count as two pairs") {
    assert(score(Array(1,1,1,1,5,6)) == 0)
    assert(score(Array(2,3,2,2,2,2)) == 0)
    assert(score(Array(4,4,4,4,4,4)) == 0)
  }
}
