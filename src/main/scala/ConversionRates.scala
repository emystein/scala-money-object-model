import java.time.LocalDate

import scala.collection.mutable

class ConversionRates {
  private val rates: mutable.Map[ConversionDirection, mutable.Map[LocalDate, ConversionRate]] = mutable.Map.empty

  def addRate(sourceCurrency: Currency, targetCurrency: Currency, conversionDate: LocalDate, rate: Double) = {
    val conversionDirection = ConversionDirection(sourceCurrency, targetCurrency)

    val ratesByDate = rates.getOrElse(conversionDirection, mutable.Map.empty)

    ratesByDate += (conversionDate -> ConversionRate(conversionDirection, conversionDate, rate))

    rates.put(conversionDirection, ratesByDate)
  }

  def convert(money: Money, targetCurrency: Currency, conversionDate: LocalDate): Option[Money] = {
    val conversionDirection = ConversionDirection(money.currency, targetCurrency)

    rates.get(conversionDirection)
      .flatMap(ratesByDate => ratesByDate.get(conversionDate))
      .map(rate => rate.convert(money))
  }
}