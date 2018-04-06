import org.scalatest.FunSuite
import maxiYatzy.scores.ThreePairs.score

class ThreePairsSuite extends FunSuite {
  test("Having three pairs scores the sum of numbers") {
    assert(score(Array(2,3,2,3,5,5)) == 20)
  }

  test("Placing of the dices shouldn't matter") {
    assert(score(Array(4,4,2,2,1,1)) == 14)
    assert(score(Array(5,4,6,6,4,5)) == 30)
    assert(score(Array(2,4,2,4,5,5)) == 22)
  }


  test("Not having three pairs scores zero points") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(1,3,6,6,4,5)) == 0)
    assert(score(Array(5,2,2,2,6,6)) == 0)
    assert(score(Array(1,1,1,1,4,4)) == 0)
    assert(score(Array(6,2,2,2,6,6)) == 0)
    assert(score(Array(5,2,2,2,2,6)) == 0)
    assert(score(Array(4,4,2,1,6,6)) == 0)
    assert(score(Array(3,3,3,3,3,6)) == 0)
  }

  test("Having six of a kind doesn't count as three pairs") {
    assert(score(Array(1,1,1,1,1,1)) == 0)
    assert(score(Array(5,5,5,5,5,5)) == 0)
  }
}
