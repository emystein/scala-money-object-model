import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MoneyTest extends AnyFunSuite with Matchers {
  test("sum two amounts of the same currency") {
    val ars25 = Money(25.00, "ARS")
    val ars10 = Money(10.00, "ARS")

    val ars35 = ars25.plus(ars10)

    ars35.amount shouldBe(35.00)
  }

  test("multiply an amount by a factor")  {
    val ars2 = Money(2.00, "ARS")

    val ars6 = ars2.multiplyBy(3)

    ars6.amount shouldBe(6.00)
  }
}