package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Map;

public enum Session$Type {

   LEGACY("LEGACY", 0, "legacy"),
   MOJANG("MOJANG", 1, "mojang");
   private static final Map field_152425_c = Maps.newHashMap();
   private final String field_152426_d;
   // $FF: synthetic field
   private static final Session$Type[] $VALUES = new Session$Type[]{LEGACY, MOJANG};


   private Session$Type(String var1, int var2, String var3) {
      this.field_152426_d = var3;
   }

   public static Session$Type func_152421_a(String var0) {
      return (Session$Type)field_152425_c.get(var0.toLowerCase());
   }

   static {
      Session$Type[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Session$Type var3 = var0[var2];
         field_152425_c.put(var3.field_152426_d, var3);
      }

   }
}
