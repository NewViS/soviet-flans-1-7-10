package net.minecraft.tileentity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHopper extends TileEntity implements IHopper {

   private ItemStack[] field_145900_a = new ItemStack[5];
   private String field_145902_i;
   private int field_145901_j = -1;


   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.field_145900_a = new ItemStack[this.getSizeInventory()];
      if(var1.hasKey("CustomName", 8)) {
         this.field_145902_i = var1.getString("CustomName");
      }

      this.field_145901_j = var1.getInteger("TransferCooldown");

      for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         byte var5 = var4.getByte("Slot");
         if(var5 >= 0 && var5 < this.field_145900_a.length) {
            this.field_145900_a[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for(int var3 = 0; var3 < this.field_145900_a.length; ++var3) {
         if(this.field_145900_a[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.field_145900_a[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      var1.setInteger("TransferCooldown", this.field_145901_j);
      if(this.hasCustomInventoryName()) {
         var1.setString("CustomName", this.field_145902_i);
      }

   }

   public void markDirty() {
      super.markDirty();
   }

   public int getSizeInventory() {
      return this.field_145900_a.length;
   }

   public ItemStack getStackInSlot(int var1) {
      return this.field_145900_a[var1];
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(this.field_145900_a[var1] != null) {
         ItemStack var3;
         if(this.field_145900_a[var1].stackSize <= var2) {
            var3 = this.field_145900_a[var1];
            this.field_145900_a[var1] = null;
            return var3;
         } else {
            var3 = this.field_145900_a[var1].splitStack(var2);
            if(this.field_145900_a[var1].stackSize == 0) {
               this.field_145900_a[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(this.field_145900_a[var1] != null) {
         ItemStack var2 = this.field_145900_a[var1];
         this.field_145900_a[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.field_145900_a[var1] = var2;
      if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

   }

   public String getInventoryName() {
      return this.hasCustomInventoryName()?this.field_145902_i:"container.hopper";
   }

   public boolean hasCustomInventoryName() {
      return this.field_145902_i != null && this.field_145902_i.length() > 0;
   }

   public void func_145886_a(String var1) {
      this.field_145902_i = var1;
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
      return true;
   }

   public void updateEntity() {
      if(super.worldObj != null && !super.worldObj.isRemote) {
         --this.field_145901_j;
         if(!this.func_145888_j()) {
            this.func_145896_c(0);
            this.func_145887_i();
         }

      }
   }

   public boolean func_145887_i() {
      if(super.worldObj != null && !super.worldObj.isRemote) {
         if(!this.func_145888_j() && BlockHopper.func_149917_c(this.getBlockMetadata())) {
            boolean var1 = false;
            if(!this.func_152104_k()) {
               var1 = this.func_145883_k();
            }

            if(!this.func_152105_l()) {
               var1 = func_145891_a(this) || var1;
            }

            if(var1) {
               this.func_145896_c(8);
               this.markDirty();
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean func_152104_k() {
      ItemStack[] var1 = this.field_145900_a;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ItemStack var4 = var1[var3];
         if(var4 != null) {
            return false;
         }
      }

      return true;
   }

   private boolean func_152105_l() {
      ItemStack[] var1 = this.field_145900_a;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ItemStack var4 = var1[var3];
         if(var4 == null || var4.stackSize != var4.getMaxStackSize()) {
            return false;
         }
      }

      return true;
   }

   private boolean func_145883_k() {
      IInventory var1 = this.func_145895_l();
      if(var1 == null) {
         return false;
      } else {
         int var2 = Facing.oppositeSide[BlockHopper.getDirectionFromMetadata(this.getBlockMetadata())];
         if(this.func_152102_a(var1, var2)) {
            return false;
         } else {
            for(int var3 = 0; var3 < this.getSizeInventory(); ++var3) {
               if(this.getStackInSlot(var3) != null) {
                  ItemStack var4 = this.getStackInSlot(var3).copy();
                  ItemStack var5 = func_145889_a(var1, this.decrStackSize(var3, 1), var2);
                  if(var5 == null || var5.stackSize == 0) {
                     var1.markDirty();
                     return true;
                  }

                  this.setInventorySlotContents(var3, var4);
               }
            }

            return false;
         }
      }
   }

   private boolean func_152102_a(IInventory var1, int var2) {
      if(var1 instanceof ISidedInventory && var2 > -1) {
         ISidedInventory var7 = (ISidedInventory)var1;
         int[] var8 = var7.getAccessibleSlotsFromSide(var2);

         for(int var9 = 0; var9 < var8.length; ++var9) {
            ItemStack var6 = var7.getStackInSlot(var8[var9]);
            if(var6 == null || var6.stackSize != var6.getMaxStackSize()) {
               return false;
            }
         }
      } else {
         int var3 = var1.getSizeInventory();

         for(int var4 = 0; var4 < var3; ++var4) {
            ItemStack var5 = var1.getStackInSlot(var4);
            if(var5 == null || var5.stackSize != var5.getMaxStackSize()) {
               return false;
            }
         }
      }

      return true;
   }

   private static boolean func_152103_b(IInventory var0, int var1) {
      if(var0 instanceof ISidedInventory && var1 > -1) {
         ISidedInventory var5 = (ISidedInventory)var0;
         int[] var6 = var5.getAccessibleSlotsFromSide(var1);

         for(int var4 = 0; var4 < var6.length; ++var4) {
            if(var5.getStackInSlot(var6[var4]) != null) {
               return false;
            }
         }
      } else {
         int var2 = var0.getSizeInventory();

         for(int var3 = 0; var3 < var2; ++var3) {
            if(var0.getStackInSlot(var3) != null) {
               return false;
            }
         }
      }

      return true;
   }

   public static boolean func_145891_a(IHopper var0) {
      IInventory var1 = func_145884_b(var0);
      if(var1 != null) {
         byte var2 = 0;
         if(func_152103_b(var1, var2)) {
            return false;
         }

         if(var1 instanceof ISidedInventory && var2 > -1) {
            ISidedInventory var7 = (ISidedInventory)var1;
            int[] var8 = var7.getAccessibleSlotsFromSide(var2);

            for(int var5 = 0; var5 < var8.length; ++var5) {
               if(func_145892_a(var0, var1, var8[var5], var2)) {
                  return true;
               }
            }
         } else {
            int var3 = var1.getSizeInventory();

            for(int var4 = 0; var4 < var3; ++var4) {
               if(func_145892_a(var0, var1, var4, var2)) {
                  return true;
               }
            }
         }
      } else {
         EntityItem var6 = func_145897_a(var0.getWorldObj(), var0.getXPos(), var0.getYPos() + 1.0D, var0.getZPos());
         if(var6 != null) {
            return func_145898_a(var0, var6);
         }
      }

      return false;
   }

   private static boolean func_145892_a(IHopper var0, IInventory var1, int var2, int var3) {
      ItemStack var4 = var1.getStackInSlot(var2);
      if(var4 != null && func_145890_b(var1, var4, var2, var3)) {
         ItemStack var5 = var4.copy();
         ItemStack var6 = func_145889_a(var0, var1.decrStackSize(var2, 1), -1);
         if(var6 == null || var6.stackSize == 0) {
            var1.markDirty();
            return true;
         }

         var1.setInventorySlotContents(var2, var5);
      }

      return false;
   }

   public static boolean func_145898_a(IInventory var0, EntityItem var1) {
      boolean var2 = false;
      if(var1 == null) {
         return false;
      } else {
         ItemStack var3 = var1.getEntityItem().copy();
         ItemStack var4 = func_145889_a(var0, var3, -1);
         if(var4 != null && var4.stackSize != 0) {
            var1.setEntityItemStack(var4);
         } else {
            var2 = true;
            var1.setDead();
         }

         return var2;
      }
   }

   public static ItemStack func_145889_a(IInventory var0, ItemStack var1, int var2) {
      if(var0 instanceof ISidedInventory && var2 > -1) {
         ISidedInventory var6 = (ISidedInventory)var0;
         int[] var7 = var6.getAccessibleSlotsFromSide(var2);

         for(int var5 = 0; var5 < var7.length && var1 != null && var1.stackSize > 0; ++var5) {
            var1 = func_145899_c(var0, var1, var7[var5], var2);
         }
      } else {
         int var3 = var0.getSizeInventory();

         for(int var4 = 0; var4 < var3 && var1 != null && var1.stackSize > 0; ++var4) {
            var1 = func_145899_c(var0, var1, var4, var2);
         }
      }

      if(var1 != null && var1.stackSize == 0) {
         var1 = null;
      }

      return var1;
   }

   private static boolean func_145885_a(IInventory var0, ItemStack var1, int var2, int var3) {
      return !var0.isItemValidForSlot(var2, var1)?false:!(var0 instanceof ISidedInventory) || ((ISidedInventory)var0).canInsertItem(var2, var1, var3);
   }

   private static boolean func_145890_b(IInventory var0, ItemStack var1, int var2, int var3) {
      return !(var0 instanceof ISidedInventory) || ((ISidedInventory)var0).canExtractItem(var2, var1, var3);
   }

   private static ItemStack func_145899_c(IInventory var0, ItemStack var1, int var2, int var3) {
      ItemStack var4 = var0.getStackInSlot(var2);
      if(func_145885_a(var0, var1, var2, var3)) {
         boolean var5 = false;
         if(var4 == null) {
            var0.setInventorySlotContents(var2, var1);
            var1 = null;
            var5 = true;
         } else if(func_145894_a(var4, var1)) {
            int var6 = var1.getMaxStackSize() - var4.stackSize;
            int var7 = Math.min(var1.stackSize, var6);
            var1.stackSize -= var7;
            var4.stackSize += var7;
            var5 = var7 > 0;
         }

         if(var5) {
            if(var0 instanceof TileEntityHopper) {
               ((TileEntityHopper)var0).func_145896_c(8);
               var0.markDirty();
            }

            var0.markDirty();
         }
      }

      return var1;
   }

   private IInventory func_145895_l() {
      int var1 = BlockHopper.getDirectionFromMetadata(this.getBlockMetadata());
      return func_145893_b(this.getWorldObj(), (double)(super.xCoord + Facing.offsetsXForSide[var1]), (double)(super.yCoord + Facing.offsetsYForSide[var1]), (double)(super.zCoord + Facing.offsetsZForSide[var1]));
   }

   public static IInventory func_145884_b(IHopper var0) {
      return func_145893_b(var0.getWorldObj(), var0.getXPos(), var0.getYPos() + 1.0D, var0.getZPos());
   }

   public static EntityItem func_145897_a(World var0, double var1, double var3, double var5) {
      List var7 = var0.selectEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(var1, var3, var5, var1 + 1.0D, var3 + 1.0D, var5 + 1.0D), IEntitySelector.selectAnything);
      return var7.size() > 0?(EntityItem)var7.get(0):null;
   }

   public static IInventory func_145893_b(World var0, double var1, double var3, double var5) {
      IInventory var7 = null;
      int var8 = MathHelper.floor_double(var1);
      int var9 = MathHelper.floor_double(var3);
      int var10 = MathHelper.floor_double(var5);
      TileEntity var11 = var0.getTileEntity(var8, var9, var10);
      if(var11 != null && var11 instanceof IInventory) {
         var7 = (IInventory)var11;
         if(var7 instanceof TileEntityChest) {
            Block var12 = var0.getBlock(var8, var9, var10);
            if(var12 instanceof BlockChest) {
               var7 = ((BlockChest)var12).func_149951_m(var0, var8, var9, var10);
            }
         }
      }

      if(var7 == null) {
         List var13 = var0.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getBoundingBox(var1, var3, var5, var1 + 1.0D, var3 + 1.0D, var5 + 1.0D), IEntitySelector.selectInventories);
         if(var13 != null && var13.size() > 0) {
            var7 = (IInventory)var13.get(var0.rand.nextInt(var13.size()));
         }
      }

      return var7;
   }

   private static boolean func_145894_a(ItemStack var0, ItemStack var1) {
      return var0.getItem() != var1.getItem()?false:(var0.getItemDamage() != var1.getItemDamage()?false:(var0.stackSize > var0.getMaxStackSize()?false:ItemStack.areItemStackTagsEqual(var0, var1)));
   }

   public double getXPos() {
      return (double)super.xCoord;
   }

   public double getYPos() {
      return (double)super.yCoord;
   }

   public double getZPos() {
      return (double)super.zCoord;
   }

   public void func_145896_c(int var1) {
      this.field_145901_j = var1;
   }

   public boolean func_145888_j() {
      return this.field_145901_j > 0;
   }
}
