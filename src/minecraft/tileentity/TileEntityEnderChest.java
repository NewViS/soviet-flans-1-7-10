package net.minecraft.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnderChest extends TileEntity {

   public float field_145972_a;
   public float field_145975_i;
   public int field_145973_j;
   private int field_145974_k;


   public void updateEntity() {
      super.updateEntity();
      if(++this.field_145974_k % 20 * 4 == 0) {
         super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, Blocks.ender_chest, 1, this.field_145973_j);
      }

      this.field_145975_i = this.field_145972_a;
      float var1 = 0.1F;
      double var4;
      if(this.field_145973_j > 0 && this.field_145972_a == 0.0F) {
         double var2 = (double)super.xCoord + 0.5D;
         var4 = (double)super.zCoord + 0.5D;
         super.worldObj.playSoundEffect(var2, (double)super.yCoord + 0.5D, var4, "random.chestopen", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
      }

      if(this.field_145973_j == 0 && this.field_145972_a > 0.0F || this.field_145973_j > 0 && this.field_145972_a < 1.0F) {
         float var8 = this.field_145972_a;
         if(this.field_145973_j > 0) {
            this.field_145972_a += var1;
         } else {
            this.field_145972_a -= var1;
         }

         if(this.field_145972_a > 1.0F) {
            this.field_145972_a = 1.0F;
         }

         float var3 = 0.5F;
         if(this.field_145972_a < var3 && var8 >= var3) {
            var4 = (double)super.xCoord + 0.5D;
            double var6 = (double)super.zCoord + 0.5D;
            super.worldObj.playSoundEffect(var4, (double)super.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
         }

         if(this.field_145972_a < 0.0F) {
            this.field_145972_a = 0.0F;
         }
      }

   }

   public boolean receiveClientEvent(int var1, int var2) {
      if(var1 == 1) {
         this.field_145973_j = var2;
         return true;
      } else {
         return super.receiveClientEvent(var1, var2);
      }
   }

   public void invalidate() {
      this.updateContainingBlockInfo();
      super.invalidate();
   }

   public void func_145969_a() {
      ++this.field_145973_j;
      super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, Blocks.ender_chest, 1, this.field_145973_j);
   }

   public void func_145970_b() {
      --this.field_145973_j;
      super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, Blocks.ender_chest, 1, this.field_145973_j);
   }

   public boolean func_145971_a(EntityPlayer var1) {
      return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:var1.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }
}
