package net.minecraft.tileentity;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

class TileEntity$2 implements Callable {

   // $FF: synthetic field
   final TileEntity field_150832_a;


   TileEntity$2(TileEntity var1) {
      this.field_150832_a = var1;
   }

   public String call() {
      int var1 = Block.getIdFromBlock(this.field_150832_a.worldObj.getBlock(this.field_150832_a.xCoord, this.field_150832_a.yCoord, this.field_150832_a.zCoord));

      try {
         return String.format("ID #%d (%s // %s)", new Object[]{Integer.valueOf(var1), Block.getBlockById(var1).getUnlocalizedName(), Block.getBlockById(var1).getClass().getCanonicalName()});
      } catch (Throwable var3) {
         return "ID #" + var1;
      }
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
