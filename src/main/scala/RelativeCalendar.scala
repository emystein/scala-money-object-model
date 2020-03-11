import java.time.LocalDate

object RelativeCalendar {
  def yesterday: LocalDate = LocalDate.now().minusDays(1)
}
