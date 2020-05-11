package net.minecraft.server.management;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.server.management.UserListEntry;

public class UserListOpsEntry extends UserListEntry {

   private final int field_152645_a;


   public UserListOpsEntry(GameProfile var1, int var2) {
      super(var1);
      this.field_152645_a = var2;
   }

   public UserListOpsEntry(JsonObject var1) {
      super(func_152643_b(var1), var1);
      this.field_152645_a = var1.has("level")?var1.get("level").getAsInt():0;
   }

   public int func_152644_a() {
      return this.field_152645_a;
   }

   protected void func_152641_a(JsonObject var1) {
      if(this.func_152640_f() != null) {
         var1.addProperty("uuid", ((GameProfile)this.func_152640_f()).getId() == null?"":((GameProfile)this.func_152640_f()).getId().toString());
         var1.addProperty("name", ((GameProfile)this.func_152640_f()).getName());
         super.func_152641_a(var1);
         var1.addProperty("level", Integer.valueOf(this.field_152645_a));
      }
   }

   private static GameProfile func_152643_b(JsonObject var0) {
      if(var0.has("uuid") && var0.has("name")) {
         String var1 = var0.get("uuid").getAsString();

         UUID var2;
         try {
            var2 = UUID.fromString(var1);
         } catch (Throwable var4) {
            return null;
         }

         return new GameProfile(var2, var0.get("name").getAsString());
      } else {
         return null;
      }
   }
}
