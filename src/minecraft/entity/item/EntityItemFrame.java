package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class EntityItemFrame extends EntityHanging {

   private float itemDropChance = 1.0F;


   public EntityItemFrame(World var1) {
      super(var1);
   }

   public EntityItemFrame(World var1, int var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5);
      this.setDirection(var5);
   }

   protected void entityInit() {
      this.getDataWatcher().addObjectByDataType(2, 5);
      this.getDataWatcher().addObject(3, Byte.valueOf((byte)0));
   }

   public boolean attackEntityFrom(DamageSource var1, float var2) {
      if(this.isEntityInvulnerable()) {
         return false;
      } else if(this.getDisplayedItem() != null) {
         if(!super.worldObj.isRemote) {
            this.func_146065_b(var1.getEntity(), false);
            this.setDisplayedItem((ItemStack)null);
         }

         return true;
      } else {
         return super.attackEntityFrom(var1, var2);
      }
   }

   public int getWidthPixels() {
      return 9;
   }

   public int getHeightPixels() {
      return 9;
   }

   public boolean isInRangeToRenderDist(double var1) {
      double var3 = 16.0D;
      var3 *= 64.0D * super.renderDistanceWeight;
      return var1 < var3 * var3;
   }

   public void onBroken(Entity var1) {
      this.func_146065_b(var1, true);
   }

   public void func_146065_b(Entity var1, boolean var2) {
      ItemStack var3 = this.getDisplayedItem();
      if(var1 instanceof EntityPlayer) {
         EntityPlayer var4 = (EntityPlayer)var1;
         if(var4.capabilities.isCreativeMode) {
            this.removeFrameFromMap(var3);
            return;
         }
      }

      if(var2) {
         this.entityDropItem(new ItemStack(Items.item_frame), 0.0F);
      }

      if(var3 != null && super.rand.nextFloat() < this.itemDropChance) {
         var3 = var3.copy();
         this.removeFrameFromMap(var3);
         this.entityDropItem(var3, 0.0F);
      }

   }

   private void removeFrameFromMap(ItemStack var1) {
      if(var1 != null) {
         if(var1.getItem() == Items.filled_map) {
            MapData var2 = ((ItemMap)var1.getItem()).getMapData(var1, super.worldObj);
            var2.playersVisibleOnMap.remove("frame-" + this.getEntityId());
         }

         var1.setItemFrame((EntityItemFrame)null);
      }
   }

   public ItemStack getDisplayedItem() {
      return this.getDataWatcher().getWatchableObjectItemStack(2);
   }

   public void setDisplayedItem(ItemStack var1) {
      if(var1 != null) {
         var1 = var1.copy();
         var1.stackSize = 1;
         var1.setItemFrame(this);
      }

      this.getDataWatcher().updateObject(2, var1);
      this.getDataWatcher().setObjectWatched(2);
   }

   public int getRotation() {
      return this.getDataWatcher().getWatchableObjectByte(3);
   }

   public void setItemRotation(int var1) {
      this.getDataWatcher().updateObject(3, Byte.valueOf((byte)(var1 % 4)));
   }

   public void writeEntityToNBT(NBTTagCompound var1) {
      if(this.getDisplayedItem() != null) {
         var1.setTag("Item", this.getDisplayedItem().writeToNBT(new NBTTagCompound()));
         var1.setByte("ItemRotation", (byte)this.getRotation());
         var1.setFloat("ItemDropChance", this.itemDropChance);
      }

      super.writeEntityToNBT(var1);
   }

   public void readEntityFromNBT(NBTTagCompound var1) {
      NBTTagCompound var2 = var1.getCompoundTag("Item");
      if(var2 != null && !var2.hasNoTags()) {
         this.setDisplayedItem(ItemStack.loadItemStackFromNBT(var2));
         this.setItemRotation(var1.getByte("ItemRotation"));
         if(var1.hasKey("ItemDropChance", 99)) {
            this.itemDropChance = var1.getFloat("ItemDropChance");
         }
      }

      super.readEntityFromNBT(var1);
   }

   public boolean interactFirst(EntityPlayer var1) {
      if(this.getDisplayedItem() == null) {
         ItemStack var2 = var1.getHeldItem();
         if(var2 != null && !super.worldObj.isRemote) {
            this.setDisplayedItem(var2);
            if(!var1.capabilities.isCreativeMode && --var2.stackSize <= 0) {
               var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
            }
         }
      } else if(!super.worldObj.isRemote) {
         this.setItemRotation(this.getRotation() + 1);
      }

      return true;
   }
}
