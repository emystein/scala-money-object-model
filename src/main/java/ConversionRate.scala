import java.time.LocalDate

class ConversionRate(val sourceCurrency: String, val targetCurrency: String, val conversionDate: LocalDate, val rate: Double) {
  def canConvert(sourceCurrency: String, targetCurrency: String, conversionDate: LocalDate): Boolean =
    this.sourceCurrency == sourceCurrency && this.targetCurrency == targetCurrency && this.conversionDate == conversionDate

  def convert(money: Money): Money = {
    Money(amount = money.amount * rate, targetCurrency)
  }
}