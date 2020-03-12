import ConversionDirections.arPesosToUsDollars
import Currencies._
import RelativeCalendar.{today, yesterday}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ConversionRatesDslTest extends AnyFunSuite with Matchers {
  test("Get conversion rates for ars to usd") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, today, 1 / 63.0)

    rates.from(arPesos).to(usDollars).dateRates shouldBe Map(today -> ConversionRate(arPesosToUsDollars, 1 / 63.0))
  }

  test("Convert from ars to usd") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, today, 1 / 63.0)

    rates.from(arPesos).to(usDollars).forDate(today) shouldBe Some(ConversionRate(arPesosToUsDollars, 1 / 63.0))
  }

  test("Origin currency not found in rates") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, today, 1 / 63.0)

    rates.from(euros).to(usDollars).forDate(today) shouldBe None
  }

  test("Destination currency not found in rates") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, today, 1 / 63.0)

    rates.from(arPesos).to(euros).forDate(today) shouldBe None
  }

  test("Rate for date not found") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, today, 1 / 63.0)

    rates.from(arPesos).to(usDollars).forDate(yesterday) shouldBe None
  }
}

