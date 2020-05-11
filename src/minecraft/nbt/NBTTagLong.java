package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTBase$NBTPrimitive;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagLong extends NBTBase$NBTPrimitive {

   private long data;


   NBTTagLong() {}

   public NBTTagLong(long var1) {
      this.data = var1;
   }

   void write(DataOutput var1) {
      var1.writeLong(this.data);
   }

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.func_152450_a(64L);
      this.data = var1.readLong();
   }

   public byte getId() {
      return (byte)4;
   }

   public String toString() {
      return "" + this.data + "L";
   }

   public NBTBase copy() {
      return new NBTTagLong(this.data);
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         NBTTagLong var2 = (NBTTagLong)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return super.hashCode() ^ (int)(this.data ^ this.data >>> 32);
   }

   public long func_150291_c() {
      return this.data;
   }

   public int func_150287_d() {
      return (int)(this.data & -1L);
   }

   public short func_150289_e() {
      return (short)((int)(this.data & 65535L));
   }

   public byte func_150290_f() {
      return (byte)((int)(this.data & 255L));
   }

   public double func_150286_g() {
      return (double)this.data;
   }

   public float func_150288_h() {
      return (float)this.data;
   }
}
