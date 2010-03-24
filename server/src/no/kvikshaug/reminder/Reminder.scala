package no.kvikshaug.reminder

import java.util.{Date, Calendar, Timer}

object Reminder {
  def main(args: Array[String]) = {
    // initialize the mailer (check that mail.properties file exists and is loadable)
    Mailer.initialize
    val timer = new Timer
    val checker = new Checker
    timer.scheduleAtFixedRate(checker, tomorrowNight, 1000 * 60 * 60 * 24)
  }

  def tomorrowNight: Date = {
    val tomorrow = Calendar.getInstance
    tomorrow.add(Calendar.DATE, 1)
    tomorrow.set(Calendar.HOUR_OF_DAY, 0)
    tomorrow.set(Calendar.MINUTE, 10) // ten past, just in case the timing is off when checking our current date
    return tomorrow.getTime()
  }
}
