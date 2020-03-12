import RelativeCalendar.yesterday

class TaxConversion(val conversionRates: ConversionRates) {
    def convert(priceTaxes: Iterable[PriceTax], destinationCurrency: Currency): Option[Money] = {
        val sum = priceTaxes.reduce(PriceTax.merge)

        conversionRates
          .from(sum.currency)
          .to(destinationCurrency)
          .forDate(yesterday)
          .flatMap(rate => rate.convert(sum.tax))
    }
}

object PriceTax {
  def merge(a: PriceTax, b: PriceTax): PriceTax = {
    PriceTax(Money(a.price.plus(b.price).amount, a.currency), a.tax.plus(b.tax));
  }
}

case class PriceTax(price: Money, tax: Money) {
  def currency: Currency = price.currency
}

