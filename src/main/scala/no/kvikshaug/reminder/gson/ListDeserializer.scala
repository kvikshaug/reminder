package no.kvikshaug.reminder.gson

import org.joda.time.DateTime
import java.lang.reflect.Type
import com.google.gson.{JsonObject, JsonDeserializationContext, JsonElement, JsonDeserializer}

class ListDeserializer extends JsonDeserializer[List[DateTime]] {
  def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List[DateTime] = {
    val iterator = json.asInstanceOf[JsonObject].getAsJsonArray("underlying").iterator;
    val list = List[DateTime]()
    while(iterator.hasNext) {
      val dateTimeString = iterator.next.toString
      val dateTime = new DateTime((dateTimeString.substring(1, dateTimeString.length-1)))
      dateTime :: list
    }
    return list
  }
}
