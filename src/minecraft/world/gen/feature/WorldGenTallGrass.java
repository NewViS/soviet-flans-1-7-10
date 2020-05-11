package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTallGrass extends WorldGenerator {

   private Block field_150522_a;
   private int tallGrassMetadata;


   public WorldGenTallGrass(Block var1, int var2) {
      this.field_150522_a = var1;
      this.tallGrassMetadata = var2;
   }

   public boolean generate(World var1, Random var2, int var3, int var4, int var5) {
      Block var6;
      while(((var6 = var1.getBlock(var3, var4, var5)).getMaterial() == Material.air || var6.getMaterial() == Material.leaves) && var4 > 0) {
         --var4;
      }

      for(int var7 = 0; var7 < 128; ++var7) {
         int var8 = var3 + var2.nextInt(8) - var2.nextInt(8);
         int var9 = var4 + var2.nextInt(4) - var2.nextInt(4);
         int var10 = var5 + var2.nextInt(8) - var2.nextInt(8);
         if(var1.isAirBlock(var8, var9, var10) && this.field_150522_a.canBlockStay(var1, var8, var9, var10)) {
            var1.setBlock(var8, var9, var10, this.field_150522_a, this.tallGrassMetadata, 2);
         }
      }

      return true;
   }
}