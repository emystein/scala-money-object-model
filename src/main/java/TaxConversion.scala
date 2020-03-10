import java.time.LocalDate;

class TaxConversion(val conversionRates: ConversionRates) {
    def convert(priceTaxes: Iterable[PriceTax], targetCurrency: Currency): Option[Money] = {
        val taxSum = priceTaxes.reduce((pt1, pt2) => PriceTax.merge(pt1, pt2))
        conversionRates.convert(taxSum.tax, targetCurrency, yesterday);
    }

    private def yesterday: LocalDate = LocalDate.now().minusDays(1)
}
