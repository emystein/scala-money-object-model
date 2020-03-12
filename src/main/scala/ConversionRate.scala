case class ConversionDirection(originCurrency: Currency, destinationCurrency: Currency)

case class ConversionRate(conversionDirection: ConversionDirection, rate: Double) {
  def convert(money: Money): Option[Money] = {
    if (canConvert(money)) {
      Some(Money(amount = money.amount * rate, conversionDirection.destinationCurrency))
    } else {
      None
    }
  }

  def canConvert(money: Money): Boolean = money.currency == conversionDirection.originCurrency
}