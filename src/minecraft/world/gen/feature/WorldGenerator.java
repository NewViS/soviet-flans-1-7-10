package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public abstract class WorldGenerator {

   private final boolean doBlockNotify;


   public WorldGenerator() {
      this.doBlockNotify = false;
   }

   public WorldGenerator(boolean var1) {
      this.doBlockNotify = var1;
   }

   public abstract boolean generate(World var1, Random var2, int var3, int var4, int var5);

   public void setScale(double var1, double var3, double var5) {}

   protected void func_150515_a(World var1, int var2, int var3, int var4, Block var5) {
      this.setBlockAndNotifyAdequately(var1, var2, var3, var4, var5, 0);
   }

   protected void setBlockAndNotifyAdequately(World var1, int var2, int var3, int var4, Block var5, int var6) {
      if(this.doBlockNotify) {
         var1.setBlock(var2, var3, var4, var5, var6, 3);
      } else {
         var1.setBlock(var2, var3, var4, var5, var6, 2);
      }

   }
}
