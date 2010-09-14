package no.kvikshaug.reminder

import org.joda.time.DateTime

case class Event(val name: String, val month: Int, val date: Int, val notifyList: List[Int]) {

  // GSON needs a no-args constructor (http://sites.google.com/site/gson/gson-user-guide#TOC-Writing-an-Instance-Creator)
  // i don't know if creating that this way is good practice/the best way (and don't think it is)
  def this() = this("", 0, 0, List[Int]())

  def textualDate(): String = date + ". " + Reminder.textualMonthOf(month)

  /**
   * Pretty-print all the notification dates
   */
  def notifyDates: String = join(notifyList)

  def join(list : List[Int]): String = list match {
    case List() => ""
    case List(x) => x.toString
    case List(x,y) => x + " and " + y
    case List(x,y,z) => x + ", " + y + ", and " + z
    case _ => list(0) + ", " + join(list.tail)
  }

  /**
   * True if this event occurs before the event we're comparing to,
   * at the time the check is performed
   */
  def <(e: Event): Boolean = {
    val now = new DateTime()
    var thisEvent = new DateTime().monthOfYear().setCopy(month).dayOfMonth().setCopy(date)
    var thatEvent = new DateTime().monthOfYear().setCopy(e.month).dayOfMonth().setCopy(e.date)
    if(thisEvent.isBefore(now)) {
      thisEvent = thisEvent.plusYears(1)
    }
    if(thatEvent.isBefore(now)) {
      thatEvent = thatEvent.plusYears(1)
    }
    return thisEvent.isBefore(thatEvent)
  }
}

