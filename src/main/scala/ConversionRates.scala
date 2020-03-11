import java.time.LocalDate

case class ConversionRates(rates: Map[ConversionDirection, Map[LocalDate, ConversionRate]] = Map.empty) {
  def addRate(conversionDirection: ConversionDirection, conversionDate: LocalDate, rate: Double): ConversionRates = {
    val directionRates = rates.getOrElse(conversionDirection, Map.empty) + (conversionDate -> ConversionRate(conversionDirection, rate))

    ConversionRates(rates + (conversionDirection -> directionRates))
  }

  def convert(money: Money, targetCurrency: Currency, conversionDate: LocalDate): Option[Money] = {
    val conversionDirection = ConversionDirection(money.currency, targetCurrency)

    rates.get(conversionDirection)
      .flatMap(ratesByDate => ratesByDate.get(conversionDate))
      .map(rate => rate.convert(money))
  }
}