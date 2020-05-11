package net.minecraft.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Locale;
import net.minecraft.util.EnumTypeAdapterFactory$1;

public class EnumTypeAdapterFactory implements TypeAdapterFactory {

   public TypeAdapter create(Gson var1, TypeToken var2) {
      Class var3 = var2.getRawType();
      if(!var3.isEnum()) {
         return null;
      } else {
         HashMap var4 = new HashMap();
         Object[] var5 = var3.getEnumConstants();
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            Object var8 = var5[var7];
            var4.put(this.func_151232_a(var8), var8);
         }

         return new EnumTypeAdapterFactory$1(this, var4);
      }
   }

   private String func_151232_a(Object var1) {
      return var1 instanceof Enum?((Enum)var1).name().toLowerCase(Locale.US):var1.toString().toLowerCase(Locale.US);
   }

   // $FF: synthetic method
   static String access$000(EnumTypeAdapterFactory var0, Object var1) {
      return var0.func_151232_a(var1);
   }
}
