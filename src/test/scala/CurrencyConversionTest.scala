import java.time.LocalDate

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CurrencyConversionTest extends AnyFunSuite with Matchers {
  test("given pesos when convert the pesos to dollars then it should return dollars") {
    val ars63 = Ars(63.0)

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    rates.convert(ars63, "USD", LocalDate.now) shouldBe Some(Money(1.0, "USD"))
  }

  test("given pesos when convert the pesos to dollars using yesterday rate then it should return dollars") {
    val ars62 = Ars(62.0)

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now.minusDays(1), 1 / 62.0)
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    val usd = rates.convert(ars62, "USD", LocalDate.now.minusDays(1))

    usd shouldBe Some(Money(1.0, "USD"))
  }

  test("given empty conversion rates when convert to currency then it should throw an exception") {
    val ars62 = Ars(62.0)

    val rates = new ConversionRates

    rates.convert(ars62, "NOTFOUND", LocalDate.now) shouldBe None
  }

  test("given acurrency when convert to an unknown currency then it should throw an exception") {
    val ars62 = Ars(62.0)

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    rates.convert(ars62, "NOTFOUND", LocalDate.now) shouldBe None
  }

  test("given two conversion rates for the same target currency and date when add the conversion rates then the second conversion rate should override the first one") {
    val ars62 = Ars(62.0)

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 62.0)
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    val converted = rates.convert(ars62, "USD", LocalDate.now).get
    converted.amount should equal (1.0 +- 0.1)
    converted.currency shouldBe "USD"
  }
}