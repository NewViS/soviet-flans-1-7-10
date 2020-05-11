package net.minecraft.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;

public class TileEntityPiston extends TileEntity {

   private Block storedBlock;
   private int storedMetadata;
   private int storedOrientation;
   private boolean extending;
   private boolean shouldHeadBeRendered;
   private float progress;
   private float lastProgress;
   private List pushedObjects = new ArrayList();


   public TileEntityPiston() {}

   public TileEntityPiston(Block var1, int var2, int var3, boolean var4, boolean var5) {
      this.storedBlock = var1;
      this.storedMetadata = var2;
      this.storedOrientation = var3;
      this.extending = var4;
      this.shouldHeadBeRendered = var5;
   }

   public Block getStoredBlockID() {
      return this.storedBlock;
   }

   public int getBlockMetadata() {
      return this.storedMetadata;
   }

   public boolean isExtending() {
      return this.extending;
   }

   public int getPistonOrientation() {
      return this.storedOrientation;
   }

   public boolean func_145867_d() {
      return this.shouldHeadBeRendered;
   }

   public float func_145860_a(float var1) {
      if(var1 > 1.0F) {
         var1 = 1.0F;
      }

      return this.lastProgress + (this.progress - this.lastProgress) * var1;
   }

   public float func_145865_b(float var1) {
      return this.extending?(this.func_145860_a(var1) - 1.0F) * (float)Facing.offsetsXForSide[this.storedOrientation]:(1.0F - this.func_145860_a(var1)) * (float)Facing.offsetsXForSide[this.storedOrientation];
   }

   public float func_145862_c(float var1) {
      return this.extending?(this.func_145860_a(var1) - 1.0F) * (float)Facing.offsetsYForSide[this.storedOrientation]:(1.0F - this.func_145860_a(var1)) * (float)Facing.offsetsYForSide[this.storedOrientation];
   }

   public float func_145859_d(float var1) {
      return this.extending?(this.func_145860_a(var1) - 1.0F) * (float)Facing.offsetsZForSide[this.storedOrientation]:(1.0F - this.func_145860_a(var1)) * (float)Facing.offsetsZForSide[this.storedOrientation];
   }

   private void func_145863_a(float var1, float var2) {
      if(this.extending) {
         var1 = 1.0F - var1;
      } else {
         --var1;
      }

      AxisAlignedBB var3 = Blocks.piston_extension.func_149964_a(super.worldObj, super.xCoord, super.yCoord, super.zCoord, this.storedBlock, var1, this.storedOrientation);
      if(var3 != null) {
         List var4 = super.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, var3);
         if(!var4.isEmpty()) {
            this.pushedObjects.addAll(var4);
            Iterator var5 = this.pushedObjects.iterator();

            while(var5.hasNext()) {
               Entity var6 = (Entity)var5.next();
               var6.moveEntity((double)(var2 * (float)Facing.offsetsXForSide[this.storedOrientation]), (double)(var2 * (float)Facing.offsetsYForSide[this.storedOrientation]), (double)(var2 * (float)Facing.offsetsZForSide[this.storedOrientation]));
            }

            this.pushedObjects.clear();
         }
      }

   }

   public void clearPistonTileEntity() {
      if(this.lastProgress < 1.0F && super.worldObj != null) {
         this.lastProgress = this.progress = 1.0F;
         super.worldObj.removeTileEntity(super.xCoord, super.yCoord, super.zCoord);
         this.invalidate();
         if(super.worldObj.getBlock(super.xCoord, super.yCoord, super.zCoord) == Blocks.piston_extension) {
            super.worldObj.setBlock(super.xCoord, super.yCoord, super.zCoord, this.storedBlock, this.storedMetadata, 3);
            super.worldObj.notifyBlockOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.storedBlock);
         }
      }

   }

   public void updateEntity() {
      this.lastProgress = this.progress;
      if(this.lastProgress >= 1.0F) {
         this.func_145863_a(1.0F, 0.25F);
         super.worldObj.removeTileEntity(super.xCoord, super.yCoord, super.zCoord);
         this.invalidate();
         if(super.worldObj.getBlock(super.xCoord, super.yCoord, super.zCoord) == Blocks.piston_extension) {
            super.worldObj.setBlock(super.xCoord, super.yCoord, super.zCoord, this.storedBlock, this.storedMetadata, 3);
            super.worldObj.notifyBlockOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.storedBlock);
         }

      } else {
         this.progress += 0.5F;
         if(this.progress >= 1.0F) {
            this.progress = 1.0F;
         }

         if(this.extending) {
            this.func_145863_a(this.progress, this.progress - this.lastProgress + 0.0625F);
         }

      }
   }

   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      this.storedBlock = Block.getBlockById(var1.getInteger("blockId"));
      this.storedMetadata = var1.getInteger("blockData");
      this.storedOrientation = var1.getInteger("facing");
      this.lastProgress = this.progress = var1.getFloat("progress");
      this.extending = var1.getBoolean("extending");
   }

   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      var1.setInteger("blockId", Block.getIdFromBlock(this.storedBlock));
      var1.setInteger("blockData", this.storedMetadata);
      var1.setInteger("facing", this.storedOrientation);
      var1.setFloat("progress", this.lastProgress);
      var1.setBoolean("extending", this.extending);
   }
}
