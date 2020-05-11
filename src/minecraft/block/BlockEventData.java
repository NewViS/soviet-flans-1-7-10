package net.minecraft.block;

import net.minecraft.block.Block;

public class BlockEventData {

   private int coordX;
   private int coordY;
   private int coordZ;
   private Block field_151344_d;
   private int eventID;
   private int eventParameter;


   public BlockEventData(int var1, int var2, int var3, Block var4, int var5, int var6) {
      this.coordX = var1;
      this.coordY = var2;
      this.coordZ = var3;
      this.eventID = var5;
      this.eventParameter = var6;
      this.field_151344_d = var4;
   }

   public int func_151340_a() {
      return this.coordX;
   }

   public int func_151342_b() {
      return this.coordY;
   }

   public int func_151341_c() {
      return this.coordZ;
   }

   public int getEventID() {
      return this.eventID;
   }

   public int getEventParameter() {
      return this.eventParameter;
   }

   public Block getBlock() {
      return this.field_151344_d;
   }

   public boolean equals(Object var1) {
      if(!(var1 instanceof BlockEventData)) {
         return false;
      } else {
         BlockEventData var2 = (BlockEventData)var1;
         return this.coordX == var2.coordX && this.coordY == var2.coordY && this.coordZ == var2.coordZ && this.eventID == var2.eventID && this.eventParameter == var2.eventParameter && this.field_151344_d == var2.field_151344_d;
      }
   }

   public String toString() {
      return "TE(" + this.coordX + "," + this.coordY + "," + this.coordZ + ")," + this.eventID + "," + this.eventParameter + "," + this.field_151344_d;
   }
}
