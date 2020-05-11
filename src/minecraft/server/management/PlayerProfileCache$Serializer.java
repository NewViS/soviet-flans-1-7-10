package net.minecraft.server.management;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.PlayerProfileCache$1;
import net.minecraft.server.management.PlayerProfileCache$ProfileEntry;

class PlayerProfileCache$Serializer implements JsonDeserializer, JsonSerializer {

   // $FF: synthetic field
   final PlayerProfileCache field_152677_a;


   private PlayerProfileCache$Serializer(PlayerProfileCache var1) {
      this.field_152677_a = var1;
   }

   public JsonElement func_152676_a(PlayerProfileCache$ProfileEntry var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("name", var1.func_152668_a().getName());
      UUID var5 = var1.func_152668_a().getId();
      var4.addProperty("uuid", var5 == null?"":var5.toString());
      var4.addProperty("expiresOn", PlayerProfileCache.field_152659_a.format(var1.func_152670_b()));
      return var4;
   }

   public PlayerProfileCache$ProfileEntry func_152675_a(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if(var1.isJsonObject()) {
         JsonObject var4 = var1.getAsJsonObject();
         JsonElement var5 = var4.get("name");
         JsonElement var6 = var4.get("uuid");
         JsonElement var7 = var4.get("expiresOn");
         if(var5 != null && var6 != null) {
            String var8 = var6.getAsString();
            String var9 = var5.getAsString();
            Date var10 = null;
            if(var7 != null) {
               try {
                  var10 = PlayerProfileCache.field_152659_a.parse(var7.getAsString());
               } catch (ParseException var14) {
                  var10 = null;
               }
            }

            if(var9 != null && var8 != null) {
               UUID var11;
               try {
                  var11 = UUID.fromString(var8);
               } catch (Throwable var13) {
                  return null;
               }

               PlayerProfileCache$ProfileEntry var12 = new PlayerProfileCache$ProfileEntry(this.field_152677_a, new GameProfile(var11, var9), var10, (PlayerProfileCache$1)null);
               return var12;
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.func_152676_a((PlayerProfileCache$ProfileEntry)var1, var2, var3);
   }

   // $FF: synthetic method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return this.func_152675_a(var1, var2, var3);
   }

   // $FF: synthetic method
   PlayerProfileCache$Serializer(PlayerProfileCache var1, PlayerProfileCache$1 var2) {
      this(var1);
   }
}
