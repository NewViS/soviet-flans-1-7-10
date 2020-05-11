package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagIntArray;

public class NBTTagList extends NBTBase {

   private List tagList = new ArrayList();
   private byte tagType = 0;


   void write(DataOutput var1) {
      if(!this.tagList.isEmpty()) {
         this.tagType = ((NBTBase)this.tagList.get(0)).getId();
      } else {
         this.tagType = 0;
      }

      var1.writeByte(this.tagType);
      var1.writeInt(this.tagList.size());

      for(int var2 = 0; var2 < this.tagList.size(); ++var2) {
         ((NBTBase)this.tagList.get(var2)).write(var1);
      }

   }

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {
      if(var2 > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         var3.func_152450_a(8L);
         this.tagType = var1.readByte();
         int var4 = var1.readInt();
         this.tagList = new ArrayList();

         for(int var5 = 0; var5 < var4; ++var5) {
            NBTBase var6 = NBTBase.func_150284_a(this.tagType);
            var6.func_152446_a(var1, var2 + 1, var3);
            this.tagList.add(var6);
         }

      }
   }

   public byte getId() {
      return (byte)9;
   }

   public String toString() {
      String var1 = "[";
      int var2 = 0;

      for(Iterator var3 = this.tagList.iterator(); var3.hasNext(); ++var2) {
         NBTBase var4 = (NBTBase)var3.next();
         var1 = var1 + "" + var2 + ':' + var4 + ',';
      }

      return var1 + "]";
   }

   public void appendTag(NBTBase var1) {
      if(this.tagType == 0) {
         this.tagType = var1.getId();
      } else if(this.tagType != var1.getId()) {
         System.err.println("WARNING: Adding mismatching tag types to tag list");
         return;
      }

      this.tagList.add(var1);
   }

   public void func_150304_a(int var1, NBTBase var2) {
      if(var1 >= 0 && var1 < this.tagList.size()) {
         if(this.tagType == 0) {
            this.tagType = var2.getId();
         } else if(this.tagType != var2.getId()) {
            System.err.println("WARNING: Adding mismatching tag types to tag list");
            return;
         }

         this.tagList.set(var1, var2);
      } else {
         System.err.println("WARNING: index out of bounds to set tag in tag list");
      }
   }

   public NBTBase removeTag(int var1) {
      return (NBTBase)this.tagList.remove(var1);
   }

   public NBTTagCompound getCompoundTagAt(int var1) {
      if(var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = (NBTBase)this.tagList.get(var1);
         return var2.getId() == 10?(NBTTagCompound)var2:new NBTTagCompound();
      } else {
         return new NBTTagCompound();
      }
   }

   public int[] func_150306_c(int var1) {
      if(var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = (NBTBase)this.tagList.get(var1);
         return var2.getId() == 11?((NBTTagIntArray)var2).func_150302_c():new int[0];
      } else {
         return new int[0];
      }
   }

   public double func_150309_d(int var1) {
      if(var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = (NBTBase)this.tagList.get(var1);
         return var2.getId() == 6?((NBTTagDouble)var2).func_150286_g():0.0D;
      } else {
         return 0.0D;
      }
   }

   public float func_150308_e(int var1) {
      if(var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = (NBTBase)this.tagList.get(var1);
         return var2.getId() == 5?((NBTTagFloat)var2).func_150288_h():0.0F;
      } else {
         return 0.0F;
      }
   }

   public String getStringTagAt(int var1) {
      if(var1 >= 0 && var1 < this.tagList.size()) {
         NBTBase var2 = (NBTBase)this.tagList.get(var1);
         return var2.getId() == 8?var2.func_150285_a_():var2.toString();
      } else {
         return "";
      }
   }

   public int tagCount() {
      return this.tagList.size();
   }

   public NBTBase copy() {
      NBTTagList var1 = new NBTTagList();
      var1.tagType = this.tagType;
      Iterator var2 = this.tagList.iterator();

      while(var2.hasNext()) {
         NBTBase var3 = (NBTBase)var2.next();
         NBTBase var4 = var3.copy();
         var1.tagList.add(var4);
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         NBTTagList var2 = (NBTTagList)var1;
         if(this.tagType == var2.tagType) {
            return this.tagList.equals(var2.tagList);
         }
      }

      return false;
   }

   public int hashCode() {
      return super.hashCode() ^ this.tagList.hashCode();
   }

   public int func_150303_d() {
      return this.tagType;
   }
}
