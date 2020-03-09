case class Money(amount: Double, currency: String) {
  def plus(other: Money): Money = Money(amount + other.amount, currency)
  def multiplyBy(factor: Int): Money = Money(amount * factor, currency)
}
