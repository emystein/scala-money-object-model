case class Money(amount: Double, currency: Currency) {
  def plus(other: Money): Money = Money(amount + other.amount, currency)
  def multiplyBy(factor: Int): Money = Money(amount * factor, currency)
}

object Ars {
  def apply(amount: Double) = Money(amount, Currency("ARS"))
}

object Dollars {
  def apply(amount: Double) = Money(amount, Currency("USD"))
}

