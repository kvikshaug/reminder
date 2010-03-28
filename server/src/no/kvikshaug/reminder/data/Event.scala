package no.kvikshaug.reminder.data

import collection.jcl.ArrayList
import org.joda.time.DateTime

class Event {
  var name: String
  var message: String
  var occurs: String
  val notificationDates = new ArrayList[DateTime]
}
