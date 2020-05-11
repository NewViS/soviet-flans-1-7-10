package net.minecraft.world;


public enum EnumSkyBlock {

   Sky("Sky", 0, 15),
   Block("Block", 1, 0);
   public final int defaultLightValue;
   // $FF: synthetic field
   private static final EnumSkyBlock[] $VALUES = new EnumSkyBlock[]{Sky, Block};


   private EnumSkyBlock(String var1, int var2, int var3) {
      this.defaultLightValue = var3;
   }

}
