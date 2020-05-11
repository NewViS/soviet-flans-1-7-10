package net.minecraft.network;

import net.minecraft.network.NetworkStatistics$1;

class NetworkStatistics$PacketStatData {

   private final long field_152496_a;
   private final int field_152497_b;
   private final double field_152498_c;


   private NetworkStatistics$PacketStatData(long var1, int var3, double var4) {
      this.field_152496_a = var1;
      this.field_152497_b = var3;
      this.field_152498_c = var4;
   }

   public NetworkStatistics$PacketStatData func_152494_a(long var1) {
      return new NetworkStatistics$PacketStatData(var1 + this.field_152496_a, this.field_152497_b + 1, (double)((var1 + this.field_152496_a) / (long)(this.field_152497_b + 1)));
   }

   public long func_152493_a() {
      return this.field_152496_a;
   }

   public int func_152495_b() {
      return this.field_152497_b;
   }

   public String toString() {
      return "{totalBytes=" + this.field_152496_a + ", count=" + this.field_152497_b + ", averageBytes=" + this.field_152498_c + '}';
   }

   // $FF: synthetic method
   NetworkStatistics$PacketStatData(long var1, int var3, double var4, NetworkStatistics$1 var6) {
      this(var1, var3, var4);
   }

   // $FF: synthetic method
   static long access$300(NetworkStatistics$PacketStatData var0) {
      return var0.field_152496_a;
   }

   // $FF: synthetic method
   static int access$400(NetworkStatistics$PacketStatData var0) {
      return var0.field_152497_b;
   }
}
