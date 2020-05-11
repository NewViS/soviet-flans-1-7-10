package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.api.recipes.OvenRecipes;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class OvenTileEntity extends TileEntity implements ISidedInventory {

   private static final int[] slotsTop = new int[]{0};
   private static final int[] slotsBottom = new int[]{2, 1};
   private static final int[] slotsSides = new int[]{1};
   private ItemStack[] ovenItemStacks = new ItemStack[3];
   public int ovenBurnTime;
   public int currentItemBurnTime;
   private String field_145958_o;
   private int furnaceCookTime;
   private static final String __OBFID = "CL_00000357";


   public int func_70302_i_() {
      return this.ovenItemStacks.length;
   }

   public ItemStack func_70301_a(int id) {
      return this.ovenItemStacks[id];
   }

   public ItemStack func_70298_a(int slotID, int Itemstack) {
      if(this.ovenItemStacks[slotID] != null) {
         ItemStack itemstack;
         if(this.ovenItemStacks[slotID].stackSize <= Itemstack) {
            itemstack = this.ovenItemStacks[slotID];
            this.ovenItemStacks[slotID] = null;
            return itemstack;
         } else {
            itemstack = this.ovenItemStacks[slotID].splitStack(Itemstack);
            if(this.ovenItemStacks[slotID].stackSize == 0) {
               this.ovenItemStacks[slotID] = null;
            }

            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int slotID) {
      if(this.ovenItemStacks[slotID] != null) {
         ItemStack itemstack = this.ovenItemStacks[slotID];
         this.ovenItemStacks[slotID] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void func_70299_a(int slotID, ItemStack StackID) {
      this.ovenItemStacks[slotID] = StackID;
      if(StackID != null && StackID.stackSize > this.func_70297_j_()) {
         StackID.stackSize = this.func_70297_j_();
      }

   }

   public String func_145825_b() {
      return null;
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.readFromNBT(p_145839_1_);
      NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
      this.ovenItemStacks = new ItemStack[this.func_70302_i_()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
         byte b0 = nbttagcompound1.getByte("Slot");
         if(b0 >= 0 && b0 < this.ovenItemStacks.length) {
            this.ovenItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
         }
      }

      this.ovenBurnTime = p_145839_1_.getShort("BurnTime");
      this.currentItemBurnTime = getItemBurnTime(this.ovenItemStacks[1]);
      if(p_145839_1_.hasKey("CustomName", 8)) {
         this.field_145958_o = p_145839_1_.getString("CustomName");
      }

   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.writeToNBT(p_145841_1_);
      p_145841_1_.setShort("BurnTime", (short)this.ovenBurnTime);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.ovenItemStacks.length; ++i) {
         if(this.ovenItemStacks[i] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.ovenItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      p_145841_1_.setTag("Items", nbttaglist);
      if(this.func_145818_k_()) {
         p_145841_1_.setString("CustomName", this.field_145958_o);
      }

   }

   public boolean func_145818_k_() {
      return false;
   }

   public int func_70297_j_() {
      return 0;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_145850_b.getTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this?false:p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) <= 64.0D;
   }

   public void func_70295_k_() {}

   public void func_70305_f() {}

   public boolean func_94041_b(int slotID, ItemStack itemStack) {
      return slotID == 2?false:(slotID == 1?isItemFuel(itemStack):true);
   }

   public int[] func_94128_d(int slotID) {
      return slotID == 0?slotsBottom:(slotID == 1?slotsTop:slotsSides);
   }

   public boolean func_102007_a(int slotID, ItemStack itemStack, int side) {
      return this.func_94041_b(slotID, itemStack);
   }

   public boolean func_102008_b(int slotID, ItemStack itemStack, int side) {
      return side != 0 || slotID != 1 || itemStack.getItem() == Items.bucket;
   }

   public static int getItemBurnTime(ItemStack itemStack) {
      return GameRegistry.getFuelValue(itemStack);
   }

   public static boolean isItemFuel(ItemStack itemStack) {
      return getItemBurnTime(itemStack) > 0;
   }

   @SideOnly(Side.CLIENT)
   public int getBurnTimeRemainingScaled(int p_145955_1_) {
      if(this.currentItemBurnTime == 0) {
         this.currentItemBurnTime = 200;
      }

      return this.ovenBurnTime * p_145955_1_ / this.currentItemBurnTime;
   }

   public boolean isBurning() {
      return this.ovenBurnTime > 0;
   }

   public void func_145845_h() {
      boolean flag = this.ovenBurnTime > 0;
      boolean flag1 = false;
      if(this.ovenBurnTime > 0) {
         --this.ovenBurnTime;
      }

      if(!this.field_145850_b.isRemote) {
         if(this.ovenBurnTime != 0 || this.ovenItemStacks[1] != null && this.ovenItemStacks[0] != null) {
            if(this.ovenBurnTime == 0 && this.canSmelt()) {
               this.currentItemBurnTime = this.ovenBurnTime = getItemBurnTime(this.ovenItemStacks[1]);
               if(this.ovenBurnTime > 0) {
                  flag1 = true;
                  if(this.ovenItemStacks[1] != null) {
                     --this.ovenItemStacks[1].stackSize;
                     if(this.ovenItemStacks[1].stackSize == 0) {
                        this.ovenItemStacks[1] = this.ovenItemStacks[1].getItem().getContainerItem(this.ovenItemStacks[1]);
                     }
                  }
               }
            }

            if(this.isBurning() && this.canSmelt()) {
               ++this.furnaceCookTime;
               if(this.furnaceCookTime == 200) {
                  this.furnaceCookTime = 0;
                  this.smeltItem();
                  flag1 = true;
               }
            } else {
               this.furnaceCookTime = 0;
            }
         }

         if(flag != this.ovenBurnTime > 0) {
            flag1 = true;
            BlockFurnace.updateFurnaceBlockState(this.ovenBurnTime > 0, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }
      }

      if(flag1) {
         this.func_70296_d();
      }

   }

   private boolean canSmelt() {
      if(this.ovenItemStacks[0] == null) {
         return false;
      } else {
         ItemStack itemstack = OvenRecipes.smelting().getSmeltingResult(this.ovenItemStacks[0]);
         if(itemstack == null) {
            return false;
         } else if(this.ovenItemStacks[2] == null) {
            return true;
         } else if(!this.ovenItemStacks[2].isItemEqual(itemstack)) {
            return false;
         } else {
            int result = this.ovenItemStacks[2].stackSize + itemstack.stackSize;
            return result <= this.func_70297_j_() && result <= this.ovenItemStacks[2].getMaxStackSize();
         }
      }
   }

   public void smeltItem() {
      if(this.canSmelt()) {
         ItemStack itemstack = OvenRecipes.smelting().getSmeltingResult(this.ovenItemStacks[0]);
         if(this.ovenItemStacks[2] == null) {
            this.ovenItemStacks[2] = itemstack.copy();
         } else if(this.ovenItemStacks[2].getItem() == itemstack.getItem()) {
            this.ovenItemStacks[2].stackSize += itemstack.stackSize;
         }

         --this.ovenItemStacks[0].stackSize;
         if(this.ovenItemStacks[0].stackSize <= 0) {
            this.ovenItemStacks[0] = null;
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public int getCookProgressScaled(int scale) {
      return this.furnaceCookTime * scale / 200;
   }

}
