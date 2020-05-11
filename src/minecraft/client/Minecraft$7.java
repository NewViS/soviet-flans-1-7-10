package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

class Minecraft$7 implements Callable {

   // $FF: synthetic field
   final Minecraft mc;


   Minecraft$7(Minecraft var1) {
      this.mc = var1;
   }

   public String call() {
      return GL11.glGetString(7937) + " GL version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936);
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
