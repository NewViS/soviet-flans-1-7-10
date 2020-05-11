package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTBase$NBTPrimitive;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagInt extends NBTBase$NBTPrimitive {

   private int data;


   NBTTagInt() {}

   public NBTTagInt(int var1) {
      this.data = var1;
   }

   void write(DataOutput var1) {
      var1.writeInt(this.data);
   }

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.func_152450_a(32L);
      this.data = var1.readInt();
   }

   public byte getId() {
      return (byte)3;
   }

   public String toString() {
      return "" + this.data;
   }

   public NBTBase copy() {
      return new NBTTagInt(this.data);
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         NBTTagInt var2 = (NBTTagInt)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   public long func_150291_c() {
      return (long)this.data;
   }

   public int func_150287_d() {
      return this.data;
   }

   public short func_150289_e() {
      return (short)(this.data & '\uffff');
   }

   public byte func_150290_f() {
      return (byte)(this.data & 255);
   }

   public double func_150286_g() {
      return (double)this.data;
   }

   public float func_150288_h() {
      return (float)this.data;
   }
}
