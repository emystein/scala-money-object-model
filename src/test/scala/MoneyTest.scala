import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MoneyTest extends AnyFunSuite with Matchers {
  val ars10 = Ars(10.00)

  test("sum two amounts of the same currency") {
    val ars25 = Ars(25.00)

    val ars35 = ars25.plus(ars10)

    ars35.amount shouldBe 35.00
    ars35.currency shouldBe "ARS"
  }

  test("multiply an amount by a factor")  {
    val ars30 = ars10.multiplyBy(3)

    ars30.amount shouldBe 30.00
    ars30.currency shouldBe "ARS"
  }
}