package net.minecraft.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier;
import net.minecraft.util.JsonUtils;

public class ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer implements JsonDeserializer, JsonSerializer {

   public ServerStatusResponse$MinecraftProtocolVersionIdentifier deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = JsonUtils.getJsonElementAsJsonObject(var1, "version");
      return new ServerStatusResponse$MinecraftProtocolVersionIdentifier(JsonUtils.getJsonObjectStringFieldValue(var4, "name"), JsonUtils.getJsonObjectIntegerFieldValue(var4, "protocol"));
   }

   public JsonElement serialize(ServerStatusResponse$MinecraftProtocolVersionIdentifier var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("name", var1.func_151303_a());
      var4.addProperty("protocol", Integer.valueOf(var1.func_151304_b()));
      return var4;
   }

   // $FF: synthetic method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((ServerStatusResponse$MinecraftProtocolVersionIdentifier)var1, var2, var3);
   }

   // $FF: synthetic method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return this.deserialize(var1, var2, var3);
   }
}
