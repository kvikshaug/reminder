package no.kvikshaug.reminder

import org.joda.time.DateTime

case class Event(val name: String, val month: Int, val day: Int, val notifyList: List[Int]) {

  // GSON needs a no-args constructor (http://sites.google.com/site/gson/gson-user-guide#TOC-Writing-an-Instance-Creator)
  // i don't know if creating that this way is good practice/the best way (and don't think it is)
  def this() = this("", 0, 0, List[Int]())

  def date(): String = day + ". " + Reminder.textualMonthOf(month)

  /**
   * Pretty-print all the notification dates
   */
  def notifyDates(): String = {
    val str = new StringBuilder
    var i = 0
    val last = notifyList.size - 1
    while(i < notifyList.size) {
      i match {
        case 0 => str.append(notifyList(i))
        case `last` => str.append(" and ").append(notifyList(i))
        case _ => str.append(", ").append(notifyList(i))
      }
      i = i + 1
    }
    str.toString
  }
}
