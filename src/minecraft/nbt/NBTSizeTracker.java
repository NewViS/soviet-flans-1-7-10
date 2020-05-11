package net.minecraft.nbt;

import net.minecraft.nbt.NBTSizeTracker$1;

public class NBTSizeTracker {

   public static final NBTSizeTracker field_152451_a = new NBTSizeTracker$1(0L);
   private final long field_152452_b;
   private long field_152453_c;


   public NBTSizeTracker(long var1) {
      this.field_152452_b = var1;
   }

   public void func_152450_a(long var1) {
      this.field_152453_c += var1 / 8L;
      if(this.field_152453_c > this.field_152452_b) {
         throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.field_152453_c + "bytes where max allowed: " + this.field_152452_b);
      }
   }

}
