package net.minecraft.world;

import net.minecraft.block.Block;

public class NextTickListEntry implements Comparable {

   private static long nextTickEntryID;
   private final Block field_151352_g;
   public int xCoord;
   public int yCoord;
   public int zCoord;
   public long scheduledTime;
   public int priority;
   private long tickEntryID;


   public NextTickListEntry(int var1, int var2, int var3, Block var4) {
      this.tickEntryID = (long)(nextTickEntryID++);
      this.xCoord = var1;
      this.yCoord = var2;
      this.zCoord = var3;
      this.field_151352_g = var4;
   }

   public boolean equals(Object var1) {
      if(!(var1 instanceof NextTickListEntry)) {
         return false;
      } else {
         NextTickListEntry var2 = (NextTickListEntry)var1;
         return this.xCoord == var2.xCoord && this.yCoord == var2.yCoord && this.zCoord == var2.zCoord && Block.isEqualTo(this.field_151352_g, var2.field_151352_g);
      }
   }

   public int hashCode() {
      return (this.xCoord * 1024 * 1024 + this.zCoord * 1024 + this.yCoord) * 256;
   }

   public NextTickListEntry setScheduledTime(long var1) {
      this.scheduledTime = var1;
      return this;
   }

   public void setPriority(int var1) {
      this.priority = var1;
   }

   public int compareTo(NextTickListEntry var1) {
      return this.scheduledTime < var1.scheduledTime?-1:(this.scheduledTime > var1.scheduledTime?1:(this.priority != var1.priority?this.priority - var1.priority:(this.tickEntryID < var1.tickEntryID?-1:(this.tickEntryID > var1.tickEntryID?1:0))));
   }

   public String toString() {
      return Block.getIdFromBlock(this.field_151352_g) + ": (" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + "), " + this.scheduledTime + ", " + this.priority + ", " + this.tickEntryID;
   }

   public Block func_151351_a() {
      return this.field_151352_g;
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.compareTo((NextTickListEntry)var1);
   }
}
