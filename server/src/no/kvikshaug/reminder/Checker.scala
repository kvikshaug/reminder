package no.kvikshaug.reminder

import java.util.TimerTask

class Checker extends TimerTask {

  def run = {
    println("Whoa, looks like I'm running!")
  }
}
