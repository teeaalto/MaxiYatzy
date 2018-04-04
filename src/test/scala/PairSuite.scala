import org.scalatest.FunSuite
import maxiYatzy.scores.Pairs.score

class PairSuite extends FunSuite {
  test("A simple pair should score the sum of numbers") {
    assert(score(Array(1,2,3,4,3,6)) == 6)
  }

  test("Position of the pair should be irrelevant") {
    assert(score(Array(2,3,5,4,6,2)) == 4)
    assert(score(Array(6,6,3,5,4,1)) == 12)
    assert(score(Array(2,3,5,4,1,1)) == 2)
    assert(score(Array(6,5,3,6,4,1)) == 12)
    assert(score(Array(1,3,5,4,6,4)) == 8)
  }

  test("With no pairs, score should be zero") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(6,3,5,1,2,4)) == 0)
  }

  test("With multiple pairs, the greater one should be scored") {
    assert(score(Array(6,2,5,4,5,6)) == 12)
    assert(score(Array(3,2,3,4,5,2)) == 6)
  }

  test("Having an excessive number of given number shouldn't matter") {
    assert(score(Array(2,3,2,2,1,4)) == 4)
    assert(score(Array(2,3,2,2,3,4)) == 6)
    assert(score(Array(4,4,4,4,4,4)) == 8)
    assert(score(Array(5,4,5,4,4,5)) == 10)
    assert(score(Array(3,3,5,4,3,3)) == 6)
  }
}
