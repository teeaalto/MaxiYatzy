import maxiYatzy.scores.Ones.score
import org.scalatest.FunSuite

class OnesSuite extends FunSuite {
  test("Any number of ones scores the sum of their values") {
    assert(score(Array(1,2,3,4,5,6)) == 1)
    assert(score(Array(2,2,4,3,5,6)) == 0)
    assert(score(Array(1,3,5,1,2,2)) == 2)
    assert(score(Array(1,3,5,5,1,1)) == 3)
    assert(score(Array(6,4,1,1,1,1)) == 4)
    assert(score(Array(1,1,1,1,1,2)) == 5)
    assert(score(Array(1,1,1,1,1,1)) == 6)
  }
}
