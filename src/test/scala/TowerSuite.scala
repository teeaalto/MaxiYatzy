import maxiYatzy.scores.Tower.score
import org.scalatest.FunSuite

class TowerSuite extends FunSuite {
  test("Having a pair and a four of a kind scores the sum of dice values") {
    assert(score(Array(2,2,4,4,4,4)) == 20)
  }

  test("Ordering of the dices shouldn't matter") {
    assert(score(Array(1,6,6,6,6,1)) == 26)
    assert(score(Array(4,5,4,5,4,4)) == 26)
    assert(score(Array(2,2,6,6,2,2)) == 20)
  }

  test("Not having four of a kind scores zero points") {
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(4,5,5,3,6,2)) == 0)
    assert(score(Array(6,5,6,5,4,2)) == 0)
    assert(score(Array(6,5,6,5,5,2)) == 0)
    assert(score(Array(2,2,1,3,1,3)) == 0)
  }

  test("Four of a kind without a pair scores zero points") {
    assert(score(Array(1,1,3,1,5,1)) == 0)
    assert(score(Array(2,4,2,3,2,2)) == 0)
    assert(score(Array(6,6,6,5,6,6)) == 0)
  }

  test("Having six of a kind doesn't count as a tower") {
    assert(score(Array(1,1,1,1,1,1)) == 0)
    assert(score(Array(5,5,5,5,5,5)) == 0)
  }
}
