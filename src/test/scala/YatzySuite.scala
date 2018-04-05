import org.scalatest.FunSuite
import maxiYatzy.scores.Yatzy.score

class YatzySuite extends FunSuite {
  test("Having same number on all dices results in a yatzy") {
    assert(score(Array(1,1,1,1,1,1)) == 50)
    assert(score(Array(2,2,2,2,2,2)) == 50)
    assert(score(Array(3,3,3,3,3,3)) == 50)
    assert(score(Array(4,4,4,4,4,4)) == 50)
    assert(score(Array(5,5,5,5,5,5)) == 50)
    assert(score(Array(6,6,6,6,6,6)) == 50)
  }

  test("Not having yatzy scores zero points") {
    assert(score(Array(6,6,1,6,6,6)) == 0)
    assert(score(Array(2,3,3,3,3,3)) == 0)
    assert(score(Array(5,5,5,5,5,3)) == 0)
    assert(score(Array(2,3,3,3,2,3)) == 0)
    assert(score(Array(2,4,4,4,4,1)) == 0)
    assert(score(Array(2,3,4,5,5,5)) == 0)
    assert(score(Array(5,3,5,3,3,5)) == 0)
    assert(score(Array(2,1,2,1,5,5)) == 0)
    assert(score(Array(1,2,3,4,5,6)) == 0)
    assert(score(Array(1,3,4,5,6,2)) == 0)
  }
}
