package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagString extends NBTBase {

   private String data;


   public NBTTagString() {
      this.data = "";
   }

   public NBTTagString(String var1) {
      this.data = var1;
      if(var1 == null) {
         throw new IllegalArgumentException("Empty string not allowed");
      }
   }

   void write(DataOutput var1) {
      var1.writeUTF(this.data);
   }

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {
      this.data = var1.readUTF();
      var3.func_152450_a((long)(16 * this.data.length()));
   }

   public byte getId() {
      return (byte)8;
   }

   public String toString() {
      return "\"" + this.data + "\"";
   }

   public NBTBase copy() {
      return new NBTTagString(this.data);
   }

   public boolean equals(Object var1) {
      if(!super.equals(var1)) {
         return false;
      } else {
         NBTTagString var2 = (NBTTagString)var1;
         return this.data == null && var2.data == null || this.data != null && this.data.equals(var2.data);
      }
   }

   public int hashCode() {
      return super.hashCode() ^ this.data.hashCode();
   }

   public String func_150285_a_() {
      return this.data;
   }
}
