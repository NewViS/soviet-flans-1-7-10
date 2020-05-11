package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class ItemEnderEye extends Item {

   public ItemEnderEye() {
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      Block var11 = var3.getBlock(var4, var5, var6);
      int var12 = var3.getBlockMetadata(var4, var5, var6);
      if(var2.canPlayerEdit(var4, var5, var6, var7, var1) && var11 == Blocks.end_portal_frame && !BlockEndPortalFrame.isEnderEyeInserted(var12)) {
         if(var3.isRemote) {
            return true;
         } else {
            var3.setBlockMetadataWithNotify(var4, var5, var6, var12 + 4, 2);
            var3.func_147453_f(var4, var5, var6, Blocks.end_portal_frame);
            --var1.stackSize;

            int var13;
            for(var13 = 0; var13 < 16; ++var13) {
               double var14 = (double)((float)var4 + (5.0F + Item.itemRand.nextFloat() * 6.0F) / 16.0F);
               double var16 = (double)((float)var5 + 0.8125F);
               double var18 = (double)((float)var6 + (5.0F + Item.itemRand.nextFloat() * 6.0F) / 16.0F);
               double var20 = 0.0D;
               double var22 = 0.0D;
               double var24 = 0.0D;
               var3.spawnParticle("smoke", var14, var16, var18, var20, var22, var24);
            }

            var13 = var12 & 3;
            int var26 = 0;
            int var15 = 0;
            boolean var27 = false;
            boolean var17 = true;
            int var28 = Direction.rotateRight[var13];

            int var19;
            int var21;
            int var29;
            for(var19 = -2; var19 <= 2; ++var19) {
               var29 = var4 + Direction.offsetX[var28] * var19;
               var21 = var6 + Direction.offsetZ[var28] * var19;
               if(var3.getBlock(var29, var5, var21) == Blocks.end_portal_frame) {
                  if(!BlockEndPortalFrame.isEnderEyeInserted(var3.getBlockMetadata(var29, var5, var21))) {
                     var17 = false;
                     break;
                  }

                  var15 = var19;
                  if(!var27) {
                     var26 = var19;
                     var27 = true;
                  }
               }
            }

            if(var17 && var15 == var26 + 2) {
               for(var19 = var26; var19 <= var15; ++var19) {
                  var29 = var4 + Direction.offsetX[var28] * var19;
                  var21 = var6 + Direction.offsetZ[var28] * var19;
                  var29 += Direction.offsetX[var13] * 4;
                  var21 += Direction.offsetZ[var13] * 4;
                  if(var3.getBlock(var29, var5, var21) != Blocks.end_portal_frame || !BlockEndPortalFrame.isEnderEyeInserted(var3.getBlockMetadata(var29, var5, var21))) {
                     var17 = false;
                     break;
                  }
               }

               int var30;
               for(var19 = var26 - 1; var19 <= var15 + 1; var19 += 4) {
                  for(var29 = 1; var29 <= 3; ++var29) {
                     var21 = var4 + Direction.offsetX[var28] * var19;
                     var30 = var6 + Direction.offsetZ[var28] * var19;
                     var21 += Direction.offsetX[var13] * var29;
                     var30 += Direction.offsetZ[var13] * var29;
                     if(var3.getBlock(var21, var5, var30) != Blocks.end_portal_frame || !BlockEndPortalFrame.isEnderEyeInserted(var3.getBlockMetadata(var21, var5, var30))) {
                        var17 = false;
                        break;
                     }
                  }
               }

               if(var17) {
                  for(var19 = var26; var19 <= var15; ++var19) {
                     for(var29 = 1; var29 <= 3; ++var29) {
                        var21 = var4 + Direction.offsetX[var28] * var19;
                        var30 = var6 + Direction.offsetZ[var28] * var19;
                        var21 += Direction.offsetX[var13] * var29;
                        var30 += Direction.offsetZ[var13] * var29;
                        var3.setBlock(var21, var5, var30, Blocks.end_portal, 0, 2);
                     }
                  }
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(var2, var3, false);
      if(var4 != null && var4.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK && var2.getBlock(var4.blockX, var4.blockY, var4.blockZ) == Blocks.end_portal_frame) {
         return var1;
      } else {
         if(!var2.isRemote) {
            ChunkPosition var5 = var2.findClosestStructure("Stronghold", (int)var3.posX, (int)var3.posY, (int)var3.posZ);
            if(var5 != null) {
               EntityEnderEye var6 = new EntityEnderEye(var2, var3.posX, var3.posY + 1.62D - (double)var3.yOffset, var3.posZ);
               var6.moveTowards((double)var5.chunkPosX, var5.chunkPosY, (double)var5.chunkPosZ);
               var2.spawnEntityInWorld(var6);
               var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));
               var2.playAuxSFXAtEntity((EntityPlayer)null, 1002, (int)var3.posX, (int)var3.posY, (int)var3.posZ, 0);
               if(!var3.capabilities.isCreativeMode) {
                  --var1.stackSize;
               }
            }
         }

         return var1;
      }
   }
}
