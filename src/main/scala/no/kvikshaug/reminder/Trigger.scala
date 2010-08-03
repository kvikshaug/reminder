package no.kvikshaug.reminder

import data.Event

import java.util.TimerTask
import org.joda.time.DateTime

object Trigger extends TimerTask {

  def run = {
    for(event <- Manager.events
      if(triggersToday(event))
    ) {
      notifyFor(event)
    }
  }

  def triggersToday(event: Event): Boolean = {
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
