import RelativeCalendar.yesterday

class TaxConversion(val conversionRates: ConversionRates) {
    def convert(priceTaxes: Iterable[PriceTax], targetCurrency: Currency): Option[Money] = {
        val sum = priceTaxes.reduce(PriceTax.merge)
        conversionRates.convert(sum.tax, targetCurrency, yesterday);
    }
}
