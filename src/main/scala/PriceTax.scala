object PriceTax {
  def merge(a: PriceTax, b: PriceTax): PriceTax = {
    PriceTax(Money(a.price.plus(b.price).amount, a.currency), a.tax.plus(b.tax));
  }
}

case class PriceTax(price: Money, tax: Money) {
  def currency: Currency = price.currency
}
