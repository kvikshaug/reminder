package no.kvikshaug.reminder.data

import collection.jcl.ArrayList
import org.joda.time.DateTime

class Event(val name: String, val message: String, val occurs: String,
            val notificationDates: ArrayList[DateTime]) {
}
