import org.scalatest.FunSuite
import maxiYatzy.scores.FiveOfAKind.score

class FiveOAKSuite extends FunSuite {
  test("A simple five of a kind should score the sum of numbers") {
    assert(score(Array(1,3,3,3,3,3)) == 15)
  }

  test("Position of the quintet should be irrelevant") {
    assert(score(Array(2,2,2,2,2,1)) == 10)
    assert(score(Array(6,6,6,6,4,6)) == 30)
    assert(score(Array(2,1,1,1,1,1)) == 5)
    assert(score(Array(4,6,4,4,4,4)) == 20)
  }

  test("With no quintets, score should be zero") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(6,3,5,1,2,4)) == 0)
    assert(score(Array(6,3,5,5,2,4)) == 0)
    assert(score(Array(6,6,5,5,2,2)) == 0)
    assert(score(Array(6,6,5,5,5,2)) == 0)
    assert(score(Array(1,6,5,1,5,1)) == 0)
    assert(score(Array(2,2,2,3,3,3)) == 0)
    assert(score(Array(2,2,2,2,3,3)) == 0)
    assert(score(Array(3,2,2,3,3,3)) == 0)
    assert(score(Array(5,2,5,5,3,5)) == 0)
    assert(score(Array(2,2,6,6,6,6)) == 0)
  }

  test("Having an excessive number of given number shouldn't matter") {
    assert(score(Array(2,2,2,2,2,2)) == 10)
    assert(score(Array(3,3,3,3,3,3)) == 15)
    assert(score(Array(4,4,4,4,4,4)) == 20)
  }
}
