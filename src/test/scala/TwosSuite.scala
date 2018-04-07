import maxiYatzy.scores.Twos.score
import org.scalatest.FunSuite

class TwosSuite extends FunSuite {
  test("Any number of twos scores the sum of their values") {
    assert(score(Array(1,2,3,4,5,6)) == 2)
    assert(score(Array(1,1,4,3,5,6)) == 0)
    assert(score(Array(1,3,5,1,2,2)) == 4)
    assert(score(Array(2,3,5,5,2,2)) == 6)
    assert(score(Array(6,4,2,2,2,2)) == 8)
    assert(score(Array(2,2,2,2,1,2)) == 10)
    assert(score(Array(2,2,2,2,2,2)) == 12)
  }
}
