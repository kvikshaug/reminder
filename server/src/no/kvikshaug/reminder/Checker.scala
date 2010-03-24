package no.kvikshaug.reminder

import data.Event

import java.util.TimerTask
import org.joda.time.DateTime

object Checker extends TimerTask {

  def run = {
    for(event <- Manager.events
      if(notifyToday(event))
    ) {
      println("Supposed to send notification for: " + event)
    }
  }

  def notifyToday(event: Event): Boolean = {
    val now = new DateTime
    for(dateTime <- event.notificationDates
      if(dateTime.getDayOfMonth.equals(now.getDayOfMonth));
      if(dateTime.getMonthOfYear.equals(now.getMonthOfYear))
    ) {
      return true
    }
    return false
  }
}
