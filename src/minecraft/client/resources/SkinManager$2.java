package net.minecraft.client.resources;

import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.SkinManager$SkinAvailableCallback;
import net.minecraft.util.ResourceLocation;

class SkinManager$2 implements IImageBuffer {

   // $FF: synthetic field
   final IImageBuffer field_152635_a;
   // $FF: synthetic field
   final SkinManager$SkinAvailableCallback field_152636_b;
   // $FF: synthetic field
   final Type field_152637_c;
   // $FF: synthetic field
   final ResourceLocation field_152638_d;
   // $FF: synthetic field
   final SkinManager field_152639_e;


   SkinManager$2(SkinManager var1, IImageBuffer var2, SkinManager$SkinAvailableCallback var3, Type var4, ResourceLocation var5) {
      this.field_152639_e = var1;
      this.field_152635_a = var2;
      this.field_152636_b = var3;
      this.field_152637_c = var4;
      this.field_152638_d = var5;
   }

   public BufferedImage parseUserSkin(BufferedImage var1) {
      if(this.field_152635_a != null) {
         var1 = this.field_152635_a.parseUserSkin(var1);
      }

      return var1;
   }

   public void func_152634_a() {
      if(this.field_152635_a != null) {
         this.field_152635_a.func_152634_a();
      }

      if(this.field_152636_b != null) {
         this.field_152636_b.func_152121_a(this.field_152637_c, this.field_152638_d);
      }

   }
}
