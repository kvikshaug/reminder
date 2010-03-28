package no.kvikshaug.reminder.gson

import org.joda.time.DateTime
import java.lang.reflect.Type
import collection.jcl.ArrayList
import com.google.gson.{JsonObject, JsonDeserializationContext, JsonElement, JsonDeserializer}

class ListDeserializer extends JsonDeserializer[ArrayList[DateTime]] {
  def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ArrayList[DateTime] = {
    val iterator = json.asInstanceOf[JsonObject].getAsJsonArray("underlying").iterator;
    val list = new ArrayList[DateTime]
    while(iterator.hasNext) {
      val dateTimeString = iterator.next.toString
      val dateTime = new DateTime((dateTimeString.substring(1, dateTimeString.length-1)))
      list.add(dateTime)
    }
    return list
  }
}
