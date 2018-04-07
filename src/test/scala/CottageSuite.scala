import maxiYatzy.scores.Cottage.score
import org.scalatest.FunSuite

class CottageSuite extends FunSuite {
  test("Having 3 of a kind and a pair scores the sum of dices") {
    assert(score(Array(2,2,4,2,4,5)) == 14)
  }

  test("Ordering of the dices shouldn't matter") {
    assert(score(Array(1,6,5,6,5,6)) == 28)
    assert(score(Array(4,4,4,5,3,3)) == 18)
    assert(score(Array(4,4,2,1,1,1)) == 11)
  }

  test("A house is always a cottage; the larger option should be scored") {
    assert(score(Array(1,1,1,2,2,2)) == 8)
    assert(score(Array(4,4,5,5,5,4)) == 23)
    assert(score(Array(6,6,6,2,2,2)) == 22)
    assert(score(Array(4,5,4,5,4,5)) == 23)
  }

  test("A tower is always a cottage") {
    assert(score(Array(1,1,1,1,2,2)) == 7)
    assert(score(Array(4,4,5,5,5,5)) == 23)
    assert(score(Array(3,3,3,3,2,2)) == 13)
    assert(score(Array(4,6,4,4,4,6)) == 24)
  }

  test("Not having three of a kind scores zero points") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(6,5,3,4,2,6)) == 0)
    assert(score(Array(1,1,3,4,3,4)) == 0)
    assert(score(Array(1,1,6,4,3,4)) == 0)
  }

  test("Having three of a kind without a pair scores zero points") {
    assert(score(Array(1,1,3,4,5,1)) == 0)
    assert(score(Array(1,2,3,3,3,6)) == 0)
    assert(score(Array(1,3,3,3,3,6)) == 0)
  }

  test("Having five or six of a kind doesn't count as a cottage") {
    assert(score(Array(1,1,1,1,1,2)) == 0)
    assert(score(Array(3,3,5,3,3,3)) == 0)
    assert(score(Array(5,5,5,5,5,5)) == 0)
  }
}
