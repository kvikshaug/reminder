package no.kvikshaug.reminder

import org.joda.time.DateTime

class Event(val name: String, val message: String, val occurs: String,
            val notificationDates: List[DateTime]) {

  // GSON needs a no-args constructor (http://sites.google.com/site/gson/gson-user-guide#TOC-Writing-an-Instance-Creator)
  // i don't know if creating that this way is good practice/the best way (and don't think it is)
  def this() = this("", "", "", List[DateTime]())
}
