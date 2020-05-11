package net.minecraft.util;

import com.google.common.base.Function;
import java.util.Iterator;
import net.minecraft.util.IChatComponent;

final class ChatComponentStyle$1 implements Function {

   public Iterator apply(IChatComponent var1) {
      return var1.iterator();
   }

   // $FF: synthetic method
   public Object apply(Object var1) {
      return this.apply((IChatComponent)var1);
   }
}
