package no.kvikshaug.reminder.gson

import org.joda.time.DateTime
import java.lang.reflect.Type
import com.google.gson.{JsonObject, JsonDeserializationContext, JsonElement, JsonDeserializer}

class ListDeserializer extends JsonDeserializer[List[DateTime]] {
  def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List[DateTime] = {
    var list = List[DateTime]()

    for(str <- json.getAsString().split(",")) {
      list = new DateTime(str) :: list
    }
    list
  }
}
