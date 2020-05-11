package net.minecraft.tileentity;

import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDaylightDetector extends TileEntity {

   public void updateEntity() {
      if(super.worldObj != null && !super.worldObj.isRemote && super.worldObj.getTotalWorldTime() % 20L == 0L) {
         super.blockType = this.getBlockType();
         if(super.blockType instanceof BlockDaylightDetector) {
            ((BlockDaylightDetector)super.blockType).func_149957_e(super.worldObj, super.xCoord, super.yCoord, super.zCoord);
         }
      }

   }
}
