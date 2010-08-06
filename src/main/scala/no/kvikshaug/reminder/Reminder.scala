package no.kvikshaug.reminder

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

    // Listen for and handle keyboard input
    new Thread(Input).start

    val nextEvent = nextNEvents(3)
    println("\nReady. Next three events will be:")
    for(i <- 0 to 2) {
      println(nextEvent(i).textualDate + ": \t" + nextEvent(i).name + " (" + nextEvent(i).notifyDates + ")")
    }
  }

  /**
   * Called by Trigger every midnight. Checks if there is any event that has a notification for this date.
   */
  def runChecks = {
    println("\nCurrent time is " + new DateTime().toString() + ": Running checks.")
    var hits = 0
    for(event <- events) {
      for(diff <- event.notifyList) {
        val now = new DateTime().plusDays(diff)
        if(now.getMonthOfYear == event.month && now.getDayOfMonth == event.date) {
          hits = hits + 1
          Mailer.mail("Reminder: "+event.name+" ("+event.textualDate+")",
                      "The event '"+event.name+"' occurs "+event.date+".\n" +
                      "You'll be reminded "+event.notifyDates+" days before that.")
        }
      }
    }
    println(hits + " notifications sent. Next three events will be:")
    val nextEvent = nextNEvents(3)
    for(i <- 0 to 2) {
      println(nextEvent(i).textualDate + ": \t" + nextEvent(i).name + " (" + nextEvent(i).notifyDates + ")")
    }
  }

  def monthOf(month: String): Int = month.toLowerCase match {
    case "january" => 1
    case "february" => 2
    case "march" => 3
    case "april" => 4
    case "may" => 5
    case "june" => 6
    case "july" => 7
    case "august" => 8
    case "september" => 9
    case "october" => 10
    case "november" => 11
    case "december" => 12
    case _  => 0
  }

  def textualMonthOf(month: Int): String = month match {
    case 1  => "january"
    case 2  => "february"
    case 3  => "march"
    case 4  => "april"
    case 5  => "may"
    case 6  => "june"
    case 7  => "july"
    case 8  => "august"
    case 9  => "september"
    case 10 => "october"
    case 11 => "november"
    case 12 => "december"
    case _  => "invalid month"
  }

  def nextNEvents(n: Int): List[Event] = {
    val newEvents = events.sortWith(_ < _)
    var retEvents = List[Event]()
    var o = 0
    if(n > events.size) {
      o = events.size
    } else {
      o = n
    }
    for(i <- 0 to (o-1)) {
      retEvents = retEvents :+ newEvents(i)
    }
    retEvents
  }

}
