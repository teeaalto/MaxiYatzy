import org.scalatest.FunSuite
import maxiYatzy.scores.ThreeOfAKind.score

class TOAKSuite extends FunSuite {
  test("A simple triplet should score the sum of numbers") {
    assert(score(Array(1,3,3,4,3,6)) == 9)
  }

  test("Position of the triplet should be irrelevant") {
    assert(score(Array(2,3,2,4,6,2)) == 6)
    assert(score(Array(6,6,6,5,4,1)) == 18)
    assert(score(Array(2,3,5,1,1,1)) == 3)
    assert(score(Array(6,5,6,6,4,1)) == 18)
    assert(score(Array(1,3,4,4,6,4)) == 12)
  }

  test("With no triplets, score should be zero") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(6,3,5,1,2,4)) == 0)
    assert(score(Array(6,3,5,5,2,4)) == 0)
    assert(score(Array(6,6,5,5,2,2)) == 0)
  }

  test("With multiple pairs, the greater one should be scored") {
    assert(score(Array(6,5,5,6,5,6)) == 18)
    assert(score(Array(3,2,3,3,2,2)) == 9)
  }

  test("Having an excessive number of given number shouldn't matter") {
    assert(score(Array(2,2,2,2,1,4)) == 6)
    assert(score(Array(2,3,2,2,3,2)) == 6)
    assert(score(Array(4,4,4,4,4,4)) == 12)
    assert(score(Array(3,3,5,3,3,3)) == 9)
  }
}
