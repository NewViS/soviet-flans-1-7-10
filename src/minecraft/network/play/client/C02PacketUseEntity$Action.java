package net.minecraft.network.play.client;


public enum C02PacketUseEntity$Action {

   INTERACT("INTERACT", 0, 0),
   ATTACK("ATTACK", 1, 1);
   private static final C02PacketUseEntity$Action[] field_151421_c = new C02PacketUseEntity$Action[values().length];
   private final int field_151418_d;
   // $FF: synthetic field
   private static final C02PacketUseEntity$Action[] $VALUES = new C02PacketUseEntity$Action[]{INTERACT, ATTACK};


   private C02PacketUseEntity$Action(String var1, int var2, int var3) {
      this.field_151418_d = var3;
   }

   // $FF: synthetic method
   static C02PacketUseEntity$Action[] access$000() {
      return field_151421_c;
   }

   // $FF: synthetic method
   static int access$100(C02PacketUseEntity$Action var0) {
      return var0.field_151418_d;
   }

   static {
      C02PacketUseEntity$Action[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         C02PacketUseEntity$Action var3 = var0[var2];
         field_151421_c[var3.field_151418_d] = var3;
      }

   }
}
