package net.minecraft.tileentity;

import java.util.concurrent.Callable;
import net.minecraft.tileentity.TileEntity;

class TileEntity$1 implements Callable {

   // $FF: synthetic field
   final TileEntity field_150830_a;


   TileEntity$1(TileEntity var1) {
      this.field_150830_a = var1;
   }

   public String call() {
      return (String)TileEntity.access$000().get(this.field_150830_a.getClass()) + " // " + this.field_150830_a.getClass().getCanonicalName();
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
