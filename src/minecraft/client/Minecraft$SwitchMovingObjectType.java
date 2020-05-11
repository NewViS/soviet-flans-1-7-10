package net.minecraft.client;

import net.minecraft.util.MovingObjectPosition$MovingObjectType;

// $FF: synthetic class
class Minecraft$SwitchMovingObjectType {

   // $FF: synthetic field
   static final int[] field_152390_a = new int[MovingObjectPosition$MovingObjectType.values().length];


   static {
      try {
         field_152390_a[MovingObjectPosition$MovingObjectType.ENTITY.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         field_152390_a[MovingObjectPosition$MovingObjectType.BLOCK.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
