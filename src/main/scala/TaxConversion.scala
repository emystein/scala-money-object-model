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
