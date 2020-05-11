package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStronghold$Start;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs2;

public class MapGenStronghold extends MapGenStructure {

   private List field_151546_e;
   private boolean ranBiomeCheck;
   private ChunkCoordIntPair[] structureCoords;
   private double field_82671_h;
   private int field_82672_i;


   public MapGenStronghold() {
      this.structureCoords = new ChunkCoordIntPair[3];
      this.field_82671_h = 32.0D;
      this.field_82672_i = 3;
      this.field_151546_e = new ArrayList();
      BiomeGenBase[] var1 = BiomeGenBase.getBiomeGenArray();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         BiomeGenBase var4 = var1[var3];
         if(var4 != null && var4.rootHeight > 0.0F) {
            this.field_151546_e.add(var4);
         }
      }

   }

   public MapGenStronghold(Map var1) {
      this();
      Iterator var2 = var1.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         if(((String)var3.getKey()).equals("distance")) {
            this.field_82671_h = MathHelper.parseDoubleWithDefaultAndMax((String)var3.getValue(), this.field_82671_h, 1.0D);
         } else if(((String)var3.getKey()).equals("count")) {
            this.structureCoords = new ChunkCoordIntPair[MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.structureCoords.length, 1)];
         } else if(((String)var3.getKey()).equals("spread")) {
            this.field_82672_i = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82672_i, 1);
         }
      }

   }

   public String func_143025_a() {
      return "Stronghold";
   }

   protected boolean canSpawnStructureAtCoords(int var1, int var2) {
      if(!this.ranBiomeCheck) {
         Random var3 = new Random();
         var3.setSeed(super.worldObj.getSeed());
         double var4 = var3.nextDouble() * 3.141592653589793D * 2.0D;
         int var6 = 1;

         for(int var7 = 0; var7 < this.structureCoords.length; ++var7) {
            double var8 = (1.25D * (double)var6 + var3.nextDouble()) * this.field_82671_h * (double)var6;
            int var10 = (int)Math.round(Math.cos(var4) * var8);
            int var11 = (int)Math.round(Math.sin(var4) * var8);
            ChunkPosition var12 = super.worldObj.getWorldChunkManager().findBiomePosition((var10 << 4) + 8, (var11 << 4) + 8, 112, this.field_151546_e, var3);
            if(var12 != null) {
               var10 = var12.chunkPosX >> 4;
               var11 = var12.chunkPosZ >> 4;
            }

            this.structureCoords[var7] = new ChunkCoordIntPair(var10, var11);
            var4 += 6.283185307179586D * (double)var6 / (double)this.field_82672_i;
            if(var7 == this.field_82672_i) {
               var6 += 2 + var3.nextInt(5);
               this.field_82672_i += 1 + var3.nextInt(2);
            }
         }

         this.ranBiomeCheck = true;
      }

      ChunkCoordIntPair[] var13 = this.structureCoords;
      int var14 = var13.length;

      for(int var5 = 0; var5 < var14; ++var5) {
         ChunkCoordIntPair var15 = var13[var5];
         if(var1 == var15.chunkXPos && var2 == var15.chunkZPos) {
            return true;
         }
      }

      return false;
   }

   protected List getCoordList() {
      ArrayList var1 = new ArrayList();
      ChunkCoordIntPair[] var2 = this.structureCoords;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ChunkCoordIntPair var5 = var2[var4];
         if(var5 != null) {
            var1.add(var5.func_151349_a(64));
         }
      }

      return var1;
   }

   protected StructureStart getStructureStart(int var1, int var2) {
      MapGenStronghold$Start var3;
      for(var3 = new MapGenStronghold$Start(super.worldObj, super.rand, var1, var2); var3.getComponents().isEmpty() || ((StructureStrongholdPieces$Stairs2)var3.getComponents().get(0)).strongholdPortalRoom == null; var3 = new MapGenStronghold$Start(super.worldObj, super.rand, var1, var2)) {
         ;
      }

      return var3;
   }
}
