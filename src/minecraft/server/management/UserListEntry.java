package net.minecraft.server.management;

import com.google.gson.JsonObject;

public class UserListEntry {

   private final Object field_152642_a;


   public UserListEntry(Object var1) {
      this.field_152642_a = var1;
   }

   protected UserListEntry(Object var1, JsonObject var2) {
      this.field_152642_a = var1;
   }

   Object func_152640_f() {
      return this.field_152642_a;
   }

   boolean hasBanExpired() {
      return false;
   }

   protected void func_152641_a(JsonObject var1) {}
}
