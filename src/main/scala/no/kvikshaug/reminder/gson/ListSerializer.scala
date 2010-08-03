package no.kvikshaug.reminder.gson

import org.joda.time.DateTime
import java.lang.reflect.Type
import com.google.gson.{JsonElement, JsonPrimitive, JsonSerializationContext, JsonSerializer}

class ListSerializer extends JsonSerializer[List[DateTime]] {
  def serialize(src: List[DateTime], typeOfSrc: Type, context: JsonSerializationContext): JsonElement = {
    val str = new StringBuilder
    for(dt <- src) {
      // comma-separate dates in one string
      str.append(dt.toString()).append(",")
    }
    new JsonPrimitive(str.toString);
  }
}
