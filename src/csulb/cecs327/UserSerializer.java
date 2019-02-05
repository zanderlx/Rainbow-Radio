package csulb.cecs327;

import com.google.gson.*;

public class UserSerializer implements JsonSerializer<User> {
    public JsonElement serialize(User user, java.lang.reflect.Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.add("userName", new JsonPrimitive(user.getUserName()));
        result.add("email", new JsonPrimitive(user.getEmail()));
        result.add("password", new JsonPrimitive(user.getPassword()));
        return result;
    }
}