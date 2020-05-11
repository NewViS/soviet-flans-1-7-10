package net.minecraft.nbt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.util.ReportedException;

public class CompressedStreamTools {

   public static NBTTagCompound readCompressed(InputStream var0) {
      DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(var0)));

      NBTTagCompound var2;
      try {
         var2 = func_152456_a(var1, NBTSizeTracker.field_152451_a);
      } finally {
         var1.close();
      }

      return var2;
   }

   public static void writeCompressed(NBTTagCompound var0, OutputStream var1) {
      DataOutputStream var2 = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(var1)));

      try {
         write(var0, (DataOutput)var2);
      } finally {
         var2.close();
      }

   }

   public static NBTTagCompound func_152457_a(byte[] var0, NBTSizeTracker var1) {
      DataInputStream var2 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(var0))));

      NBTTagCompound var3;
      try {
         var3 = func_152456_a(var2, var1);
      } finally {
         var2.close();
      }

      return var3;
   }

   public static byte[] compress(NBTTagCompound var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      DataOutputStream var2 = new DataOutputStream(new GZIPOutputStream(var1));

      try {
         write(var0, (DataOutput)var2);
      } finally {
         var2.close();
      }

      return var1.toByteArray();
   }

   public static void safeWrite(NBTTagCompound var0, File var1) {
      File var2 = new File(var1.getAbsolutePath() + "_tmp");
      if(var2.exists()) {
         var2.delete();
      }

      write(var0, var2);
      if(var1.exists()) {
         var1.delete();
      }

      if(var1.exists()) {
         throw new IOException("Failed to delete " + var1);
      } else {
         var2.renameTo(var1);
      }
   }

   public static void write(NBTTagCompound var0, File var1) {
      DataOutputStream var2 = new DataOutputStream(new FileOutputStream(var1));

      try {
         write(var0, (DataOutput)var2);
      } finally {
         var2.close();
      }

   }

   public static NBTTagCompound read(File var0) {
      return func_152458_a(var0, NBTSizeTracker.field_152451_a);
   }

   public static NBTTagCompound func_152458_a(File var0, NBTSizeTracker var1) {
      if(!var0.exists()) {
         return null;
      } else {
         DataInputStream var2 = new DataInputStream(new FileInputStream(var0));

         NBTTagCompound var3;
         try {
            var3 = func_152456_a(var2, var1);
         } finally {
            var2.close();
         }

         return var3;
      }
   }

   public static NBTTagCompound read(DataInputStream var0) {
      return func_152456_a(var0, NBTSizeTracker.field_152451_a);
   }

   public static NBTTagCompound func_152456_a(DataInput var0, NBTSizeTracker var1) {
      NBTBase var2 = func_152455_a(var0, 0, var1);
      if(var2 instanceof NBTTagCompound) {
         return (NBTTagCompound)var2;
      } else {
         throw new IOException("Root tag must be a named compound tag");
      }
   }

   public static void write(NBTTagCompound var0, DataOutput var1) {
      func_150663_a(var0, var1);
   }

   private static void func_150663_a(NBTBase var0, DataOutput var1) {
      var1.writeByte(var0.getId());
      if(var0.getId() != 0) {
         var1.writeUTF("");
         var0.write(var1);
      }
   }

   private static NBTBase func_152455_a(DataInput var0, int var1, NBTSizeTracker var2) {
      byte var3 = var0.readByte();
      if(var3 == 0) {
         return new NBTTagEnd();
      } else {
         var0.readUTF();
         NBTBase var4 = NBTBase.func_150284_a(var3);

         try {
            var4.func_152446_a(var0, var1, var2);
            return var4;
         } catch (IOException var8) {
            CrashReport var6 = CrashReport.makeCrashReport(var8, "Loading NBT data");
            CrashReportCategory var7 = var6.makeCategory("NBT Tag");
            var7.addCrashSection("Tag name", "[UNNAMED TAG]");
            var7.addCrashSection("Tag type", Byte.valueOf(var3));
            throw new ReportedException(var6);
         }
      }
   }
}
