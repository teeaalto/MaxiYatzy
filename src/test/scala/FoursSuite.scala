import maxiYatzy.scores.Fours.score
import org.scalatest.FunSuite

class FoursSuite extends FunSuite {
  test("Any number of fours scores the sum of their values") {
    assert(score(Array(1,2,3,5,5,6)) == 0)
    assert(score(Array(1,2,3,4,5,6)) == 4)
    assert(score(Array(4,3,5,4,2,2)) == 8)
    assert(score(Array(4,3,5,5,4,4)) == 12)
    assert(score(Array(6,4,1,4,4,4)) == 16)
    assert(score(Array(4,4,4,4,4,2)) == 20)
    assert(score(Array(4,4,4,4,4,4)) == 24)
  }
}
