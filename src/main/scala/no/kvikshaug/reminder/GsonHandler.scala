package no.kvikshaug.reminder

import com.google.gson.GsonBuilder
import gson.{ListSerializer, ListDeserializer}
import org.joda.time.DateTime
import java.io.File

object GsonHandler {
  val jsonFile = new File("events.json")
  val gsonbuilder = new GsonBuilder
  gsonbuilder.registerTypeAdapter(classOf[List[Int]], new ListSerializer)
  gsonbuilder.registerTypeAdapter(classOf[List[Int]], new ListDeserializer)
  val gson = gsonbuilder.create

  def initialize = {
    if(!jsonFile.exists) {
      printf("Missing events file (%s). Create it? [y/n] ", jsonFile.getAbsolutePath)
      if(new java.util.Scanner(System.in).nextLine.toLowerCase.equals("y")) {
        if(!jsonFile.createNewFile) {
          printf("Unable to create '%s'. Permission problem?", jsonFile.getAbsolutePath)
          println("Cannot continue without an events file, exiting.")
          System.exit(-1)
        } else {
          println("Created empty events file '%s'.")
        }
      } else {
        println("Cannot continue without an events file, exiting.")
        System.exit(-1)
      }
    }
  }

  def load: List[Event] = {
    var events = List[Event]()
    for(line <- scala.io.Source.fromFile(jsonFile).getLines
      if(line.length() > 0)
    ) {
      events = gson.fromJson(line, classOf[Event]).asInstanceOf[Event] :: events
    }
    println(" > " + events.size + " events loaded from '" + jsonFile.getName + "'.")
    events
  }

  def save(events: List[Event]) = {
    val out = new java.io.FileWriter(jsonFile)
    for(event <- events) {
      out.write(gson.toJson(event))
      out.write("\n")
    }
    out.write("\n")
    out.close
  }
}
