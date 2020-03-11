import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PriceTaxTest extends AnyFunSuite with Matchers {
  test("merge") {
    val priceTax1 = PriceTax(price = Ars(100.0), tax = Ars(21.0))
    val priceTax2 = PriceTax(price = Ars(200.0), tax = Ars(42.0))

    PriceTax.merge(priceTax1, priceTax2) shouldBe PriceTax(Ars(300.0), Ars(63.0))
  }
}
