package net.minecraft.world.biome;

import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMesa extends BiomeGenBase {

   private byte[] field_150621_aC;
   private long field_150622_aD;
   private NoiseGeneratorPerlin field_150623_aE;
   private NoiseGeneratorPerlin field_150624_aF;
   private NoiseGeneratorPerlin field_150625_aG;
   private boolean field_150626_aH;
   private boolean field_150620_aI;


   public BiomeGenMesa(int var1, boolean var2, boolean var3) {
      super(var1);
      this.field_150626_aH = var2;
      this.field_150620_aI = var3;
      this.setDisableRain();
      this.setTemperatureRainfall(2.0F, 0.0F);
      super.spawnableCreatureList.clear();
      super.topBlock = Blocks.sand;
      super.field_150604_aj = 1;
      super.fillerBlock = Blocks.stained_hardened_clay;
      super.theBiomeDecorator.treesPerChunk = -999;
      super.theBiomeDecorator.deadBushPerChunk = 20;
      super.theBiomeDecorator.reedsPerChunk = 3;
      super.theBiomeDecorator.cactiPerChunk = 5;
      super.theBiomeDecorator.flowersPerChunk = 0;
      super.spawnableCreatureList.clear();
      if(var3) {
         super.theBiomeDecorator.treesPerChunk = 5;
      }

   }

   public WorldGenAbstractTree func_150567_a(Random var1) {
      return super.worldGeneratorTrees;
   }

   public int getBiomeFoliageColor(int var1, int var2, int var3) {
      return 10387789;
   }

   public int getBiomeGrassColor(int var1, int var2, int var3) {
      return 9470285;
   }

   public void decorate(World var1, Random var2, int var3, int var4) {
      super.decorate(var1, var2, var3, var4);
   }

   public void genTerrainBlocks(World var1, Random var2, Block[] var3, byte[] var4, int var5, int var6, double var7) {
      if(this.field_150621_aC == null || this.field_150622_aD != var1.getSeed()) {
         this.func_150619_a(var1.getSeed());
      }

      if(this.field_150623_aE == null || this.field_150624_aF == null || this.field_150622_aD != var1.getSeed()) {
         Random var9 = new Random(this.field_150622_aD);
         this.field_150623_aE = new NoiseGeneratorPerlin(var9, 4);
         this.field_150624_aF = new NoiseGeneratorPerlin(var9, 1);
      }

      this.field_150622_aD = var1.getSeed();
      double var25 = 0.0D;
      int var11;
      int var12;
      if(this.field_150626_aH) {
         var11 = (var5 & -16) + (var6 & 15);
         var12 = (var6 & -16) + (var5 & 15);
         double var13 = Math.min(Math.abs(var7), this.field_150623_aE.func_151601_a((double)var11 * 0.25D, (double)var12 * 0.25D));
         if(var13 > 0.0D) {
            double var15 = 0.001953125D;
            double var17 = Math.abs(this.field_150624_aF.func_151601_a((double)var11 * var15, (double)var12 * var15));
            var25 = var13 * var13 * 2.5D;
            double var19 = Math.ceil(var17 * 50.0D) + 14.0D;
            if(var25 > var19) {
               var25 = var19;
            }

            var25 += 64.0D;
         }
      }

      var11 = var5 & 15;
      var12 = var6 & 15;
      boolean var26 = true;
      Block var14 = Blocks.stained_hardened_clay;
      Block var27 = super.fillerBlock;
      int var16 = (int)(var7 / 3.0D + 3.0D + var2.nextDouble() * 0.25D);
      boolean var28 = Math.cos(var7 / 3.0D * 3.141592653589793D) > 0.0D;
      int var18 = -1;
      boolean var29 = false;
      int var20 = var3.length / 256;

      for(int var21 = 255; var21 >= 0; --var21) {
         int var22 = (var12 * 16 + var11) * var20 + var21;
         if((var3[var22] == null || var3[var22].getMaterial() == Material.air) && var21 < (int)var25) {
            var3[var22] = Blocks.stone;
         }

         if(var21 <= 0 + var2.nextInt(5)) {
            var3[var22] = Blocks.bedrock;
         } else {
            Block var23 = var3[var22];
            if(var23 != null && var23.getMaterial() != Material.air) {
               if(var23 == Blocks.stone) {
                  byte var24;
                  if(var18 == -1) {
                     var29 = false;
                     if(var16 <= 0) {
                        var14 = null;
                        var27 = Blocks.stone;
                     } else if(var21 >= 59 && var21 <= 64) {
                        var14 = Blocks.stained_hardened_clay;
                        var27 = super.fillerBlock;
                     }

                     if(var21 < 63 && (var14 == null || var14.getMaterial() == Material.air)) {
                        var14 = Blocks.water;
                     }

                     var18 = var16 + Math.max(0, var21 - 63);
                     if(var21 >= 62) {
                        if(this.field_150620_aI && var21 > 86 + var16 * 2) {
                           if(var28) {
                              var3[var22] = Blocks.dirt;
                              var4[var22] = 1;
                           } else {
                              var3[var22] = Blocks.grass;
                           }
                        } else if(var21 > 66 + var16) {
                           var24 = 16;
                           if(var21 >= 64 && var21 <= 127) {
                              if(!var28) {
                                 var24 = this.func_150618_d(var5, var21, var6);
                              }
                           } else {
                              var24 = 1;
                           }

                           if(var24 < 16) {
                              var3[var22] = Blocks.stained_hardened_clay;
                              var4[var22] = (byte)var24;
                           } else {
                              var3[var22] = Blocks.hardened_clay;
                           }
                        } else {
                           var3[var22] = super.topBlock;
                           var4[var22] = (byte)super.field_150604_aj;
                           var29 = true;
                        }
                     } else {
                        var3[var22] = var27;
                        if(var27 == Blocks.stained_hardened_clay) {
                           var4[var22] = 1;
                        }
                     }
                  } else if(var18 > 0) {
                     --var18;
                     if(var29) {
                        var3[var22] = Blocks.stained_hardened_clay;
                        var4[var22] = 1;
                     } else {
                        var24 = this.func_150618_d(var5, var21, var6);
                        if(var24 < 16) {
                           var3[var22] = Blocks.stained_hardened_clay;
                           var4[var22] = var24;
                        } else {
                           var3[var22] = Blocks.hardened_clay;
                        }
                     }
                  }
               }
            } else {
               var18 = -1;
            }
         }
      }

   }

   private void func_150619_a(long var1) {
      this.field_150621_aC = new byte[64];
      Arrays.fill(this.field_150621_aC, (byte)16);
      Random var3 = new Random(var1);
      this.field_150625_aG = new NoiseGeneratorPerlin(var3, 1);

      int var4;
      for(var4 = 0; var4 < 64; ++var4) {
         var4 += var3.nextInt(5) + 1;
         if(var4 < 64) {
            this.field_150621_aC[var4] = 1;
         }
      }

      var4 = var3.nextInt(4) + 2;

      int var5;
      int var6;
      int var7;
      int var8;
      for(var5 = 0; var5 < var4; ++var5) {
         var6 = var3.nextInt(3) + 1;
         var7 = var3.nextInt(64);

         for(var8 = 0; var7 + var8 < 64 && var8 < var6; ++var8) {
            this.field_150621_aC[var7 + var8] = 4;
         }
      }

      var5 = var3.nextInt(4) + 2;

      int var9;
      for(var6 = 0; var6 < var5; ++var6) {
         var7 = var3.nextInt(3) + 2;
         var8 = var3.nextInt(64);

         for(var9 = 0; var8 + var9 < 64 && var9 < var7; ++var9) {
            this.field_150621_aC[var8 + var9] = 12;
         }
      }

      var6 = var3.nextInt(4) + 2;

      for(var7 = 0; var7 < var6; ++var7) {
         var8 = var3.nextInt(3) + 1;
         var9 = var3.nextInt(64);

         for(int var10 = 0; var9 + var10 < 64 && var10 < var8; ++var10) {
            this.field_150621_aC[var9 + var10] = 14;
         }
      }

      var7 = var3.nextInt(3) + 3;
      var8 = 0;

      for(var9 = 0; var9 < var7; ++var9) {
         byte var12 = 1;
         var8 += var3.nextInt(16) + 4;

         for(int var11 = 0; var8 + var11 < 64 && var11 < var12; ++var11) {
            this.field_150621_aC[var8 + var11] = 0;
            if(var8 + var11 > 1 && var3.nextBoolean()) {
               this.field_150621_aC[var8 + var11 - 1] = 8;
            }

            if(var8 + var11 < 63 && var3.nextBoolean()) {
               this.field_150621_aC[var8 + var11 + 1] = 8;
            }
         }
      }

   }

   private byte func_150618_d(int var1, int var2, int var3) {
      int var4 = (int)Math.round(this.field_150625_aG.func_151601_a((double)var1 * 1.0D / 512.0D, (double)var1 * 1.0D / 512.0D) * 2.0D);
      return this.field_150621_aC[(var2 + var4 + 64) % 64];
   }

   protected BiomeGenBase createMutation() {
      boolean var1 = super.biomeID == BiomeGenBase.mesa.biomeID;
      BiomeGenMesa var2 = new BiomeGenMesa(super.biomeID + 128, var1, this.field_150620_aI);
      if(!var1) {
         var2.setHeight(BiomeGenBase.height_LowHills);
         var2.setBiomeName(super.biomeName + " M");
      } else {
         var2.setBiomeName(super.biomeName + " (Bryce)");
      }

      var2.func_150557_a(super.color, true);
      return var2;
   }
}
