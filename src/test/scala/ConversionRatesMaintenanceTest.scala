import ConversionDirections.arPesosToUsDollars
import Currencies._
import RelativeCalendar.{today, yesterday}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ConversionRatesMaintenanceTest extends AnyFunSuite with Matchers {
  test("Conversion rates for the same origin and destination currency and different day") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, yesterday, 1 / 62.0)
      .addRate(arPesosToUsDollars, today, 1 / 63.0)

    rates.from(arPesos).to(usDollars).forDate(today) shouldBe Some(ConversionRate(arPesosToUsDollars, 1 / 63.0))
  }

  test("Conversion rates for the same origin and destination currency and same date") {
    val rates = ConversionRates()
      .addRate(arPesosToUsDollars, today, 1 / 62.0)
      .addRate(arPesosToUsDollars, today, 1 / 63.0)

    rates.from(arPesos).to(usDollars).forDate(today) shouldBe Some(ConversionRate(arPesosToUsDollars, 1 / 63.0))
  }
}
