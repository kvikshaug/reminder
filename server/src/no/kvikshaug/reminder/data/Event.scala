package no.kvikshaug.reminder.data

import collection.jcl.ArrayList
import org.joda.time.DateTime

class Event {
  val notificationDates = new ArrayList[DateTime]
  notificationDates.add(new DateTime)
  notificationDates.add(new DateTime().plusDays(2).plusHours(3))
  val string = "string contents :)"
}