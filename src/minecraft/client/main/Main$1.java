package net.minecraft.client.main;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import net.minecraft.client.main.Main$1$1;

public final class Main$1 implements ParameterizedType {

   public Type[] getActualTypeArguments() {
      return new Type[]{String.class, new Main$1$1(this)};
   }

   public Type getRawType() {
      return Map.class;
   }

   public Type getOwnerType() {
      return null;
   }
}
