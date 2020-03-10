import java.time.LocalDate
import scala.collection.mutable

class ConversionRates {
  private val rates: mutable.Set[ConversionRate] = mutable.Set.empty

  def addRate(sourceCurrency: Currency, targetCurrency: Currency, conversionDate: LocalDate, rate: Double) =
    rates += new ConversionRate(sourceCurrency, targetCurrency, conversionDate, rate)

  def convert(money: Money, targetCurrency: Currency, conversionDate: LocalDate): Option[Money] = {
    val conversionRate = rates.find(rate => rate.canConvert(money.currency, targetCurrency, conversionDate))
    conversionRate.map(_.convert(money))
  }
}