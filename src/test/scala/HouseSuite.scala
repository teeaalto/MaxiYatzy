import maxiYatzy.scores.House.score
import org.scalatest.FunSuite

class HouseSuite extends FunSuite {
  test("Having 3 of a kind x2 scores the sum of dices") {
    assert(score(Array(2,2,4,2,4,4)) == 18)
  }

  test("Ordering of the dices shouldn't matter") {
    assert(score(Array(1,1,1,2,2,2)) == 9)
    assert(score(Array(4,5,5,5,4,4)) == 27)
    assert(score(Array(6,5,6,5,6,5)) == 33)
  }

  test("Not having a house scores zero points") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(4,5,5,3,6,2)) == 0)
    assert(score(Array(6,5,6,5,4,2)) == 0)
    assert(score(Array(6,5,6,5,5,2)) == 0)
    assert(score(Array(2,2,1,3,1,3)) == 0)
    assert(score(Array(5,5,2,2,2,2)) == 0)
  }

  test("Having six of a kind doesn't count as a house") {
    assert(score(Array(1,1,1,1,1,1)) == 0)
    assert(score(Array(5,5,5,5,5,5)) == 0)
  }
}
