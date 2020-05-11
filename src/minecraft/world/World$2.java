package net.minecraft.world;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.world.World;

class World$2 implements Callable {

   // $FF: synthetic field
   final Block field_151300_a;
   // $FF: synthetic field
   final World theWorld;


   World$2(World var1, Block var2) {
      this.theWorld = var1;
      this.field_151300_a = var2;
   }

   public String call() {
      try {
         return String.format("ID #%d (%s // %s)", new Object[]{Integer.valueOf(Block.getIdFromBlock(this.field_151300_a)), this.field_151300_a.getUnlocalizedName(), this.field_151300_a.getClass().getCanonicalName()});
      } catch (Throwable var2) {
         return "ID #" + Block.getIdFromBlock(this.field_151300_a);
      }
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
