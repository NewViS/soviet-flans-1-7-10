package net.minecraft.tileentity;

import java.util.concurrent.Callable;
import net.minecraft.tileentity.TileEntity;

class TileEntity$3 implements Callable {

   // $FF: synthetic field
   final TileEntity field_150834_a;


   TileEntity$3(TileEntity var1) {
      this.field_150834_a = var1;
   }

   public String call() {
      int var1 = this.field_150834_a.worldObj.getBlockMetadata(this.field_150834_a.xCoord, this.field_150834_a.yCoord, this.field_150834_a.zCoord);
      if(var1 < 0) {
         return "Unknown? (Got " + var1 + ")";
      } else {
         String var2 = String.format("%4s", new Object[]{Integer.toBinaryString(var1)}).replace(" ", "0");
         return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[]{Integer.valueOf(var1), var2});
      }
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
