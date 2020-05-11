package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTBase$NBTPrimitive;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.util.MathHelper;

public class NBTTagDouble extends NBTBase$NBTPrimitive {

   private double data;


   NBTTagDouble() {}

   public NBTTagDouble(double var1) {
      this.data = var1;
   }

   void write(DataOutput var1) {
      var1.writeDouble(this.data);
   }

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.func_152450_a(64L);
      this.data = var1.readDouble();
   }

   public byte getId() {
      return (byte)6;
   }

   public String toString() {
      return "" + this.data + "d";
   }

   public NBTBase copy() {
      return new NBTTagDouble(this.data);
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         NBTTagDouble var2 = (NBTTagDouble)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   public int hashCode() {
      long var1 = Double.doubleToLongBits(this.data);
      return super.hashCode() ^ (int)(var1 ^ var1 >>> 32);
   }

   public long func_150291_c() {
      return (long)Math.floor(this.data);
   }

   public int func_150287_d() {
      return MathHelper.floor_double(this.data);
   }

   public short func_150289_e() {
      return (short)(MathHelper.floor_double(this.data) & '\uffff');
   }

   public byte func_150290_f() {
      return (byte)(MathHelper.floor_double(this.data) & 255);
   }

   public double func_150286_g() {
      return this.data;
   }

   public float func_150288_h() {
      return (float)this.data;
   }
}
