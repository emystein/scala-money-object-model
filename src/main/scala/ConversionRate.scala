import java.time.LocalDate

class ConversionRate(val sourceCurrency: Currency, val targetCurrency: Currency, val conversionDate: LocalDate, val rate: Double) {
  def canConvert(sourceCurrency: Currency, targetCurrency: Currency, conversionDate: LocalDate): Boolean =
    this.sourceCurrency == sourceCurrency && this.targetCurrency == targetCurrency && this.conversionDate == conversionDate

  def convert(money: Money): Money = {
    Money(amount = money.amount * rate, targetCurrency)
  }
}