import maxiYatzy.scores.Fives.score
import org.scalatest.FunSuite

class FivesSuite extends FunSuite {
  test("Any number of ones scores the sum of their values") {
    assert(score(Array(1,2,4,3,6,6)) == 0)
    assert(score(Array(1,2,3,4,5,6)) == 5)
    assert(score(Array(1,3,5,1,5,2)) == 10)
    assert(score(Array(1,3,5,5,5,1)) == 15)
    assert(score(Array(6,4,5,5,5,5)) == 20)
    assert(score(Array(5,5,5,5,5,2)) == 25)
    assert(score(Array(5,5,5,5,5,5)) == 30)
  }
}
