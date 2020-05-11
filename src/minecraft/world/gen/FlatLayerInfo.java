package net.minecraft.world.gen;

import net.minecraft.block.Block;

public class FlatLayerInfo {

   private Block field_151537_a;
   private int layerCount;
   private int layerFillBlockMeta;
   private int layerMinimumY;


   public FlatLayerInfo(int var1, Block var2) {
      this.layerCount = 1;
      this.layerCount = var1;
      this.field_151537_a = var2;
   }

   public FlatLayerInfo(int var1, Block var2, int var3) {
      this(var1, var2);
      this.layerFillBlockMeta = var3;
   }

   public int getLayerCount() {
      return this.layerCount;
   }

   public Block func_151536_b() {
      return this.field_151537_a;
   }

   public int getFillBlockMeta() {
      return this.layerFillBlockMeta;
   }

   public int getMinY() {
      return this.layerMinimumY;
   }

   public void setMinY(int var1) {
      this.layerMinimumY = var1;
   }

   public String toString() {
      String var1 = Integer.toString(Block.getIdFromBlock(this.field_151537_a));
      if(this.layerCount > 1) {
         var1 = this.layerCount + "x" + var1;
      }

      if(this.layerFillBlockMeta > 0) {
         var1 = var1 + ":" + this.layerFillBlockMeta;
      }

      return var1;
   }
}
