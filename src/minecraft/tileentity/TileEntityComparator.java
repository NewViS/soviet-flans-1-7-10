package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityComparator extends TileEntity {

   private int field_145997_a;


   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setInteger("OutputSignal", this.field_145997_a);
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.field_145997_a = var1.getInteger("OutputSignal");
   }

   public int getOutputSignal() {
      return this.field_145997_a;
   }

   public void setOutputSignal(int var1) {
      this.field_145997_a = var1;
   }
}
