import ConversionDirections.arPesosToUsDollars
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CurrencyConversionTest extends AnyFunSuite with Matchers {
  val ars63 = Ars(63.0)
  val usd1 = UsDollars(1.0)

  val arPesosToUsDollarsRate = ConversionRate(arPesosToUsDollars, 1 / 63.0)

  test("given pesos when convert the pesos to dollars then it should return dollars") {
    arPesosToUsDollarsRate.convert(ars63) shouldBe Some(usd1)
  }

  test("can't convert money in different currency than rate origin currency") {
    arPesosToUsDollarsRate.convert(usd1) shouldBe None
  }
}