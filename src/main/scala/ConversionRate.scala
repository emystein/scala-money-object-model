

case class ConversionDirection(originCurrency: Currency, destinationCurrency: Currency)

case class ConversionRate(conversionDirection: ConversionDirection, rate: Double) {
  def convert(money: Money): Money = {
    Money(amount = money.amount * rate, conversionDirection.destinationCurrency)
  }
}