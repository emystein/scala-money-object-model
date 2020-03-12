import ConversionDirections.arPesosToUsDollars
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CurrencyConversionTest extends AnyFunSuite with Matchers {
  val ars63 = Ars(63.0)
  val usd1 = UsDollars(1.0)

  test("given pesos when convert the pesos to dollars then it should return dollars") {
    val rate = ConversionRate(arPesosToUsDollars, 1 / 63.0)

    rate.convert(ars63) shouldBe Some(UsDollars(1.0))
  }

  test("can't convert money in different currency than rate origin currency") {
    val rate = ConversionRate(arPesosToUsDollars, 1 / 63.0)

    rate.convert(usd1) shouldBe None
  }
}