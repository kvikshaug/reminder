package no.kvikshaug.reminder.gson

import org.joda.time.DateTime
import java.lang.reflect.Type
import com.google.gson.{JsonObject, JsonDeserializationContext, JsonElement, JsonDeserializer}

class ListDeserializer extends JsonDeserializer[List[Int]] {
  def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List[Int] = {
    var list = List[Int]()
    if(json.getAsString().equals("")) {
      return list
    }

    for(str <- json.getAsString().split(",")) {
      list = Integer.parseInt(str) :: list
    }
    list.sortWith(_ > _)
  }
}
