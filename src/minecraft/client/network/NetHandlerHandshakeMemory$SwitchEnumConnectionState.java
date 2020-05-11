package net.minecraft.client.network;

import net.minecraft.network.EnumConnectionState;

// $FF: synthetic class
class NetHandlerHandshakeMemory$SwitchEnumConnectionState {

   // $FF: synthetic field
   static final int[] field_151263_a = new int[EnumConnectionState.values().length];


   static {
      try {
         field_151263_a[EnumConnectionState.LOGIN.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         field_151263_a[EnumConnectionState.STATUS.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
