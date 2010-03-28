package no.kvikshaug.reminder

import java.util.{Date, Calendar, Timer}

object Reminder {
  def main(args: Array[String]) = {
    // initialize the mailer (check that mail.properties file exists and is loadable)
    Mailer.initialize
    
    val timer = new Timer
    timer.scheduleAtFixedRate(Checker, tomorrowNight, 1000 * 60 * 60 * 24)
    Manager.initialize
  }

  def tomorrowNight: Date = {
    // not using joda-time here because we need to pass a java.util.Date to the Timer
    // (but maybe we should -- and convert?)
    val tomorrow = Calendar.getInstance
    tomorrow.add(Calendar.DATE, 1)
    tomorrow.set(Calendar.HOUR_OF_DAY, 0)
    tomorrow.set(Calendar.MINUTE, 10) // ten past, just in case the timing is off when checking our current date
    return tomorrow.getTime()
  }
}
