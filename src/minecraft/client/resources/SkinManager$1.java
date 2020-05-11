package net.minecraft.client.resources;

import com.google.common.cache.CacheLoader;
import com.mojang.authlib.GameProfile;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SkinManager;

class SkinManager$1 extends CacheLoader {

   // $FF: synthetic field
   final SkinManager field_152787_a;


   SkinManager$1(SkinManager var1) {
      this.field_152787_a = var1;
   }

   public Map func_152786_a(GameProfile var1) {
      return Minecraft.getMinecraft().func_152347_ac().getTextures(var1, false);
   }

   // $FF: synthetic method
   public Object load(Object var1) {
      return this.func_152786_a((GameProfile)var1);
   }
}
