import maxiYatzy.scores.Threes.score
import org.scalatest.FunSuite

class ThreesSuite extends FunSuite {
  test("Any number of threes scores the sum of their values") {
    assert(score(Array(1,2,3,4,5,6)) == 3)
    assert(score(Array(3,2,4,3,5,6)) == 6)
    assert(score(Array(1,3,5,1,3,3)) == 9)
    assert(score(Array(1,4,6,5,4,2)) == 0)
    assert(score(Array(6,4,3,3,3,3)) == 12)
    assert(score(Array(3,3,3,3,3,2)) == 15)
    assert(score(Array(3,3,3,3,3,3)) == 18)
  }
}
