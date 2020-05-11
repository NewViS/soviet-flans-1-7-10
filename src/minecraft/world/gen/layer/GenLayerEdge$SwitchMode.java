package net.minecraft.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayerEdge$Mode;

// $FF: synthetic class
class GenLayerEdge$SwitchMode {

   // $FF: synthetic field
   static final int[] field_151642_a = new int[GenLayerEdge$Mode.values().length];


   static {
      try {
         field_151642_a[GenLayerEdge$Mode.COOL_WARM.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
         ;
      }

      try {
         field_151642_a[GenLayerEdge$Mode.HEAT_ICE.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         field_151642_a[GenLayerEdge$Mode.SPECIAL.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
