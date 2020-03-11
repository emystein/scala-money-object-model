import java.time.LocalDate.now

import ConversionDirections.arPesosToUsDollars
import RelativeCalendar.yesterday
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CurrencyConversionTest extends AnyFunSuite with Matchers {
  val ars62 = Ars(62.0)
  val ars63 = Ars(63.0)

  test("given pesos when convert the pesos to dollars then it should return dollars") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, now, 1 / 63.0)

    rates.convert(ars63, Currency("USD"), now) shouldBe Some(Dollars(1.0))
  }

  test("given pesos when convert the pesos to dollars using yesterday rate then it should return dollars") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, yesterday, 1 / 62.0)
      .addRate(arPesosToUsDollars, now, 1 / 63.0)

    val usd = rates.convert(ars62, Currency("USD"), yesterday)

    usd shouldBe Some(Dollars(1.0))
  }

  test("given empty conversion rates when convert to currency then it should throw an exception") {
    val rates = ConversionRates()

    rates.convert(ars62, Currency("NOTFOUND"), now) shouldBe None
  }

  test("given acurrency when convert to an unknown currency then it should throw an exception") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, now, 1 / 63.0)

    rates.convert(ars62, Currency("NOTFOUND"), now) shouldBe None
  }

  test("given two conversion rates for the same target currency and date when add the conversion rates then the second conversion rate should override the first one") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, now, 1 / 62.0)
      .addRate(arPesosToUsDollars, now, 1 / 63.0)

    val converted = rates.convert(ars62, Currency("USD"), now).get

    converted.amount should equal (1.0 +- 0.1)
    converted.currency shouldBe Currency("USD")
  }
}