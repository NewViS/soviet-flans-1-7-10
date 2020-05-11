package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSpikes extends WorldGenerator {

   private Block field_150520_a;


   public WorldGenSpikes(Block var1) {
      this.field_150520_a = var1;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      if(var1.isAirBlock(var3, var4, var5) && var1.getBlock(var3, var4 - 1, var5) == this.field_150520_a) {
         int var6 = var2.nextInt(32) + 6;
         int var7 = var2.nextInt(4) + 1;

         int var8;
         int var9;
         int var10;
         int var11;
         for(var8 = var3 - var7; var8 <= var3 + var7; ++var8) {
            for(var9 = var5 - var7; var9 <= var5 + var7; ++var9) {
               var10 = var8 - var3;
               var11 = var9 - var5;
               if(var10 * var10 + var11 * var11 <= var7 * var7 + 1 && var1.getBlock(var8, var4 - 1, var9) != this.field_150520_a) {
                  return false;
               }
            }
         }

         for(var8 = var4; var8 < var4 + var6 && var8 < 256; ++var8) {
            for(var9 = var3 - var7; var9 <= var3 + var7; ++var9) {
               for(var10 = var5 - var7; var10 <= var5 + var7; ++var10) {
                  var11 = var9 - var3;
                  int var12 = var10 - var5;
                  if(var11 * var11 + var12 * var12 <= var7 * var7 + 1) {
                     var1.setBlock(var9, var8, var10, Blocks.obsidian, 0, 2);
                  }
               }
            }
         }

         EntityEnderCrystal var13 = new EntityEnderCrystal(var1);
         var13.setLocationAndAngles((double)((float)var3 + 0.5F), (double)(var4 + var6), (double)((float)var5 + 0.5F), var2.nextFloat() * 360.0F, 0.0F);
         var1.spawnEntityInWorld(var13);
         var1.setBlock(var3, var4 + var6, var5, Blocks.bedrock, 0, 2);
         return true;
      } else {
         return false;
      }
   }
}
