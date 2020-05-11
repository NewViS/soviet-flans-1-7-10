package net.minecraft.util;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.util.IJsonSerializable;

public class JsonSerializableSet extends ForwardingSet implements IJsonSerializable {

   private final Set underlyingSet = Sets.newHashSet();


   public void func_152753_a(JsonElement var1) {
      if(var1.isJsonArray()) {
         Iterator var2 = var1.getAsJsonArray().iterator();

         while(var2.hasNext()) {
            JsonElement var3 = (JsonElement)var2.next();
            this.add(var3.getAsString());
         }
      }

   }

   public JsonElement getSerializableElement() {
      JsonArray var1 = new JsonArray();
      Iterator var2 = this.iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.add(new JsonPrimitive(var3));
      }

      return var1;
   }

   protected Set delegate() {
      return this.underlyingSet;
   }
}
