package net.minecraft.client.main;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import net.minecraft.client.main.Main$1;

class Main$1$1 implements ParameterizedType {

   // $FF: synthetic field
   final Main$1 field_152580_a;


   Main$1$1(Main$1 var1) {
      this.field_152580_a = var1;
   }

   public Type[] getActualTypeArguments() {
      return new Type[]{String.class};
   }

   public Type getRawType() {
      return Collection.class;
   }

   public Type getOwnerType() {
      return null;
   }
}
