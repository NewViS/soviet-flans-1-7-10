package net.minecraft.world;

import net.minecraft.entity.player.PlayerCapabilities;

public enum WorldSettings$GameType {

   NOT_SET("NOT_SET", 0, -1, ""),
   SURVIVAL("SURVIVAL", 1, 0, "survival"),
   CREATIVE("CREATIVE", 2, 1, "creative"),
   ADVENTURE("ADVENTURE", 3, 2, "adventure");
   int id;
   String name;
   // $FF: synthetic field
   private static final WorldSettings$GameType[] $VALUES = new WorldSettings$GameType[]{NOT_SET, SURVIVAL, CREATIVE, ADVENTURE};


   private WorldSettings$GameType(String var1, int var2, int var3, String var4) {
      this.id = var3;
      this.name = var4;
   }

   public int getID() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public void configurePlayerCapabilities(PlayerCapabilities var1) {
      if(this == CREATIVE) {
         var1.allowFlying = true;
         var1.isCreativeMode = true;
         var1.disableDamage = true;
      } else {
         var1.allowFlying = false;
         var1.isCreativeMode = false;
         var1.disableDamage = false;
         var1.isFlying = false;
      }

      var1.allowEdit = !this.isAdventure();
   }

   public boolean isAdventure() {
      return this == ADVENTURE;
   }

   public boolean isCreative() {
      return this == CREATIVE;
   }

   public boolean isSurvivalOrAdventure() {
      return this == SURVIVAL || this == ADVENTURE;
   }

   public static WorldSettings$GameType getByID(int var0) {
      WorldSettings$GameType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         WorldSettings$GameType var4 = var1[var3];
         if(var4.id == var0) {
            return var4;
         }
      }

      return SURVIVAL;
   }

   public static WorldSettings$GameType getByName(String var0) {
      WorldSettings$GameType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         WorldSettings$GameType var4 = var1[var3];
         if(var4.name.equals(var0)) {
            return var4;
         }
      }

      return SURVIVAL;
   }

}
