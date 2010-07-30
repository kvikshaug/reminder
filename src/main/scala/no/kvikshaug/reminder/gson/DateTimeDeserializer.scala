package no.kvikshaug.reminder.gson

import org.joda.time.DateTime
import com.google.gson.{JsonDeserializationContext, JsonElement, JsonDeserializer}
import java.lang.reflect.Type

class DateTimeDeserializer extends JsonDeserializer[DateTime] {
  def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): DateTime = {
    return new DateTime(json.getAsJsonPrimitive().getAsString());
  }
}