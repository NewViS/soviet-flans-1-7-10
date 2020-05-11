package net.minecraft.nbt;

import java.util.concurrent.Callable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

class NBTTagCompound$2 implements Callable {

   // $FF: synthetic field
   final int field_82588_a;
   // $FF: synthetic field
   final NBTTagCompound theNBTTagCompound;


   NBTTagCompound$2(NBTTagCompound var1, int var2) {
      this.theNBTTagCompound = var1;
      this.field_82588_a = var2;
   }

   public String call() {
      return NBTBase.NBTTypes[this.field_82588_a];
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
