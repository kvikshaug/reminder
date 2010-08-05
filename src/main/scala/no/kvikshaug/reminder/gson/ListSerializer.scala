package no.kvikshaug.reminder.gson

import org.joda.time.DateTime
import java.lang.reflect.Type
import com.google.gson.{JsonElement, JsonPrimitive, JsonSerializationContext, JsonSerializer}

class ListSerializer extends JsonSerializer[List[Int]] {
  def serialize(src: List[Int], typeOfSrc: Type, context: JsonSerializationContext): JsonElement = {
    if(src.size == 0) {
      return new JsonPrimitive("")
    }
    val strb = new StringBuilder
    for(int <- src) {
      // comma-separate dates in one string
      strb.append(int).append(",")
    }
    val str = strb.toString
    new JsonPrimitive(str.substring(0, str.length()-1))
  }
}
