package no.kvikshaug.reminder

import collection.jcl.ArrayList
import com.google.gson.{GsonBuilder}
import data.{DateTimeSerializer, Event}
import gson.{ListDeserializer, DateTimeDeserializer}
import org.joda.time.DateTime

object Manager {
  val events = new ArrayList[Event]

  def initialize = {
    events.add(new Event)

    val gsonbuilder = new GsonBuilder;
    gsonbuilder.registerTypeAdapter(classOf[DateTime], new DateTimeSerializer)
    gsonbuilder.registerTypeAdapter(classOf[DateTime], new DateTimeDeserializer)
    gsonbuilder.registerTypeAdapter(classOf[ArrayList[DateTime]], new ListDeserializer)
    val gson = gsonbuilder.create
    var json: String = ""
    for(event <- events) {
      json = gson.toJson(event);
      println(json)
    }
    val newObj: Event = gson.fromJson(json, classOf[Event]).asInstanceOf[Event]
    println("And now, for the result:")
    println(newObj.string)
    for(theDate: DateTime <- newObj.notificationDates) {
      println(theDate.getDayOfMonth)
    }
  }
}
