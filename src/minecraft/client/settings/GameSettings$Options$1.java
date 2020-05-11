package net.minecraft.client.settings;

import net.minecraft.util.MathHelper;

enum GameSettings$Options$1 {

   GameSettings$Options$1(String var1, int var2, String var3, boolean var4, boolean var5, float var6, float var7, float var8) {}

   protected float snapToStep(float var1) {
      return (float)MathHelper.roundUpToPowerOfTwo((int)var1);
   }
}
