import maxiYatzy.scores.Sixes.score
import org.scalatest.FunSuite

class SixesSuite extends FunSuite {
  test("Any number of sixes scores the sum of their values") {
    assert(score(Array(1,2,4,3,5,5)) == 0)
    assert(score(Array(1,2,3,4,5,6)) == 6)
    assert(score(Array(6,3,5,6,2,2)) == 12)
    assert(score(Array(1,3,6,6,6,1)) == 18)
    assert(score(Array(6,6,1,1,6,6)) == 24)
    assert(score(Array(6,6,6,6,6,2)) == 30)
    assert(score(Array(6,6,6,6,6,6)) == 36)
  }
}
