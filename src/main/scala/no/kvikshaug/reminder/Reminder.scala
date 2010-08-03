package no.kvikshaug.reminder

import java.util.{Date, Calendar, Timer}

object Reminder {

  def main(args: Array[String]) = {
    // initialize all resources; Trigger will start a TimerTask (and therefore never end)
    Mailer.initialize
    GsonHandler.initialize
    Trigger.initialize
  }

  /**
   * Called by Trigger every midnight. Checks if there is any event that has a notification for this date.
   */
  def runChecks = {
    for(event <- events
      if(triggersToday(event))
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
