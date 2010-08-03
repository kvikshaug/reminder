package no.kvikshaug.reminder

import com.google.gson.{GsonBuilder}
import data.{DateTimeSerializer, Event}
import gson.{ListDeserializer, DateTimeDeserializer}
import org.joda.time.DateTime
import java.io.File

object GsonHandler {
  val jsonFile = new File("events.json")
  val gsonbuilder = new GsonBuilder
  gsonbuilder.registerTypeAdapter(classOf[DateTime], new DateTimeSerializer)
  gsonbuilder.registerTypeAdapter(classOf[DateTime], new DateTimeDeserializer)
  gsonbuilder.registerTypeAdapter(classOf[List[DateTime]], new ListDeserializer)
  val gson = gsonbuilder.create

  def initialize = {
    if(!jsonFile.exists) {
      // TODO ask if we should create it instead of telling the user to (e.g. "Start with an empty events file?")
      printf("Error: missing events file, expected path: '%s'. You can create an empty one if you have no events.\n", jsonFile.getAbsolutePath)
      System.exit(-1)
    }

    // load events from disk
    load
    println("some line")
  }

  def load: List[Event] = {
    val events = List[Event]()
    for(line <- scala.io.Source.fromFile(jsonFile).getLines) {
      println(line)
      gson.fromJson(line.substring(0, line.length-1), classOf[Event]).asInstanceOf[Event] :: events
    }
    events
  }

  def save(events: List[Event]) = {
    val out = new java.io.FileWriter(jsonFile)
    for(event <- events) {
      out.write(gson.toJson(event))
    }
    out.write("\n")
    out.close
  }
}
