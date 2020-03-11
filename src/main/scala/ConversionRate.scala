import java.time.LocalDate

case class ConversionDirection(originCurrency: Currency, destinationCurrency: Currency)

case class ConversionRate(conversionDirection: ConversionDirection, date: LocalDate, rate: Double) {
  def convert(money: Money): Money = {
    Money(amount = money.amount * rate, conversionDirection.destinationCurrency)
  }
}