package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagByteArray extends NBTBase {

   private byte[] byteArray;


   NBTTagByteArray() {}

   public NBTTagByteArray(byte[] var1) {
      this.byteArray = var1;
   }

   void write(DataOutput var1) {
      var1.writeInt(this.byteArray.length);
      var1.write(this.byteArray);
   }

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {
      int var4 = var1.readInt();
      var3.func_152450_a((long)(8 * var4));
      this.byteArray = new byte[var4];
      var1.readFully(this.byteArray);
   }

   public byte getId() {
      return (byte)7;
   }

   public String toString() {
      return "[" + this.byteArray.length + " bytes]";
   }

   public NBTBase copy() {
      byte[] var1 = new byte[this.byteArray.length];
      System.arraycopy(this.byteArray, 0, var1, 0, this.byteArray.length);
      return new NBTTagByteArray(var1);
   }

   public boolean equals(Object var1) {
      return super.equals(var1)?Arrays.equals(this.byteArray, ((NBTTagByteArray)var1).byteArray):false;
   }

   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.byteArray);
   }

   public byte[] func_150292_c() {
      return this.byteArray;
   }
}
