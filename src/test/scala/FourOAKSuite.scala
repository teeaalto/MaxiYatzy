import org.scalatest.FunSuite
import maxiYatzy.scores.FourOfAKind.score

class FourOAKSuite extends FunSuite {
  test("A simple four of a kind should score the sum of numbers") {
    assert(score(Array(1,3,3,4,3,3)) == 12)
  }

  test("Position of the quadruplet should be irrelevant") {
    assert(score(Array(2,3,2,2,6,2)) == 8)
    assert(score(Array(6,6,6,6,4,1)) == 24)
    assert(score(Array(2,3,1,1,1,1)) == 4)
    assert(score(Array(6,5,6,6,6,1)) == 24)
    assert(score(Array(1,4,4,4,6,4)) == 16)
  }

  test("With no quadruplets, score should be zero") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(6,3,5,1,2,4)) == 0)
    assert(score(Array(6,3,5,5,2,4)) == 0)
    assert(score(Array(6,6,5,5,2,2)) == 0)
    assert(score(Array(6,6,5,5,5,2)) == 0)
    assert(score(Array(1,6,5,1,5,1)) == 0)
    assert(score(Array(2,2,2,3,3,3)) == 0)
  }

  test("Having an excessive number of given number shouldn't matter") {
    assert(score(Array(2,2,2,2,2,4)) == 8)
    assert(score(Array(2,3,2,2,2,2)) == 8)
    assert(score(Array(4,4,4,4,4,4)) == 16)
    assert(score(Array(3,3,5,3,3,3)) == 12)
  }
}
