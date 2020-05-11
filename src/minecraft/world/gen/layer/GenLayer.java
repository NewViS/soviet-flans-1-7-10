package net.minecraft.world.gen.layer;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer$1;
import net.minecraft.world.gen.layer.GenLayer$2;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerEdge$Mode;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayer {

   private long worldGenSeed;
   protected GenLayer parent;
   private long chunkSeed;
   protected long baseSeed;


   public static GenLayer[] initializeAllBiomeGenerators(long var0, WorldType var2) {
      boolean var3 = false;
      GenLayerIsland var4 = new GenLayerIsland(1L);
      GenLayerFuzzyZoom var11 = new GenLayerFuzzyZoom(2000L, var4);
      GenLayerAddIsland var12 = new GenLayerAddIsland(1L, var11);
      GenLayerZoom var13 = new GenLayerZoom(2001L, var12);
      var12 = new GenLayerAddIsland(2L, var13);
      var12 = new GenLayerAddIsland(50L, var12);
      var12 = new GenLayerAddIsland(70L, var12);
      GenLayerRemoveTooMuchOcean var14 = new GenLayerRemoveTooMuchOcean(2L, var12);
      GenLayerAddSnow var16 = new GenLayerAddSnow(2L, var14);
      var12 = new GenLayerAddIsland(3L, var16);
      GenLayerEdge var17 = new GenLayerEdge(2L, var12, GenLayerEdge$Mode.COOL_WARM);
      var17 = new GenLayerEdge(2L, var17, GenLayerEdge$Mode.HEAT_ICE);
      var17 = new GenLayerEdge(3L, var17, GenLayerEdge$Mode.SPECIAL);
      var13 = new GenLayerZoom(2002L, var17);
      var13 = new GenLayerZoom(2003L, var13);
      var12 = new GenLayerAddIsland(4L, var13);
      GenLayerAddMushroomIsland var22 = new GenLayerAddMushroomIsland(5L, var12);
      GenLayerDeepOcean var23 = new GenLayerDeepOcean(4L, var22);
      GenLayer var24 = GenLayerZoom.magnify(1000L, var23, 0);
      byte var5 = 4;
      if(var2 == WorldType.LARGE_BIOMES) {
         var5 = 6;
      }

      if(var3) {
         var5 = 4;
      }

      GenLayer var6 = GenLayerZoom.magnify(1000L, var24, 0);
      GenLayerRiverInit var15 = new GenLayerRiverInit(100L, var6);
      Object var7 = new GenLayerBiome(200L, var24, var2);
      if(!var3) {
         GenLayer var18 = GenLayerZoom.magnify(1000L, (GenLayer)var7, 2);
         var7 = new GenLayerBiomeEdge(1000L, var18);
      }

      GenLayer var8 = GenLayerZoom.magnify(1000L, var15, 2);
      GenLayerHills var21 = new GenLayerHills(1000L, (GenLayer)var7, var8);
      var6 = GenLayerZoom.magnify(1000L, var15, 2);
      var6 = GenLayerZoom.magnify(1000L, var6, var5);
      GenLayerRiver var19 = new GenLayerRiver(1L, var6);
      GenLayerSmooth var20 = new GenLayerSmooth(1000L, var19);
      var7 = new GenLayerRareBiome(1001L, var21);

      for(int var9 = 0; var9 < var5; ++var9) {
         var7 = new GenLayerZoom((long)(1000 + var9), (GenLayer)var7);
         if(var9 == 0) {
            var7 = new GenLayerAddIsland(3L, (GenLayer)var7);
         }

         if(var9 == 1) {
            var7 = new GenLayerShore(1000L, (GenLayer)var7);
         }
      }

      GenLayerSmooth var25 = new GenLayerSmooth(1000L, (GenLayer)var7);
      GenLayerRiverMix var26 = new GenLayerRiverMix(100L, var25, var20);
      GenLayerVoronoiZoom var10 = new GenLayerVoronoiZoom(10L, var26);
      var26.initWorldGenSeed(var0);
      var10.initWorldGenSeed(var0);
      return new GenLayer[]{var26, var10, var26};
   }

   public GenLayer(long var1) {
      this.baseSeed = var1;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += var1;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += var1;
      this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
      this.baseSeed += var1;
   }

   public void initWorldGenSeed(long var1) {
      this.worldGenSeed = var1;
      if(this.parent != null) {
         this.parent.initWorldGenSeed(var1);
      }

      this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGenSeed += this.baseSeed;
      this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGenSeed += this.baseSeed;
      this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
      this.worldGenSeed += this.baseSeed;
   }

   public void initChunkSeed(long var1, long var3) {
      this.chunkSeed = this.worldGenSeed;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var1;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var3;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var1;
      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += var3;
   }

   protected int nextInt(int var1) {
      int var2 = (int)((this.chunkSeed >> 24) % (long)var1);
      if(var2 < 0) {
         var2 += var1;
      }

      this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
      this.chunkSeed += this.worldGenSeed;
      return var2;
   }

   public abstract int[] getInts(int var1, int var2, int var3, int var4);

   protected static boolean compareBiomesById(int var0, int var1) {
      if(var0 == var1) {
         return true;
      } else if(var0 != BiomeGenBase.mesaPlateau_F.biomeID && var0 != BiomeGenBase.mesaPlateau.biomeID) {
         try {
            return BiomeGenBase.getBiome(var0) != null && BiomeGenBase.getBiome(var1) != null?BiomeGenBase.getBiome(var0).isEqualTo(BiomeGenBase.getBiome(var1)):false;
         } catch (Throwable var5) {
            CrashReport var3 = CrashReport.makeCrashReport(var5, "Comparing biomes");
            CrashReportCategory var4 = var3.makeCategory("Biomes being compared");
            var4.addCrashSection("Biome A ID", Integer.valueOf(var0));
            var4.addCrashSection("Biome B ID", Integer.valueOf(var1));
            var4.addCrashSectionCallable("Biome A", new GenLayer$1(var0));
            var4.addCrashSectionCallable("Biome B", new GenLayer$2(var1));
            throw new ReportedException(var3);
         }
      } else {
         return var1 == BiomeGenBase.mesaPlateau_F.biomeID || var1 == BiomeGenBase.mesaPlateau.biomeID;
      }
   }

   protected static boolean isBiomeOceanic(int var0) {
      return var0 == BiomeGenBase.ocean.biomeID || var0 == BiomeGenBase.deepOcean.biomeID || var0 == BiomeGenBase.frozenOcean.biomeID;
   }

   protected int selectRandom(int ... var1) {
      return var1[this.nextInt(var1.length)];
   }

   protected int selectModeOrRandom(int var1, int var2, int var3, int var4) {
      return var2 == var3 && var3 == var4?var2:(var1 == var2 && var1 == var3?var1:(var1 == var2 && var1 == var4?var1:(var1 == var3 && var1 == var4?var1:(var1 == var2 && var3 != var4?var1:(var1 == var3 && var2 != var4?var1:(var1 == var4 && var2 != var3?var1:(var2 == var3 && var1 != var4?var2:(var2 == var4 && var1 != var3?var2:(var3 == var4 && var1 != var2?var3:this.selectRandom(new int[]{var1, var2, var3, var4}))))))))));
   }
}
