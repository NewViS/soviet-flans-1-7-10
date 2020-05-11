package net.minecraft.network.play.client;


public enum C16PacketClientStatus$EnumState {

   PERFORM_RESPAWN("PERFORM_RESPAWN", 0, 0),
   REQUEST_STATS("REQUEST_STATS", 1, 1),
   OPEN_INVENTORY_ACHIEVEMENT("OPEN_INVENTORY_ACHIEVEMENT", 2, 2);
   private final int field_151403_d;
   private static final C16PacketClientStatus$EnumState[] field_151404_e = new C16PacketClientStatus$EnumState[values().length];
   // $FF: synthetic field
   private static final C16PacketClientStatus$EnumState[] $VALUES = new C16PacketClientStatus$EnumState[]{PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT};


   private C16PacketClientStatus$EnumState(String var1, int var2, int var3) {
      this.field_151403_d = var3;
   }

   // $FF: synthetic method
   static C16PacketClientStatus$EnumState[] access$000() {
      return field_151404_e;
   }

   // $FF: synthetic method
   static int access$100(C16PacketClientStatus$EnumState var0) {
      return var0.field_151403_d;
   }

   static {
      C16PacketClientStatus$EnumState[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         C16PacketClientStatus$EnumState var3 = var0[var2];
         field_151404_e[var3.field_151403_d] = var3;
      }

   }
}
