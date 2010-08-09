package no.kvikshaug.reminder

import java.util.Scanner

object Input extends Runnable {

  val scan = new Scanner(System.in)

  val helptext = """
Commands:
new event  -- Create a new event
ne         -- Create a new event
next N     -- Show when the next N events occur
find X     -- Search for the name of an event
exit       -- Exit the application
quit       -- Exit the application
Note: For now, to modify or remove an event, edit the events.json file manually.
"""

  def run = {
    while(true) {
      scan.nextLine match {
        case "help" => println(helptext)
        case "exit" => println("Don't forget me!"); System.exit(0)
        case "quit" => println("Don't forget me!"); System.exit(0)
        case "new event" => addNewEvent
        case "ne" => addNewEvent
        case "" => Unit // ignore
        case cmd =>
          if(cmd.startsWith("next")) {
            showNext(cmd)
          } else if(cmd.startsWith("find")) {
            findEvent(cmd)
          } else {
            println("Unknown command '" + cmd + "'.")
          }
      }
    }
  }

  def showNext(nStr: String) = {
    try {
      val n = Integer.parseInt(nStr.replace("next", "").trim())
      println("\nNext " + n + " events:")
      for(e <- Reminder.nextNEvents(n)) {
        println(e.textualDate + ": \t" + e.name + " (" + e.notifyDates + ")")
      }
    } catch {
      case e => println("Invalid count 'N'!")
    }
  }

  def findEvent(xCmd: String): Unit = {
    if(xCmd.trim.equals("find")) {
      println("Find what?")
      return
    }

    val x = xCmd.replace("find", "").trim.toLowerCase
    var hits = List[Event]()
    for(e <- Reminder.events) {
      if(e.name.toLowerCase.contains(x)) {
        hits = e :: hits
      }
    }
    if(hits.size == 0) {
      println("Sorry, no event matches that.")
    } else {
      for(h <- hits.sortWith(_ < _)) {
        println(h.textualDate + ": \t" + h.name + " (" + h.notifyDates + ")")
      }
    }
  }

  def addNewEvent = {
    println("Ok, you'll be asked for: Name, date, month, notification dates")

    // name
    var accepted = false
    var name = ""
    while(!accepted) {
      print("Name? [String]: ")
      scan.nextLine match {
        case "" => println("Please enter a non-empty name!")
        case newName => name = newName; accepted=true
      }
    }

    // date
    accepted = false
    var date = 0
    while(!accepted) {
      print("Date? [Int]: ")
      val dateRead = scan.nextLine
      try {
        date = Integer.parseInt(dateRead)
        if(date < 1 || date > 31) {
          println("There are only 31 days in a month. Keep it between 1 and 31.")
        } else {
          accepted = true
        }
      } catch {
        case e => println("That's not a proper integer, is it now?")
      }
    }

    // month
    accepted = false
    var month = 0
    while(!accepted) {
      print("Month? [String|Int]: ")
      val read = scan.nextLine
      try {
        val i = Integer.parseInt(read)
        // entered as an int
        if(i<1 || i>12) {
          println("There are only 12 months in a year. Keep it between 1 and 12.")
        } else {
          month = i
          accepted = true
        }
      } catch {
        case e =>
          // entered as a string
          val monthOfS = Reminder.monthOf(read)
          monthOfS match {
            case 0 => println("Unrecognized month, please try again")
            case _ =>
              month = monthOfS
              accepted = true
          }
      }
    }

    // dates
    println("Now enter the number of days you want to be notified in advance for this event. Include 0 for same day. 's' to save.")
    accepted = false
    var ints = List[Int]()
    while(!accepted) {
      print("No. of days? [Int|'s']: ")
      val input = scan.nextLine
      try {
        val days = Integer.parseInt(input)
        if(days < 0) {
          println("What's the point of being notified *after* the event?")
        } else {
          ints = days :: ints
        }
      } catch {
        case e: Exception =>
          if(input.equals("s")) {
            accepted = true
          } else {
            println("Enter a valid number, or 's' to stop.")
          }
      }
    }

    println("Sorting array and saving to file...")
    Reminder.events = Event(name, month, date, ints.sortWith(_ > _)) :: Reminder.events
    GsonHandler.save(Reminder.events)
    println("Added event, now counting " + Reminder.events.size + " events.")
  }

}
