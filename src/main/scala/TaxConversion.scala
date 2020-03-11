import java.time.LocalDate;

class TaxConversion(val conversionRates: ConversionRates) {
    def convert(priceTaxes: Iterable[PriceTax], targetCurrency: Currency): Option[Money] = {
        val sum = priceTaxes.reduce(PriceTax.merge)
        conversionRates.convert(sum.tax, targetCurrency, yesterday);
    }

    private def yesterday: LocalDate = LocalDate.now().minusDays(1)
}
