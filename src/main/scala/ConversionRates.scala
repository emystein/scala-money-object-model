import java.time.LocalDate

case class ConversionRates(rates: Map[ConversionDirection, Map[LocalDate, ConversionRate]] = Map.empty) {
  def addRate(conversionDirection: ConversionDirection, conversionDate: LocalDate, rate: Double): ConversionRates = {
    val directionRates = rates.getOrElse(conversionDirection, Map.empty) + (conversionDate -> ConversionRate(conversionDirection, rate))

    ConversionRates(rates + (conversionDirection -> directionRates))
  }

  def from(currency: Currency): RatesFromCurrency = {
    val destinationRates = rates.keys.filter(k => k.originCurrency == currency)
      .map(k => (k.destinationCurrency -> rates(k)))
      .toMap

    RatesFromCurrency(currency, destinationRates)
  }
}

case class RatesFromCurrency(from: Currency, destinationRates: Map[Currency, Map[LocalDate, ConversionRate]]) {
  def to(currency: Currency): RatesToCurrency = RatesToCurrency(currency, destinationRates.getOrElse(currency, Map.empty))
}

case class RatesToCurrency(to: Currency, dateRates: Map[LocalDate, ConversionRate]) {
  def forDate(date: LocalDate): Option[ConversionRate] = dateRates.get(date)
}

