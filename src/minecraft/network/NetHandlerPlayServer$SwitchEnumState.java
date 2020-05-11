package net.minecraft.network;

import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;

// $FF: synthetic class
class NetHandlerPlayServer$SwitchEnumState {

   // $FF: synthetic field
   static final int[] field_151290_a = new int[C16PacketClientStatus$EnumState.values().length];


   static {
      try {
         field_151290_a[C16PacketClientStatus$EnumState.PERFORM_RESPAWN.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
         ;
      }

      try {
         field_151290_a[C16PacketClientStatus$EnumState.REQUEST_STATS.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         field_151290_a[C16PacketClientStatus$EnumState.OPEN_INVENTORY_ACHIEVEMENT.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
