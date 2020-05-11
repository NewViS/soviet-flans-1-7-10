package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenAbstractTree extends WorldGenerator {

   public WorldGenAbstractTree(boolean var1) {
      super(var1);
   }

   protected boolean func_150523_a(Block var1) {
      return var1.getMaterial() == Material.air || var1.getMaterial() == Material.leaves || var1 == Blocks.grass || var1 == Blocks.dirt || var1 == Blocks.log || var1 == Blocks.log2 || var1 == Blocks.sapling || var1 == Blocks.vine;
   }

   public void func_150524_b(World var1, Random var2, int var3, int var4, int var5) {}
}
