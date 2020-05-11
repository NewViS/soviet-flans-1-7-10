package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagEnd extends NBTBase {

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {}

   void write(DataOutput var1) {}

   public byte getId() {
      return (byte)0;
   }

   public String toString() {
      return "END";
   }

   public NBTBase copy() {
      return new NBTTagEnd();
   }
}
