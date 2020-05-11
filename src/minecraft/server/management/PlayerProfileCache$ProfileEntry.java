package net.minecraft.server.management;

import com.mojang.authlib.GameProfile;
import java.util.Date;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.PlayerProfileCache$1;

class PlayerProfileCache$ProfileEntry {

   private final GameProfile field_152672_b;
   private final Date field_152673_c;
   // $FF: synthetic field
   final PlayerProfileCache field_152671_a;


   private PlayerProfileCache$ProfileEntry(PlayerProfileCache var1, GameProfile var2, Date var3) {
      this.field_152671_a = var1;
      this.field_152672_b = var2;
      this.field_152673_c = var3;
   }

   public GameProfile func_152668_a() {
      return this.field_152672_b;
   }

   public Date func_152670_b() {
      return this.field_152673_c;
   }

   // $FF: synthetic method
   PlayerProfileCache$ProfileEntry(PlayerProfileCache var1, GameProfile var2, Date var3, PlayerProfileCache$1 var4) {
      this(var1, var2, var3);
   }

   // $FF: synthetic method
   static Date access$200(PlayerProfileCache$ProfileEntry var0) {
      return var0.field_152673_c;
   }
}
