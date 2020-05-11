package net.minecraft.tileentity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityChest extends TileEntity implements IInventory {

   private ItemStack[] chestContents = new ItemStack[36];
   public boolean adjacentChestChecked;
   public TileEntityChest adjacentChestZNeg;
   public TileEntityChest adjacentChestXPos;
   public TileEntityChest adjacentChestXNeg;
   public TileEntityChest adjacentChestZPos;
   public float lidAngle;
   public float prevLidAngle;
   public int numPlayersUsing;
   private int ticksSinceSync;
   private int cachedChestType;
   private String customName;


   public TileEntityChest() {
      this.cachedChestType = -1;
   }

   public TileEntityChest(int var1) {
      this.cachedChestType = var1;
   }

   public int getSizeInventory() {
      return 27;
   }

   public ItemStack getStackInSlot(int var1) {
      return this.chestContents[var1];
   }

   public ItemStack decrStackSize(int var1, int var2) {
      if(this.chestContents[var1] != null) {
         ItemStack var3;
         if(this.chestContents[var1].stackSize <= var2) {
            var3 = this.chestContents[var1];
            this.chestContents[var1] = null;
            this.markDirty();
            return var3;
         } else {
            var3 = this.chestContents[var1].splitStack(var2);
            if(this.chestContents[var1].stackSize == 0) {
               this.chestContents[var1] = null;
            }

            this.markDirty();
            return var3;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int var1) {
      if(this.chestContents[var1] != null) {
         ItemStack var2 = this.chestContents[var1];
         this.chestContents[var1] = null;
         return var2;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int var1, ItemStack var2) {
      this.chestContents[var1] = var2;
      if(var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
         var2.stackSize = this.getInventoryStackLimit();
      }

      this.markDirty();
   }

   public String getInventoryName() {
      return this.hasCustomInventoryName()?this.customName:"container.chest";
   }

   public boolean hasCustomInventoryName() {
      return this.customName != null && this.customName.length() > 0;
   }

   public void func_145976_a(String var1) {
      this.customName = var1;
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      NBTTagList var2 = var1.getTagList("Items", 10);
      this.chestContents = new ItemStack[this.getSizeInventory()];
      if(var1.hasKey("CustomName", 8)) {
         this.customName = var1.getString("CustomName");
      }

      for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
         int var5 = var4.getByte("Slot") & 255;
         if(var5 >= 0 && var5 < this.chestContents.length) {
            this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
         }
      }

   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for(int var3 = 0; var3 < this.chestContents.length; ++var3) {
         if(this.chestContents[var3] != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setByte("Slot", (byte)var3);
            this.chestContents[var3].writeToNBT(var4);
            var2.appendTag(var4);
         }
      }

      var1.setTag("Items", var2);
      if(this.hasCustomInventoryName()) {
         var1.setString("CustomName", this.customName);
      }

   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:var1.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void updateContainingBlockInfo() {
      super.updateContainingBlockInfo();
      this.adjacentChestChecked = false;
   }

   private void func_145978_a(TileEntityChest var1, int var2) {
      if(var1.isInvalid()) {
         this.adjacentChestChecked = false;
      } else if(this.adjacentChestChecked) {
         switch(var2) {
         case 0:
            if(this.adjacentChestZPos != var1) {
               this.adjacentChestChecked = false;
            }
            break;
         case 1:
            if(this.adjacentChestXNeg != var1) {
               this.adjacentChestChecked = false;
            }
            break;
         case 2:
            if(this.adjacentChestZNeg != var1) {
               this.adjacentChestChecked = false;
            }
            break;
         case 3:
            if(this.adjacentChestXPos != var1) {
               this.adjacentChestChecked = false;
            }
         }
      }

   }

   public void checkForAdjacentChests() {
      if(!this.adjacentChestChecked) {
         this.adjacentChestChecked = true;
         this.adjacentChestZNeg = null;
         this.adjacentChestXPos = null;
         this.adjacentChestXNeg = null;
         this.adjacentChestZPos = null;
         if(this.func_145977_a(super.xCoord - 1, super.yCoord, super.zCoord)) {
            this.adjacentChestXNeg = (TileEntityChest)super.worldObj.getTileEntity(super.xCoord - 1, super.yCoord, super.zCoord);
         }

         if(this.func_145977_a(super.xCoord + 1, super.yCoord, super.zCoord)) {
            this.adjacentChestXPos = (TileEntityChest)super.worldObj.getTileEntity(super.xCoord + 1, super.yCoord, super.zCoord);
         }

         if(this.func_145977_a(super.xCoord, super.yCoord, super.zCoord - 1)) {
            this.adjacentChestZNeg = (TileEntityChest)super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord - 1);
         }

         if(this.func_145977_a(super.xCoord, super.yCoord, super.zCoord + 1)) {
            this.adjacentChestZPos = (TileEntityChest)super.worldObj.getTileEntity(super.xCoord, super.yCoord, super.zCoord + 1);
         }

         if(this.adjacentChestZNeg != null) {
            this.adjacentChestZNeg.func_145978_a(this, 0);
         }

         if(this.adjacentChestZPos != null) {
            this.adjacentChestZPos.func_145978_a(this, 2);
         }

         if(this.adjacentChestXPos != null) {
            this.adjacentChestXPos.func_145978_a(this, 1);
         }

         if(this.adjacentChestXNeg != null) {
            this.adjacentChestXNeg.func_145978_a(this, 3);
         }

      }
   }

   private boolean func_145977_a(int var1, int var2, int var3) {
      if(super.worldObj == null) {
         return false;
      } else {
         Block var4 = super.worldObj.getBlock(var1, var2, var3);
         return var4 instanceof BlockChest && ((BlockChest)var4).field_149956_a == this.func_145980_j();
      }
   }

   public void updateEntity() {
      super.updateEntity();
      this.checkForAdjacentChests();
      ++this.ticksSinceSync;
      float var1;
      if(!super.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + super.xCoord + super.yCoord + super.zCoord) % 200 == 0) {
         this.numPlayersUsing = 0;
         var1 = 5.0F;
         List var2 = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)super.xCoord - var1), (double)((float)super.yCoord - var1), (double)((float)super.zCoord - var1), (double)((float)(super.xCoord + 1) + var1), (double)((float)(super.yCoord + 1) + var1), (double)((float)(super.zCoord + 1) + var1)));
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            EntityPlayer var4 = (EntityPlayer)var3.next();
            if(var4.openContainer instanceof ContainerChest) {
               IInventory var5 = ((ContainerChest)var4.openContainer).getLowerChestInventory();
               if(var5 == this || var5 instanceof InventoryLargeChest && ((InventoryLargeChest)var5).isPartOfLargeChest(this)) {
                  ++this.numPlayersUsing;
               }
            }
         }
      }

      this.prevLidAngle = this.lidAngle;
      var1 = 0.1F;
      double var11;
      if(this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
         double var8 = (double)super.xCoord + 0.5D;
         var11 = (double)super.zCoord + 0.5D;
         if(this.adjacentChestZPos != null) {
            var11 += 0.5D;
         }

         if(this.adjacentChestXPos != null) {
            var8 += 0.5D;
         }

         super.worldObj.playSoundEffect(var8, (double)super.yCoord + 0.5D, var11, "random.chestopen", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
      }

      if(this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
         float var9 = this.lidAngle;
         if(this.numPlayersUsing > 0) {
            this.lidAngle += var1;
         } else {
            this.lidAngle -= var1;
         }

         if(this.lidAngle > 1.0F) {
            this.lidAngle = 1.0F;
         }

         float var10 = 0.5F;
         if(this.lidAngle < var10 && var9 >= var10 && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
            var11 = (double)super.xCoord + 0.5D;
            double var6 = (double)super.zCoord + 0.5D;
            if(this.adjacentChestZPos != null) {
               var6 += 0.5D;
            }

            if(this.adjacentChestXPos != null) {
               var11 += 0.5D;
            }

            super.worldObj.playSoundEffect(var11, (double)super.yCoord + 0.5D, var6, "random.chestclosed", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
         }

         if(this.lidAngle < 0.0F) {
            this.lidAngle = 0.0F;
         }
      }

   }

   public boolean receiveClientEvent(int var1, int var2) {
      if(var1 == 1) {
         this.numPlayersUsing = var2;
         return true;
      } else {
         return super.receiveClientEvent(var1, var2);
      }
   }

   public void openInventory() {
      if(this.numPlayersUsing < 0) {
         this.numPlayersUsing = 0;
      }

      ++this.numPlayersUsing;
      super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
      super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.getBlockType());
      super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord - 1, super.zCoord, this.getBlockType());
   }

   public void closeInventory() {
      if(this.getBlockType() instanceof BlockChest) {
         --this.numPlayersUsing;
         super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
         super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.getBlockType());
         super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord - 1, super.zCoord, this.getBlockType());
      }

   }

   public boolean isItemValidForSlot(int var1, ItemStack var2) {
      return true;
   }

   public void invalidate() {
      super.invalidate();
      this.updateContainingBlockInfo();
      this.checkForAdjacentChests();
   }

   public int func_145980_j() {
      if(this.cachedChestType == -1) {
         if(super.worldObj == null || !(this.getBlockType() instanceof BlockChest)) {
            return 0;
         }

         this.cachedChestType = ((BlockChest)this.getBlockType()).field_149956_a;
      }

      return this.cachedChestType;
   }
}
