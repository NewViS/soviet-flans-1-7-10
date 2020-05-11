package net.minecraft.tileentity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBeacon extends TileEntity implements IInventory {

   public static final Potion[][] effectsList = new Potion[][]{{Potion.moveSpeed, Potion.digSpeed}, {Potion.resistance, Potion.jump}, {Potion.damageBoost}, {Potion.regeneration}};
   private long field_146016_i;
   private float field_146014_j;
   private boolean field_146015_k;
   private int levels = -1;
   private int primaryEffect;
   private int secondaryEffect;
   private ItemStack payment;
   private String field_146008_p;


   public void updateEntity() {
      if(super.worldObj.getTotalWorldTime() % 80L == 0L) {
         this.func_146003_y();
         this.func_146000_x();
      }

   }

   private void func_146000_x() {
      if(this.field_146015_k && this.levels > 0 && !super.worldObj.isRemote && this.primaryEffect > 0) {
         double var1 = (double)(this.levels * 10 + 10);
         byte var3 = 0;
         if(this.levels >= 4 && this.primaryEffect == this.secondaryEffect) {
            var3 = 1;
         }

         AxisAlignedBB var4 = AxisAlignedBB.getBoundingBox((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, (double)(super.xCoord + 1), (double)(super.yCoord + 1), (double)(super.zCoord + 1)).expand(var1, var1, var1);
         var4.maxY = (double)super.worldObj.getHeight();
         List var5 = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, var4);
         Iterator var6 = var5.iterator();

         EntityPlayer var7;
         while(var6.hasNext()) {
            var7 = (EntityPlayer)var6.next();
            var7.addPotionEffect(new PotionEffect(this.primaryEffect, 180, var3, true));
         }

         if(this.levels >= 4 && this.primaryEffect != this.secondaryEffect && this.secondaryEffect > 0) {
            var6 = var5.iterator();

            while(var6.hasNext()) {
               var7 = (EntityPlayer)var6.next();
               var7.addPotionEffect(new PotionEffect(this.secondaryEffect, 180, 0, true));
            }
         }
      }

   }

   private void func_146003_y() {
      int var1 = this.levels;
      if(!super.worldObj.canBlockSeeTheSky(super.xCoord, super.yCoord + 1, super.zCoord)) {
         this.field_146015_k = false;
         this.levels = 0;
      } else {
         this.field_146015_k = true;
         this.levels = 0;

         for(int var2 = 1; var2 <= 4; this.levels = var2++) {
            int var3 = super.yCoord - var2;
            if(var3 < 0) {
               break;
            }

            boolean var4 = true;

            for(int var5 = super.xCoord - var2; var5 <= super.xCoord + var2 && var4; ++var5) {
               for(int var6 = super.zCoord - var2; var6 <= super.zCoord + var2; ++var6) {
                  Block var7 = super.worldObj.getBlock(var5, var3, var6);
                  if(var7 != Blocks.emerald_block && var7 != Blocks.gold_block && var7 != Blocks.diamond_block && var7 != Blocks.iron_block) {
                     var4 = false;
                     break;
                  }
               }
            }

            if(!var4) {
               break;
            }
         }

         if(this.levels == 0) {
            this.field_146015_k = false;
         }
      }

      if(!super.worldObj.isRemote && this.levels == 4 && var1 < this.levels) {
         Iterator var8 = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, (double)super.xCoord, (double)(super.yCoord - 4), (double)super.zCoord).expand(10.0D, 5.0D, 10.0D)).iterator();

         while(var8.hasNext()) {
            EntityPlayer var9 = (EntityPlayer)var8.next();
            var9.triggerAchievement(AchievementList.field_150965_K);
         }
      }

   }

   public float func_146002_i() {
      if(!this.field_146015_k) {
         return 0.0F;
      } else {
         int var1 = (int)(super.worldObj.getTotalWorldTime() - this.field_146016_i);
         this.field_146016_i = super.worldObj.getTotalWorldTime();
         if(var1 > 1) {
            this.field_146014_j -= (float)var1 / 40.0F;
            if(this.field_146014_j < 0.0F) {
               this.field_146014_j = 0.0F;
            }
         }

         this.field_146014_j += 0.025F;
         if(this.field_146014_j > 1.0F) {
            this.field_146014_j = 1.0F;
         }

         return this.field_146014_j;
      }
   }

   public int getPrimaryEffect() {
      return this.primaryEffect;
   }

   public int getSecondaryEffect() {
      return this.secondaryEffect;
   }

   public int getLevels() {
      return this.levels;
   }

   public void func_146005_c(int var1) {
      this.levels = var1;
   }

   public void setPrimaryEffect(int var1) {
      this.primaryEffect = 0;

      for(int var2 = 0; var2 < this.levels && var2 < 3; ++var2) {
         Potion[] var3 = effectsList[var2];
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Potion var6 = var3[var5];
            if(var6.id == var1) {
               this.primaryEffect = var1;
               return;
            }
         }
      }

   }

   public void setSecondaryEffect(int var1) {
      this.secondaryEffect = 0;
      if(this.levels >= 4) {
         for(int var2 = 0; var2 < 4; ++var2) {
            Potion[] var3 = effectsList[var2];
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               Potion var6 = var3[var5];
               if(var6.id == var1) {
                  this.secondaryEffect = var1;
                  return;
               }
            }
         }
      }

   }

   public Packet getDescriptionPacket() {
      NBTTagCompound var1 = new NBTTagCompound();
      this.writeToNBT(var1);
      return new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, 3, var1);
   }

   public double getMaxRenderDistanceSquared() {
      return 65536.0D;
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.primaryEffect = var1.getInteger("Primary");
      this.secondaryEffect = var1.getInteger("Secondary");
      this.levels = var1.getInteger("Levels");
   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setInteger("Primary", this.primaryEffect);
      var1.setInteger("Secondary", this.secondaryEffect);
      var1.setInteger("Levels", this.levels);
   }

   public int getSizeInventory() {
      return 1;
   }

   public ItemStack getStackInSlot(int var1) {
      return var1 == 0?this.payment:null;
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(var1 == 0 && this.payment != null) {
         if(var2 >= this.payment.stackSize) {
            ItemStack var3 = this.payment;
            this.payment = null;
            return var3;
         } else {
            this.payment.stackSize -= var2;
            return new ItemStack(this.payment.getItem(), var2, this.payment.getItemDamage());
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(var1 == 0 && this.payment != null) {
         ItemStack var2 = this.payment;
         this.payment = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      if(var1 == 0) {
         this.payment = var2;
      }

   }

   public String getInventoryName() {
      return this.hasCustomInventoryName()?this.field_146008_p:"container.beacon";
   }

   public boolean hasCustomInventoryName() {
      return this.field_146008_p != null && this.field_146008_p.length() > 0;
   }

   public void func_145999_a(String var1) {
      this.field_146008_p = var1;
   }

   public int getInventoryStackLimit() {
      return 1;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:var1.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return var2.getItem() == Items.emerald || var2.getItem() == Items.diamond || var2.getItem() == Items.gold_ingot || var2.getItem() == Items.iron_ingot;
   }

}
