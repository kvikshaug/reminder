package no.kvikshaug.reminder.data

import org.joda.time.DateTime
import java.lang.reflect.Type
import com.google.gson.{JsonElement, JsonPrimitive, JsonSerializationContext, JsonSerializer}

class DateTimeSerializer extends JsonSerializer[DateTime] {
  def serialize(src: DateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement = {
    new JsonPrimitive(src.toString());
  }
}