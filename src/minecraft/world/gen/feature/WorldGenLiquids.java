package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLiquids extends WorldGenerator {

   private Block field_150521_a;


   public WorldGenLiquids(Block var1) {
      this.field_150521_a = var1;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      if(var1.getBlock(var3, var4 + 1, var5) != Blocks.stone) {
         return false;
      } else if(var1.getBlock(var3, var4 - 1, var5) != Blocks.stone) {
         return false;
      } else if(var1.getBlock(var3, var4, var5).getMaterial() != Material.air && var1.getBlock(var3, var4, var5) != Blocks.stone) {
         return false;
      } else {
         int var6 = 0;
         if(var1.getBlock(var3 - 1, var4, var5) == Blocks.stone) {
            ++var6;
         }

         if(var1.getBlock(var3 + 1, var4, var5) == Blocks.stone) {
            ++var6;
         }

         if(var1.getBlock(var3, var4, var5 - 1) == Blocks.stone) {
            ++var6;
         }

         if(var1.getBlock(var3, var4, var5 + 1) == Blocks.stone) {
            ++var6;
         }

         int var7 = 0;
         if(var1.isAirBlock(var3 - 1, var4, var5)) {
            ++var7;
         }

         if(var1.isAirBlock(var3 + 1, var4, var5)) {
            ++var7;
         }

         if(var1.isAirBlock(var3, var4, var5 - 1)) {
            ++var7;
         }

         if(var1.isAirBlock(var3, var4, var5 + 1)) {
            ++var7;
         }

         if(var6 == 3 && var7 == 1) {
            var1.setBlock(var3, var4, var5, this.field_150521_a, 0, 2);
            var1.scheduledUpdatesAreImmediate = true;
            this.field_150521_a.updateTick(var1, var3, var4, var5, var2);
            var1.scheduledUpdatesAreImmediate = false;
         }

         return true;
      }
   }
}
