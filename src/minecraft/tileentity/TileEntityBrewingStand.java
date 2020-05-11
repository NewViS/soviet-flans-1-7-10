package net.minecraft.tileentity;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionHelper;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBrewingStand extends TileEntity implements ISidedInventory {

   private static final int[] field_145941_a = new int[]{3};
   private static final int[] field_145947_i = new int[]{0, 1, 2};
   private ItemStack[] brewingItemStacks = new ItemStack[4];
   private int brewTime;
   private int filledSlots;
   private Item ingredientID;
   private String field_145942_n;


   public String getInventoryName() {
      return this.hasCustomInventoryName()?this.field_145942_n:"container.brewing";
   }

   public boolean hasCustomInventoryName() {
      return this.field_145942_n != null && this.field_145942_n.length() > 0;
   }

   public void func_145937_a(String var1) {
      this.field_145942_n = var1;
   }

   public int getSizeInventory() {
      return this.brewingItemStacks.length;
   }

   public void updateEntity() {
      if(this.brewTime > 0) {
         --this.brewTime;
         if(this.brewTime == 0) {
            this.brewPotions();
            this.markDirty();
         } else if(!this.canBrew()) {
            this.brewTime = 0;
            this.markDirty();
         } else if(this.ingredientID != this.brewingItemStacks[3].getItem()) {
            this.brewTime = 0;
            this.markDirty();
         }
      } else if(this.canBrew()) {
         this.brewTime = 400;
         this.ingredientID = this.brewingItemStacks[3].getItem();
      }

      int var1 = this.getFilledSlots();
      if(var1 != this.filledSlots) {
         this.filledSlots = var1;
         super.worldObj.setBlockMetadataWithNotify(super.xCoord, super.yCoord, super.zCoord, var1, 2);
      }

      super.updateEntity();
   }

   public int getBrewTime() {
      return this.brewTime;
   }

   private boolean canBrew() {
      if(this.brewingItemStacks[3] != null && this.brewingItemStacks[3].stackSize > 0) {
         ItemStack var1 = this.brewingItemStacks[3];
         if(!var1.getItem().isPotionIngredient(var1)) {
            return false;
         } else {
            boolean var2 = false;

            for(int var3 = 0; var3 < 3; ++var3) {
               if(this.brewingItemStacks[var3] != null && this.brewingItemStacks[var3].getItem() == Items.potionitem) {
                  int var4 = this.brewingItemStacks[var3].getItemDamage();
                  int var5 = this.func_145936_c(var4, var1);
                  if(!ItemPotion.isSplash(var4) && ItemPotion.isSplash(var5)) {
                     var2 = true;
                     break;
                  }

                  List var6 = Items.potionitem.getEffects(var4);
                  List var7 = Items.potionitem.getEffects(var5);
                  if((var4 <= 0 || var6 != var7) && (var6 == null || !var6.equals(var7) && var7 != null) && var4 != var5) {
                     var2 = true;
                     break;
                  }
               }
            }

            return var2;
         }
      } else {
         return false;
      }
   }

   private void brewPotions() {
      if(this.canBrew()) {
         ItemStack var1 = this.brewingItemStacks[3];

         for(int var2 = 0; var2 < 3; ++var2) {
            if(this.brewingItemStacks[var2] != null && this.brewingItemStacks[var2].getItem() == Items.potionitem) {
               int var3 = this.brewingItemStacks[var2].getItemDamage();
               int var4 = this.func_145936_c(var3, var1);
               List var5 = Items.potionitem.getEffects(var3);
               List var6 = Items.potionitem.getEffects(var4);
               if((var3 <= 0 || var5 != var6) && (var5 == null || !var5.equals(var6) && var6 != null)) {
                  if(var3 != var4) {
                     this.brewingItemStacks[var2].setItemDamage(var4);
                  }
               } else if(!ItemPotion.isSplash(var3) && ItemPotion.isSplash(var4)) {
                  this.brewingItemStacks[var2].setItemDamage(var4);
               }
            }
         }

         if(var1.getItem().hasContainerItem()) {
            this.brewingItemStacks[3] = new ItemStack(var1.getItem().getContainerItem());
         } else {
            --this.brewingItemStacks[3].stackSize;
            if(this.brewingItemStacks[3].stackSize <= 0) {
               this.brewingItemStacks[3] = null;
            }
         }

      }
   }

   private int func_145936_c(int var1, ItemStack var2) {
      return var2 == null?var1:(var2.getItem().isPotionIngredient(var2)?PotionHelper.applyIngredient(var1, var2.getItem().getPotionEffect(var2)):var1);
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.brewingItemStacks = new ItemStack[this.getSizeInventory()];

      for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         byte var5 = var4.getByte("Slot");
         if(var5 >= 0 && var5 < this.brewingItemStacks.length) {
            this.brewingItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

      this.brewTime = var1.getShort("BrewTime");
      if(var1.hasKey("CustomName", 8)) {
         this.field_145942_n = var1.getString("CustomName");
      }

   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setShort("BrewTime", (short)this.brewTime);
      NBTTagList var2 = new NBTTagList();

      for(int var3 = 0; var3 < this.brewingItemStacks.length; ++var3) {
         if(this.brewingItemStacks[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.brewingItemStacks[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      if(this.hasCustomInventoryName()) {
         var1.setString("CustomName", this.field_145942_n);
      }

   }

   public ItemStack getStackInSlot(int var1) {
      return var1 >= 0 && var1 < this.brewingItemStacks.length?this.brewingItemStacks[var1]:null;
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(var1 >= 0 && var1 < this.brewingItemStacks.length) {
         ItemStack var3 = this.brewingItemStacks[var1];
         this.brewingItemStacks[var1] = null;
         return var3;
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(var1 >= 0 && var1 < this.brewingItemStacks.length) {
         ItemStack var2 = this.brewingItemStacks[var1];
         this.brewingItemStacks[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      if(var1 >= 0 && var1 < this.brewingItemStacks.length) {
         this.brewingItemStacks[var1] = var2;
      }

   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:var1.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return var1 == 3?var2.getItem().isPotionIngredient(var2):var2.getItem() == Items.potionitem || var2.getItem() == Items.glass_bottle;
   }

   public void func_145938_d(int var1) {
      this.brewTime = var1;
   }

   public int getFilledSlots() {
      int var1 = 0;

      for(int var2 = 0; var2 < 3; ++var2) {
         if(this.brewingItemStacks[var2] != null) {
            var1 |= 1 << var2;
         }
      }

      return var1;
   }

   public int[] getAccessibleSlotsFromSide(int var1) {
      return var1 == 1?field_145941_a:field_145947_i;
   }

   public boolean canInsertItem(int var1, ItemStack var2, int var3) {
      return this.isItemValidForSlot(var1, var2);
   }

   public boolean canExtractItem(int var1, ItemStack var2, int var3) {
      return true;
   }

}
