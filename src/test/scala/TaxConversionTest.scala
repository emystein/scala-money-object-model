import java.time.LocalDate

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TaxConversionTest extends AnyFunSuite with Matchers {
  test("convert taxes") {
    val priceTax1 = PriceTax(price = Money(100.0, "ARS"), tax = Money(21.0, "ARS"))
    val priceTax2 = PriceTax(price = Money(200.0, "ARS"), tax = Money(42.0, "ARS"))

    val priceTaxes = List(priceTax1, priceTax2)

    val conversionRates = new ConversionRates
    conversionRates.addRate("ARS", "USD", LocalDate.now.minusDays(1), 1 / 62.0)

    val taxConversion = new TaxConversion(conversionRates)

    taxConversion.convert(priceTaxes, "USD").get.amount should equal (1.0161 +- 0.0001)
  }
}