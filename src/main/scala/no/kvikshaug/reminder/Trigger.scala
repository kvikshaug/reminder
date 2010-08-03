package no.kvikshaug.reminder

import data.Event

import java.util.TimerTask
import org.joda.time.DateTime

object Trigger extends TimerTask {

  val timer = new Timer

  def initialize = {
    // we will trigger an event:
    val tomorrowNight = new DateTime()
      .plusDays(1) // tomorrow
      .secondOfDay().withMinimumValue() // at 00:00:00
      .toDate() // as a java.util.Date

    timer.scheduleAtFixedRate(this, tomorrowNight, 1000 * 60 * 60 * 24)
  }

  def run = Reminder.runChecks
}
