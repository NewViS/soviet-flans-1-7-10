package net.minecraft.nbt;

import java.util.concurrent.Callable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

class NBTTagCompound$1 implements Callable {

   // $FF: synthetic field
   final String field_82585_a;
   // $FF: synthetic field
   final NBTTagCompound theNBTTagCompound;


   NBTTagCompound$1(NBTTagCompound var1, String var2) {
      this.theNBTTagCompound = var1;
      this.field_82585_a = var2;
   }

   public String call() {
      return NBTBase.NBTTypes[((NBTBase)NBTTagCompound.access$000(this.theNBTTagCompound).get(this.field_82585_a)).getId()];
   }

   // $FF: synthetic method
   public Object call() {
      return this.call();
   }
}
