package net.minecraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFurnace extends TileEntity implements ISidedInventory {

   private static final int[] slotsTop = new int[]{0};
   private static final int[] slotsBottom = new int[]{2, 1};
   private static final int[] slotsSides = new int[]{1};
   private ItemStack[] furnaceItemStacks = new ItemStack[3];
   public int furnaceBurnTime;
   public int currentItemBurnTime;
   public int furnaceCookTime;
   private String field_145958_o;


   public int getSizeInventory() {
      return this.furnaceItemStacks.length;
   }

   public ItemStack getStackInSlot(int var1) {
      return this.furnaceItemStacks[var1];
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(this.furnaceItemStacks[var1] != null) {
         ItemStack var3;
         if(this.furnaceItemStacks[var1].stackSize <= var2) {
            var3 = this.furnaceItemStacks[var1];
            this.furnaceItemStacks[var1] = null;
            return var3;
         } else {
            var3 = this.furnaceItemStacks[var1].splitStack(var2);
            if(this.furnaceItemStacks[var1].stackSize == 0) {
               this.furnaceItemStacks[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(this.furnaceItemStacks[var1] != null) {
         ItemStack var2 = this.furnaceItemStacks[var1];
         this.furnaceItemStacks[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.furnaceItemStacks[var1] = var2;
      if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

   }

   public String getInventoryName() {
      return this.hasCustomInventoryName()?this.field_145958_o:"container.furnace";
   }

   public boolean hasCustomInventoryName() {
      return this.field_145958_o != null && this.field_145958_o.length() > 0;
   }

   public void func_145951_a(String var1) {
      this.field_145958_o = var1;
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

      for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         byte var5 = var4.getByte("Slot");
         if(var5 >= 0 && var5 < this.furnaceItemStacks.length) {
            this.furnaceItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

      this.furnaceBurnTime = var1.getShort("BurnTime");
      this.furnaceCookTime = var1.getShort("CookTime");
      this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
      if(var1.hasKey("CustomName", 8)) {
         this.field_145958_o = var1.getString("CustomName");
      }

   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setShort("BurnTime", (short)this.furnaceBurnTime);
      var1.setShort("CookTime", (short)this.furnaceCookTime);
      NBTTagList var2 = new NBTTagList();

      for(int var3 = 0; var3 < this.furnaceItemStacks.length; ++var3) {
         if(this.furnaceItemStacks[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.furnaceItemStacks[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      if(this.hasCustomInventoryName()) {
         var1.setString("CustomName", this.field_145958_o);
      }

   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public int getCookProgressScaled(int var1) {
      return this.furnaceCookTime * var1 / 200;
   }

   public int getBurnTimeRemainingScaled(int var1) {
      if(this.currentItemBurnTime == 0) {
         this.currentItemBurnTime = 200;
      }

      return this.furnaceBurnTime * var1 / this.currentItemBurnTime;
   }

   public boolean isBurning() {
      return this.furnaceBurnTime > 0;
   }

   public void updateEntity() {
      boolean var1 = this.furnaceBurnTime > 0;
      boolean var2 = false;
      if(this.furnaceBurnTime > 0) {
         --this.furnaceBurnTime;
      }

      if(!super.worldObj.isRemote) {
         if(this.furnaceBurnTime != 0 || this.furnaceItemStacks[1] != null && this.furnaceItemStacks[0] != null) {
            if(this.furnaceBurnTime == 0 && this.canSmelt()) {
               this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
               if(this.furnaceBurnTime > 0) {
                  var2 = true;
                  if(this.furnaceItemStacks[1] != null) {
                     --this.furnaceItemStacks[1].stackSize;
                     if(this.furnaceItemStacks[1].stackSize == 0) {
                        Item var3 = this.furnaceItemStacks[1].getItem().getContainerItem();
                        this.furnaceItemStacks[1] = var3 != null?new ItemStack(var3):null;
                     }
                  }
               }
            }

            if(this.isBurning() && this.canSmelt()) {
               ++this.furnaceCookTime;
               if(this.furnaceCookTime == 200) {
                  this.furnaceCookTime = 0;
                  this.smeltItem();
                  var2 = true;
               }
            } else {
               this.furnaceCookTime = 0;
            }
         }

         if(var1 != this.furnaceBurnTime > 0) {
            var2 = true;
            BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, super.worldObj, super.xCoord, super.yCoord, super.zCoord);
         }
      }

      if(var2) {
         this.markDirty();
      }

   }

   private boolean canSmelt() {
      if(this.furnaceItemStacks[0] == null) {
         return false;
      } else {
         ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
         return var1 == null?false:(this.furnaceItemStacks[2] == null?true:(!this.furnaceItemStacks[2].isItemEqual(var1)?false:(this.furnaceItemStacks[2].stackSize < this.getInventoryStackLimit() && this.furnaceItemStacks[2].stackSize < this.furnaceItemStacks[2].getMaxStackSize()?true:this.furnaceItemStacks[2].stackSize < var1.getMaxStackSize())));
      }
   }

   public void smeltItem() {
      if(this.canSmelt()) {
         ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
         if(this.furnaceItemStacks[2] == null) {
            this.furnaceItemStacks[2] = var1.copy();
         } else if(this.furnaceItemStacks[2].getItem() == var1.getItem()) {
            ++this.furnaceItemStacks[2].stackSize;
         }

         --this.furnaceItemStacks[0].stackSize;
         if(this.furnaceItemStacks[0].stackSize <= 0) {
            this.furnaceItemStacks[0] = null;
         }

      }
   }

   public static int getItemBurnTime(ItemStack var0) {
      if(var0 == null) {
         return 0;
      } else {
         Item var1 = var0.getItem();
         if(var1 instanceof ItemBlock && Block.getBlockFromItem(var1) != Blocks.air) {
            Block var2 = Block.getBlockFromItem(var1);
            if(var2 == Blocks.wooden_slab) {
               return 150;
            }

            if(var2.getMaterial() == Material.wood) {
               return 300;
            }

            if(var2 == Blocks.coal_block) {
               return 16000;
            }
         }

         return var1 instanceof ItemTool && ((ItemTool)var1).getToolMaterialName().equals("WOOD")?200:(var1 instanceof ItemSword && ((ItemSword)var1).getToolMaterialName().equals("WOOD")?200:(var1 instanceof ItemHoe && ((ItemHoe)var1).getToolMaterialName().equals("WOOD")?200:(var1 == Items.stick?100:(var1 == Items.coal?1600:(var1 == Items.lava_bucket?20000:(var1 == Item.getItemFromBlock(Blocks.sapling)?100:(var1 == Items.blaze_rod?2400:0)))))));
      }
   }

   public static boolean isItemFuel(ItemStack var0) {
      return getItemBurnTime(var0) > 0;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:var1.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return var1 == 2?false:(var1 == 1?isItemFuel(var2):true);
   }

   public int[] getAccessibleSlotsFromSide(int var1) {
      return var1 == 0?slotsBottom:(var1 == 1?slotsTop:slotsSides);
   }

   public boolean canInsertItem(int var1, ItemStack var2, int var3) {
      return this.isItemValidForSlot(var1, var2);
   }

   public boolean canExtractItem(int var1, ItemStack var2, int var3) {
      return var3 != 0 || var1 != 1 || var2.getItem() == Items.bucket;
   }

}
