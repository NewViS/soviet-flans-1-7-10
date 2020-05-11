package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBoat extends Item {

   public ItemBoat() {
      super.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabTransport);
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      float var4 = 1.0F;
      float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var4;
      float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var4;
      double var7 = var3.prevPosX + (var3.posX - var3.prevPosX) * (double)var4;
      double var9 = var3.prevPosY + (var3.posY - var3.prevPosY) * (double)var4 + 1.62D - (double)var3.yOffset;
      double var11 = var3.prevPosZ + (var3.posZ - var3.prevPosZ) * (double)var4;
      Vec3 var13 = Vec3.createVectorHelper(var7, var9, var11);
      float var14 = MathHelper.cos(-var6 * 0.017453292F - 3.1415927F);
      float var15 = MathHelper.sin(-var6 * 0.017453292F - 3.1415927F);
      float var16 = -MathHelper.cos(-var5 * 0.017453292F);
      float var17 = MathHelper.sin(-var5 * 0.017453292F);
      float var18 = var15 * var16;
      float var20 = var14 * var16;
      double var21 = 5.0D;
      Vec3 var23 = var13.addVector((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
      MovingObjectPosition var24 = var2.rayTraceBlocks(var13, var23, true);
      if(var24 == null) {
         return var1;
      } else {
         Vec3 var25 = var3.getLook(var4);
         boolean var26 = false;
         float var27 = 1.0F;
         List var28 = var2.getEntitiesWithinAABBExcludingEntity(var3, var3.boundingBox.addCoord(var25.xCoord * var21, var25.yCoord * var21, var25.zCoord * var21).expand((double)var27, (double)var27, (double)var27));

         int var29;
         for(var29 = 0; var29 < var28.size(); ++var29) {
            Entity var30 = (Entity)var28.get(var29);
            if(var30.canBeCollidedWith()) {
               float var31 = var30.getCollisionBorderSize();
               AxisAlignedBB var32 = var30.boundingBox.expand((double)var31, (double)var31, (double)var31);
               if(var32.isVecInside(var13)) {
                  var26 = true;
               }
            }
         }

         if(var26) {
            return var1;
         } else {
            if(var24.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
               var29 = var24.blockX;
               int var33 = var24.blockY;
               int var34 = var24.blockZ;
               if(var2.getBlock(var29, var33, var34) == Blocks.snow_layer) {
                  --var33;
               }

               EntityBoat var35 = new EntityBoat(var2, (double)((float)var29 + 0.5F), (double)((float)var33 + 1.0F), (double)((float)var34 + 0.5F));
               var35.rotationYaw = (float)(((MathHelper.floor_double((double)(var3.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);
               if(!var2.getCollidingBoundingBoxes(var35, var35.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                  return var1;
               }

               if(!var2.isRemote) {
                  var2.spawnEntityInWorld(var35);
               }

               if(!var3.capabilities.isCreativeMode) {
                  --var1.stackSize;
               }
            }

            return var1;
         }
      }
   }
}
