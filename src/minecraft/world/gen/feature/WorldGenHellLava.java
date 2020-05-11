package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHellLava extends WorldGenerator {

   private Block field_150553_a;
   private boolean field_94524_b;


   public WorldGenHellLava(Block var1, boolean var2) {
      this.field_150553_a = var1;
      this.field_94524_b = var2;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      if(var1.getBlock(var3, var4 + 1, var5) != Blocks.netherrack) {
         return false;
      } else if(var1.getBlock(var3, var4, var5).getMaterial() != Material.air && var1.getBlock(var3, var4, var5) != Blocks.netherrack) {
         return false;
      } else {
         int var6 = 0;
         if(var1.getBlock(var3 - 1, var4, var5) == Blocks.netherrack) {
            ++var6;
         }

         if(var1.getBlock(var3 + 1, var4, var5) == Blocks.netherrack) {
            ++var6;
         }

         if(var1.getBlock(var3, var4, var5 - 1) == Blocks.netherrack) {
            ++var6;
         }

         if(var1.getBlock(var3, var4, var5 + 1) == Blocks.netherrack) {
            ++var6;
         }

         if(var1.getBlock(var3, var4 - 1, var5) == Blocks.netherrack) {
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

         if(var1.isAirBlock(var3, var4 - 1, var5)) {
            ++var7;
         }

         if(!this.field_94524_b && var6 == 4 && var7 == 1 || var6 == 5) {
            var1.setBlock(var3, var4, var5, this.field_150553_a, 0, 2);
            var1.scheduledUpdatesAreImmediate = true;
            this.field_150553_a.updateTick(var1, var3, var4, var5, var2);
            var1.scheduledUpdatesAreImmediate = false;
         }

         return true;
      }
   }
}
