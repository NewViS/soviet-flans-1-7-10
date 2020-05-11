package net.minecraft.world.chunk.storage;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.storage.AnvilSaveConverter$1;
import net.minecraft.world.chunk.storage.AnvilSaveHandler;
import net.minecraft.world.chunk.storage.ChunkLoader;
import net.minecraft.world.chunk.storage.ChunkLoader$AnvilConverterData;
import net.minecraft.world.chunk.storage.RegionFile;
import net.minecraft.world.chunk.storage.RegionFileCache;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.SaveFormatOld;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilSaveConverter extends SaveFormatOld {

   private static final Logger logger = LogManager.getLogger();


   public AnvilSaveConverter(File var1) {
      super(var1);
   }

   public String func_154333_a() {
      return "Anvil";
   }

   public List getSaveList() {
      if(super.savesDirectory != null && super.savesDirectory.exists() && super.savesDirectory.isDirectory()) {
         ArrayList var1 = new ArrayList();
         File[] var2 = super.savesDirectory.listFiles();
         File[] var3 = var2;
         int var4 = var2.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File var6 = var3[var5];
            if(var6.isDirectory()) {
               String var7 = var6.getName();
               WorldInfo var8 = this.getWorldInfo(var7);
               if(var8 != null && (var8.getSaveVersion() == 19132 || var8.getSaveVersion() == 19133)) {
                  boolean var9 = var8.getSaveVersion() != this.getSaveVersion();
                  String var10 = var8.getWorldName();
                  if(var10 == null || MathHelper.stringNullOrLengthZero(var10)) {
                     var10 = var7;
                  }

                  long var11 = 0L;
                  var1.add(new SaveFormatComparator(var7, var10, var8.getLastTimePlayed(), var11, var8.getGameType(), var9, var8.isHardcoreModeEnabled(), var8.areCommandsAllowed()));
               }
            }
         }

         return var1;
      } else {
         throw new AnvilConverterException("Unable to read or access folder where game worlds are saved!");
      }
   }

   protected int getSaveVersion() {
      return 19133;
   }

   public void flushCache() {
      RegionFileCache.clearRegionFileReferences();
   }

   public ISaveHandler getSaveLoader(String var1, boolean var2) {
      return new AnvilSaveHandler(super.savesDirectory, var1, var2);
   }

   public boolean func_154334_a(String var1) {
      WorldInfo var2 = this.getWorldInfo(var1);
      return var2 != null && var2.getSaveVersion() == 19132;
   }

   public boolean isOldMapFormat(String var1) {
      WorldInfo var2 = this.getWorldInfo(var1);
      return var2 != null && var2.getSaveVersion() != this.getSaveVersion();
   }

   public boolean convertMapFormat(String var1, IProgressUpdate var2) {
      var2.setLoadingProgress(0);
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      File var6 = new File(super.savesDirectory, var1);
      File var7 = new File(var6, "DIM-1");
      File var8 = new File(var6, "DIM1");
      logger.info("Scanning folders...");
      this.addRegionFilesToCollection(var6, var3);
      if(var7.exists()) {
         this.addRegionFilesToCollection(var7, var4);
      }

      if(var8.exists()) {
         this.addRegionFilesToCollection(var8, var5);
      }

      int var9 = var3.size() + var4.size() + var5.size();
      logger.info("Total conversion count is " + var9);
      WorldInfo var10 = this.getWorldInfo(var1);
      Object var11 = null;
      if(var10.getTerrainType() == WorldType.FLAT) {
         var11 = new WorldChunkManagerHell(BiomeGenBase.plains, 0.5F);
      } else {
         var11 = new WorldChunkManager(var10.getSeed(), var10.getTerrainType());
      }

      this.convertFile(new File(var6, "region"), var3, (WorldChunkManager)var11, 0, var9, var2);
      this.convertFile(new File(var7, "region"), var4, new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F), var3.size(), var9, var2);
      this.convertFile(new File(var8, "region"), var5, new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F), var3.size() + var4.size(), var9, var2);
      var10.setSaveVersion(19133);
      if(var10.getTerrainType() == WorldType.DEFAULT_1_1) {
         var10.setTerrainType(WorldType.DEFAULT);
      }

      this.createFile(var1);
      ISaveHandler var12 = this.getSaveLoader(var1, false);
      var12.saveWorldInfo(var10);
      return true;
   }

   private void createFile(String var1) {
      File var2 = new File(super.savesDirectory, var1);
      if(!var2.exists()) {
         logger.warn("Unable to create level.dat_mcr backup");
      } else {
         File var3 = new File(var2, "level.dat");
         if(!var3.exists()) {
            logger.warn("Unable to create level.dat_mcr backup");
         } else {
            File var4 = new File(var2, "level.dat_mcr");
            if(!var3.renameTo(var4)) {
               logger.warn("Unable to create level.dat_mcr backup");
            }

         }
      }
   }

   private void convertFile(File var1, Iterable var2, WorldChunkManager var3, int var4, int var5, IProgressUpdate var6) {
      Iterator var7 = var2.iterator();

      while(var7.hasNext()) {
         File var8 = (File)var7.next();
         this.convertChunks(var1, var8, var3, var4, var5, var6);
         ++var4;
         int var9 = (int)Math.round(100.0D * (double)var4 / (double)var5);
         var6.setLoadingProgress(var9);
      }

   }

   private void convertChunks(File var1, File var2, WorldChunkManager var3, int var4, int var5, IProgressUpdate var6) {
      try {
         String var7 = var2.getName();
         RegionFile var8 = new RegionFile(var2);
         RegionFile var9 = new RegionFile(new File(var1, var7.substring(0, var7.length() - ".mcr".length()) + ".mca"));

         for(int var10 = 0; var10 < 32; ++var10) {
            int var11;
            for(var11 = 0; var11 < 32; ++var11) {
               if(var8.isChunkSaved(var10, var11) && !var9.isChunkSaved(var10, var11)) {
                  DataInputStream var12 = var8.getChunkDataInputStream(var10, var11);
                  if(var12 == null) {
                     logger.warn("Failed to fetch input stream");
                  } else {
                     NBTTagCompound var13 = CompressedStreamTools.read(var12);
                     var12.close();
                     NBTTagCompound var14 = var13.getCompoundTag("Level");
                     ChunkLoader$AnvilConverterData var15 = ChunkLoader.load(var14);
                     NBTTagCompound var16 = new NBTTagCompound();
                     NBTTagCompound var17 = new NBTTagCompound();
                     var16.setTag("Level", var17);
                     ChunkLoader.convertToAnvilFormat(var15, var17, var3);
                     DataOutputStream var18 = var9.getChunkDataOutputStream(var10, var11);
                     CompressedStreamTools.write(var16, (DataOutput)var18);
                     var18.close();
                  }
               }
            }

            var11 = (int)Math.round(100.0D * (double)(var4 * 1024) / (double)(var5 * 1024));
            int var20 = (int)Math.round(100.0D * (double)((var10 + 1) * 32 + var4 * 1024) / (double)(var5 * 1024));
            if(var20 > var11) {
               var6.setLoadingProgress(var20);
            }
         }

         var8.close();
         var9.close();
      } catch (IOException var19) {
         var19.printStackTrace();
      }

   }

   private void addRegionFilesToCollection(File var1, Collection var2) {
      File var3 = new File(var1, "region");
      File[] var4 = var3.listFiles(new AnvilSaveConverter$1(this));
      if(var4 != null) {
         Collections.addAll(var2, var4);
      }

   }

}
