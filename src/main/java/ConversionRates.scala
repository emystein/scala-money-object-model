import java.time.LocalDate
import scala.collection.mutable

class ConversionRates {
  private val rates: mutable.Set[ConversionRate] = mutable.Set.empty

  def addRate(sourceCurrency: String, targetCurrency: String, conversionDate: LocalDate, rate: Double) =
    rates += new ConversionRate(sourceCurrency, targetCurrency, conversionDate, rate)

  def convert(money: Money, targetCurrency: String, conversionDate: LocalDate): Option[Money] = {
    val conversionRate = rates.find(rate => rate.canConvert(money.currency, targetCurrency, conversionDate))
    conversionRate.map(_.convert(money))
  }
}