package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public enum MusicTicker$MusicType {

   MENU("MENU", 0, new ResourceLocation("minecraft:music.menu"), 20, 600),
   GAME("GAME", 1, new ResourceLocation("minecraft:music.game"), 12000, 24000),
   CREATIVE("CREATIVE", 2, new ResourceLocation("minecraft:music.game.creative"), 1200, 3600),
   CREDITS("CREDITS", 3, new ResourceLocation("minecraft:music.game.end.credits"), Integer.MAX_VALUE, Integer.MAX_VALUE),
   NETHER("NETHER", 4, new ResourceLocation("minecraft:music.game.nether"), 1200, 3600),
   END_BOSS("END_BOSS", 5, new ResourceLocation("minecraft:music.game.end.dragon"), 0, 0),
   END("END", 6, new ResourceLocation("minecraft:music.game.end"), 6000, 24000);
   private final ResourceLocation field_148645_h;
   private final int field_148646_i;
   private final int field_148643_j;
   // $FF: synthetic field
   private static final MusicTicker$MusicType[] $VALUES = new MusicTicker$MusicType[]{MENU, GAME, CREATIVE, CREDITS, NETHER, END_BOSS, END};


   private MusicTicker$MusicType(String var1, int var2, ResourceLocation var3, int var4, int var5) {
      this.field_148645_h = var3;
      this.field_148646_i = var4;
      this.field_148643_j = var5;
   }

   public ResourceLocation getMusicTickerLocation() {
      return this.field_148645_h;
   }

   public int func_148634_b() {
      return this.field_148646_i;
   }

   public int func_148633_c() {
      return this.field_148643_j;
   }

}
