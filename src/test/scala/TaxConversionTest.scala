import ConversionDirections.arPesosToUsDollars
import RelativeCalendar.yesterday
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TaxConversionTest extends AnyFunSuite with Matchers {
  test("convert taxes") {
    val priceTax1 = PriceTax(price = Ars(100.0), tax = Ars(21.0))
    val priceTax2 = PriceTax(price = Ars(200.0), tax = Ars(42.0))

    val conversionRates = ConversionRates(
      Map(arPesosToUsDollars -> Map(yesterday -> ConversionRate(arPesosToUsDollars, 1 / 62.0)))
    )

    val taxConversion = new TaxConversion(conversionRates)

    taxConversion.convert(List(priceTax1, priceTax2), Currency("USD")).get.amount should equal (1.0161 +- 0.0001)
  }
}