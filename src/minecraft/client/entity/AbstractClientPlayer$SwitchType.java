package net.minecraft.client.entity;

import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

// $FF: synthetic class
class AbstractClientPlayer$SwitchType {

   // $FF: synthetic field
   static final int[] field_152630_a = new int[Type.values().length];


   static {
      try {
         field_152630_a[Type.SKIN.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         field_152630_a[Type.CAPE.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
