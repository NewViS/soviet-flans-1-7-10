package net.minecraft.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.util.Map;
import net.minecraft.util.EnumTypeAdapterFactory;

class EnumTypeAdapterFactory$1 extends TypeAdapter {

   // $FF: synthetic field
   final Map field_151231_a;
   // $FF: synthetic field
   final EnumTypeAdapterFactory field_151230_b;


   EnumTypeAdapterFactory$1(EnumTypeAdapterFactory var1, Map var2) {
      this.field_151230_b = var1;
      this.field_151231_a = var2;
   }

   public void write(JsonWriter var1, Object var2) {
      if(var2 == null) {
         var1.nullValue();
      } else {
         var1.value(EnumTypeAdapterFactory.access$000(this.field_151230_b, var2));
      }

   }

   public Object read(JsonReader var1) {
      if(var1.peek() == JsonToken.NULL) {
         var1.nextNull();
         return null;
      } else {
         return this.field_151231_a.get(var1.nextString());
      }
   }
}
