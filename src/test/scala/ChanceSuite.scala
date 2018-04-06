import org.scalatest.FunSuite
import maxiYatzy.scores.Chance.score

class ChanceSuite extends FunSuite {
  test("Any combination of dices scores the sum of values") {
    assert(score(Array(1,3,3,3,3,3)) == 16)
    assert(score(Array(1,2,3,4,5,6)) == 21)
    assert(score(Array(6,3,5,1,2,4)) == 21)
    assert(score(Array(6,3,5,5,2,4)) == 25)
    assert(score(Array(6,6,5,5,2,2)) == 26)
    assert(score(Array(6,6,5,5,5,2)) == 29)
    assert(score(Array(1,6,5,1,5,1)) == 19)
    assert(score(Array(2,2,2,3,3,3)) == 15)
    assert(score(Array(2,2,2,2,3,3)) == 14)
    assert(score(Array(3,2,2,3,3,3)) == 16)
    assert(score(Array(5,2,5,5,3,5)) == 25)
    assert(score(Array(2,2,6,6,6,6)) == 28)
    assert(score(Array(1,1,1,1,1,1)) == 6)
  }
}
