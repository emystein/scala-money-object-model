import java.time.LocalDate

case class ConversionRates(rates: Map[ConversionDirection, Map[LocalDate, ConversionRate]] = Map.empty) {
  def addRate(conversionDirection: ConversionDirection, conversionDate: LocalDate, rate: Double): ConversionRates = {
    val directionRates = rates.getOrElse(conversionDirection, Map.empty) + (conversionDate -> ConversionRate(conversionDirection, rate))

    ConversionRates(rates + (conversionDirection -> directionRates))
  }

  def from(currency: Currency): DestinationCurrencyRates = {
    DestinationCurrencyRates(ratesFromCurrency(currency))
  }

  private def ratesFromCurrency(currency: Currency) = {
    rates.keys
      .filter(conversionDirection => conversionDirection.originCurrency == currency)
      .map(conversionDirection => (conversionDirection.destinationCurrency -> rates(conversionDirection)))
      .toMap
  }
}

case class DestinationCurrencyRates(rates: Map[Currency, Map[LocalDate, ConversionRate]]) {
  def to(currency: Currency): DateRates = DateRates(rates.getOrElse(currency, Map.empty))
}

case class DateRates(rates: Map[LocalDate, ConversionRate]) {
  def forDate(date: LocalDate): Option[ConversionRate] = rates.get(date)
}

