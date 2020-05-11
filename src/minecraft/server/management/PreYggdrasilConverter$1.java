package net.minecraft.server.management;

import com.google.common.base.Predicate;
import net.minecraft.util.StringUtils;

final class PreYggdrasilConverter$1 implements Predicate {

   public boolean func_152733_a(String var1) {
      return !StringUtils.isNullOrEmpty(var1);
   }

   // $FF: synthetic method
   public boolean apply(Object var1) {
      return this.func_152733_a((String)var1);
   }
}
