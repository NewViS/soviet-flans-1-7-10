package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.client.resources.data.BaseMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;

public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer implements JsonSerializer {

   public PackMetadataSection deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      IChatComponent var5 = (IChatComponent)var3.deserialize(var4.get("description"), IChatComponent.class);
      int var6 = JsonUtils.getJsonObjectIntegerFieldValue(var4, "pack_format");
      return new PackMetadataSection(var5, var6);
   }

   public JsonElement serialize(PackMetadataSection var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("pack_format", Integer.valueOf(var1.getPackFormat()));
      var4.add("description", var3.serialize(var1.func_152805_a()));
      return var4;
   }

   public String getSectionName() {
      return "pack";
   }

   // $FF: synthetic method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((PackMetadataSection)var1, var2, var3);
   }
}
