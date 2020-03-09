import java.time.LocalDate

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CurrencyConversionTest extends AnyFunSuite with Matchers {
  test("givenPesosWhenConvertThePesosToDollarsThenItShouldReturnDollars") {
    val ars63 = Money(63.0, "ARS")

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    rates.convert(ars63, "USD", LocalDate.now) shouldBe Some(Money(1.0, "USD"))
  }

  test("givenPesosWhenConvertThePesosToDollarsUsingYesterdayRateThenItShouldReturnDollars") {
    val ars62 = Money(62.0, "ARS")

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now.minusDays(1), 1 / 62.0)
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    val usd = rates.convert(ars62, "USD", LocalDate.now.minusDays(1))

    usd shouldBe Some(Money(1.0, "USD"))
  }

  test("givenEmptyConversionRatesWhenConvertToCurrencyThenItShouldThrowAnException") {
    val ars62 = Money(62.0, "ARS")

    val rates = new ConversionRates

    rates.convert(ars62, "NOTFOUND", LocalDate.now) shouldBe None
  }

  test("givenACurrencyWhenConvertToAnUnknownCurrencyThenItShouldThrowAnException") {
    val ars62 = Money(62.0, "ARS")

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    rates.convert(ars62, "NOTFOUND", LocalDate.now) shouldBe None
  }

  test("givenTwoConversionRatesForTheSameTargetCurrencyAndDateWhenAddTheConversionRatesThenTheSecondConversionRateShouldOverrideTheFirstOne") {
    val ars63 = Money(62.0, "ARS")

    val rates = new ConversionRates
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 62.0)
    rates.addRate("ARS", "USD", LocalDate.now, 1 / 63.0)

    val converted = rates.convert(ars63, "USD", LocalDate.now).get
    converted.amount should equal (1.0 +- 0.1)
    converted.currency shouldBe "USD"
  }
}