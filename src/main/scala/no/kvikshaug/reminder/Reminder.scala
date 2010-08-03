package no.kvikshaug.reminder

import java.util.Date
import org.joda.time.DateTime

object Reminder {

  var events = List[Event]()

  def main(args: Array[String]) = {
    println("Starting reminder:")

    // Load mail settings from file
    Mailer.initialize

    // Load events from file
    GsonHandler.initialize
    events = GsonHandler.load

    // Schedule timer
    Trigger.initialize

    // TODO calculate next notification and inform at startup
    println("\nReady. Next notification: (TODO).")
  }

  /**
   * Called by Trigger every midnight. Checks if there is any event that has a notification for this date.
   */
  def runChecks = {
    for(event <- events
      if(checkEvent(event))
    ) {
      notifyFor(event)
    }
  }

  def checkEvent(event: Event): Boolean = {
    val now = new DateTime
    for(dateTime <- event.notificationDates
      if(dateTime.getDayOfMonth.equals(now.getDayOfMonth));
      if(dateTime.getMonthOfYear.equals(now.getMonthOfYear))
    ) {
      return true
    }
    return false
  }

  def notifyFor(event: Event) = {
    Mailer.mail("Reminder for "+event.name+" ("+event.occurs+")",
              "This is a notification reminder for '"+event.name+"'.\n\n" +
              "Message: "+event.message+"\n" +
              "Occurs: "+event.occurs)
  }
}
