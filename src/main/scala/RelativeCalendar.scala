import java.time.LocalDate

object RelativeCalendar {
  def today: LocalDate = LocalDate.now()
  def yesterday: LocalDate = LocalDate.now().minusDays(1)
}
