package no.kvikshaug.reminder.data

import collection.jcl.ArrayList
import org.joda.time.DateTime

class Event {
  val notificationDates = new ArrayList[DateTime]
  notificationDates.add(new DateTime)
  val string = "something"
}