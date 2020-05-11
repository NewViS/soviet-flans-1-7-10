package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$Height;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenSnow extends BiomeGenBase {

   private boolean field_150615_aC;
   private WorldGenIceSpike field_150616_aD = new WorldGenIceSpike();
   private WorldGenIcePath field_150617_aE = new WorldGenIcePath(4);


   public BiomeGenSnow(int var1, boolean var2) {
      super(var1);
      this.field_150615_aC = var2;
      if(var2) {
         super.topBlock = Blocks.snow;
      }

      super.spawnableCreatureList.clear();
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      if(this.field_150615_aC) {
         int var5;
         int var6;
         int var7;
         for(var5 = 0; var5 < 3; ++var5) {
            var6 = var3 + var2.nextInt(16) + 8;
            var7 = var4 + var2.nextInt(16) + 8;
            this.field_150616_aD.generate(var1, var2, var6, var1.getHeightValue(var6, var7), var7);
         }

         for(var5 = 0; var5 < 2; ++var5) {
            var6 = var3 + var2.nextInt(16) + 8;
            var7 = var4 + var2.nextInt(16) + 8;
            this.field_150617_aE.generate(var1, var2, var6, var1.getHeightValue(var6, var7), var7);
         }
      }

      super.decorate(var1, var2, var3, var4);
   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return new WorldGenTaiga2(false);
   }

   protected BiomeGenBase createMutation() {
      BiomeGenBase var1 = (new BiomeGenSnow(super.biomeID + 128, true)).func_150557_a(13828095, true).setBiomeName(super.biomeName + " Spikes").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F).setHeight(new BiomeGenBase$Height(super.rootHeight + 0.1F, super.heightVariation + 0.1F));
      var1.rootHeight = super.rootHeight + 0.3F;
      var1.heightVariation = super.heightVariation + 0.4F;
      return var1;
   }
}
