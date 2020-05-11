package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTBase$NBTPrimitive;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound$1;
import net.minecraft.nbt.NBTTagCompound$2;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ReportedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NBTTagCompound extends NBTBase {

   private static final Logger logger = LogManager.getLogger();
   private Map tagMap = new HashMap();


   void write(DataOutput var1) {
      Iterator var2 = this.tagMap.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         NBTBase var4 = (NBTBase)this.tagMap.get(var3);
         func_150298_a(var3, var4, var1);
      }

      var1.writeByte(0);
   }

   void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) {
      if(var2 > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.tagMap.clear();

         byte var4;
         while((var4 = func_152447_a(var1, var3)) != 0) {
            String var5 = func_152448_b(var1, var3);
            var3.func_152450_a((long)(16 * var5.length()));
            NBTBase var6 = func_152449_a(var4, var5, var1, var2 + 1, var3);
            this.tagMap.put(var5, var6);
         }

      }
   }

   public Set func_150296_c() {
      return this.tagMap.keySet();
   }

   public byte getId() {
      return (byte)10;
   }

   public void setTag(String var1, NBTBase var2) {
      this.tagMap.put(var1, var2);
   }

   public void setByte(String var1, byte var2) {
      this.tagMap.put(var1, new NBTTagByte(var2));
   }

   public void setShort(String var1, short var2) {
      this.tagMap.put(var1, new NBTTagShort(var2));
   }

   public void setInteger(String var1, int var2) {
      this.tagMap.put(var1, new NBTTagInt(var2));
   }

   public void setLong(String var1, long var2) {
      this.tagMap.put(var1, new NBTTagLong(var2));
   }

   public void setFloat(String var1, float var2) {
      this.tagMap.put(var1, new NBTTagFloat(var2));
   }

   public void setDouble(String var1, double var2) {
      this.tagMap.put(var1, new NBTTagDouble(var2));
   }

   public void setString(String var1, String var2) {
      this.tagMap.put(var1, new NBTTagString(var2));
   }

   public void setByteArray(String var1, byte[] var2) {
      this.tagMap.put(var1, new NBTTagByteArray(var2));
   }

   public void setIntArray(String var1, int[] var2) {
      this.tagMap.put(var1, new NBTTagIntArray(var2));
   }

   public void setBoolean(String var1, boolean var2) {
      this.setByte(var1, (byte)(var2?1:0));
   }

   public NBTBase getTag(String var1) {
      return (NBTBase)this.tagMap.get(var1);
   }

   public byte func_150299_b(String var1) {
      NBTBase var2 = (NBTBase)this.tagMap.get(var1);
      return var2 != null?var2.getId():0;
   }

   public boolean hasKey(String var1) {
      return this.tagMap.containsKey(var1);
   }

   public boolean hasKey(String var1, int var2) {
      byte var3 = this.func_150299_b(var1);
      return var3 == var2?true:(var2 != 99?false:var3 == 1 || var3 == 2 || var3 == 3 || var3 == 4 || var3 == 5 || var3 == 6);
   }

   public byte getByte(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?0:((NBTBase$NBTPrimitive)this.tagMap.get(var1)).func_150290_f();
      } catch (ClassCastException var3) {
         return (byte)0;
      }
   }

   public short getShort(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?0:((NBTBase$NBTPrimitive)this.tagMap.get(var1)).func_150289_e();
      } catch (ClassCastException var3) {
         return (short)0;
      }
   }

   public int getInteger(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?0:((NBTBase$NBTPrimitive)this.tagMap.get(var1)).func_150287_d();
      } catch (ClassCastException var3) {
         return 0;
      }
   }

   public long getLong(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?0L:((NBTBase$NBTPrimitive)this.tagMap.get(var1)).func_150291_c();
      } catch (ClassCastException var3) {
         return 0L;
      }
   }

   public float getFloat(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?0.0F:((NBTBase$NBTPrimitive)this.tagMap.get(var1)).func_150288_h();
      } catch (ClassCastException var3) {
         return 0.0F;
      }
   }

   public double getDouble(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?0.0D:((NBTBase$NBTPrimitive)this.tagMap.get(var1)).func_150286_g();
      } catch (ClassCastException var3) {
         return 0.0D;
      }
   }

   public String getString(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?"":((NBTBase)this.tagMap.get(var1)).func_150285_a_();
      } catch (ClassCastException var3) {
         return "";
      }
   }

   public byte[] getByteArray(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?new byte[0]:((NBTTagByteArray)this.tagMap.get(var1)).func_150292_c();
      } catch (ClassCastException var3) {
         throw new ReportedException(this.createCrashReport(var1, 7, var3));
      }
   }

   public int[] getIntArray(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?new int[0]:((NBTTagIntArray)this.tagMap.get(var1)).func_150302_c();
      } catch (ClassCastException var3) {
         throw new ReportedException(this.createCrashReport(var1, 11, var3));
      }
   }

   public NBTTagCompound getCompoundTag(String var1) {
      try {
         return !this.tagMap.containsKey(var1)?new NBTTagCompound():(NBTTagCompound)this.tagMap.get(var1);
      } catch (ClassCastException var3) {
         throw new ReportedException(this.createCrashReport(var1, 10, var3));
      }
   }

   public NBTTagList getTagList(String var1, int var2) {
      try {
         if(this.func_150299_b(var1) != 9) {
            return new NBTTagList();
         } else {
            NBTTagList var3 = (NBTTagList)this.tagMap.get(var1);
            return var3.tagCount() > 0 && var3.func_150303_d() != var2?new NBTTagList():var3;
         }
      } catch (ClassCastException var4) {
         throw new ReportedException(this.createCrashReport(var1, 9, var4));
      }
   }

   public boolean getBoolean(String var1) {
      return this.getByte(var1) != 0;
   }

   public void removeTag(String var1) {
      this.tagMap.remove(var1);
   }

   public String toString() {
      String var1 = "{";

      String var3;
      for(Iterator var2 = this.tagMap.keySet().iterator(); var2.hasNext(); var1 = var1 + var3 + ':' + this.tagMap.get(var3) + ',') {
         var3 = (String)var2.next();
      }

      return var1 + "}";
   }

   public boolean hasNoTags() {
      return this.tagMap.isEmpty();
   }

   private CrashReport createCrashReport(String var1, int var2, ClassCastException var3) {
      CrashReport var4 = CrashReport.makeCrashReport(var3, "Reading NBT data");
      CrashReportCategory var5 = var4.makeCategoryDepth("Corrupt NBT tag", 1);
      var5.addCrashSectionCallable("Tag type found", new NBTTagCompound$1(this, var1));
      var5.addCrashSectionCallable("Tag type expected", new NBTTagCompound$2(this, var2));
      var5.addCrashSection("Tag name", var1);
      return var4;
   }

   public NBTBase copy() {
      NBTTagCompound var1 = new NBTTagCompound();
      Iterator var2 = this.tagMap.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.setTag(var3, ((NBTBase)this.tagMap.get(var3)).copy());
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if(super.equals(var1)) {
         NBTTagCompound var2 = (NBTTagCompound)var1;
         return this.tagMap.entrySet().equals(var2.tagMap.entrySet());
      } else {
         return false;
      }
   }

   public int hashCode() {
      return super.hashCode() ^ this.tagMap.hashCode();
   }

   private static void func_150298_a(String var0, NBTBase var1, DataOutput var2) {
      var2.writeByte(var1.getId());
      if(var1.getId() != 0) {
         var2.writeUTF(var0);
         var1.write(var2);
      }
   }

   private static byte func_152447_a(DataInput var0, NBTSizeTracker var1) {
      return var0.readByte();
   }

   private static String func_152448_b(DataInput var0, NBTSizeTracker var1) {
      return var0.readUTF();
   }

   static NBTBase func_152449_a(byte var0, String var1, DataInput var2, int var3, NBTSizeTracker var4) {
      NBTBase var5 = NBTBase.func_150284_a(var0);

      try {
         var5.func_152446_a(var2, var3, var4);
         return var5;
      } catch (IOException var9) {
         CrashReport var7 = CrashReport.makeCrashReport(var9, "Loading NBT data");
         CrashReportCategory var8 = var7.makeCategory("NBT Tag");
         var8.addCrashSection("Tag name", var1);
         var8.addCrashSection("Tag type", Byte.valueOf(var0));
         throw new ReportedException(var7);
      }
   }

   // $FF: synthetic method
   static Map access$000(NBTTagCompound var0) {
      return var0.tagMap;
   }

}
