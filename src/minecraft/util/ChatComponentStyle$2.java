package net.minecraft.util;

import com.google.common.base.Function;
import net.minecraft.util.IChatComponent;

final class ChatComponentStyle$2 implements Function {

   public IChatComponent apply(IChatComponent var1) {
      IChatComponent var2 = var1.createCopy();
      var2.setChatStyle(var2.getChatStyle().createDeepCopy());
      return var2;
   }

   // $FF: synthetic method
   public Object apply(Object var1) {
      return this.apply((IChatComponent)var1);
   }
}
