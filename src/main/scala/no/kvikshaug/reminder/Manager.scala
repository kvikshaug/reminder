package no.kvikshaug.reminder

import com.google.gson.{GsonBuilder}
import data.{DateTimeSerializer, Event}
import gson.{ListDeserializer, DateTimeDeserializer}
import org.joda.time.DateTime

object Manager {
  val jsonFile = "events.json"
  val events = List[Event]()
  val gsonbuilder = new GsonBuilder
  gsonbuilder.registerTypeAdapter(classOf[DateTime], new DateTimeSerializer)
  gsonbuilder.registerTypeAdapter(classOf[DateTime], new DateTimeDeserializer)
  gsonbuilder.registerTypeAdapter(classOf[List[DateTime]], new ListDeserializer)
  val gson = gsonbuilder.create

  def initialize = {
    // load events from disk
    load
    println("some line")
  }

  def load = {
    val iterator = scala.io.Source.fromFile(jsonFile).getLines
    while(iterator.hasNext) {
      val line = iterator.next
      gson.fromJson(line.substring(0, line.length-1), classOf[Event]).asInstanceOf[Event] :: events
    }
  }

  def save = {
    val out = new java.io.FileWriter(jsonFile)
    for(event <- events) {
      out.write(gson.toJson(event))
    }
    out.write("\n")
    out.close
  }
}
