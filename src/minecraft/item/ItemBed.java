package net.minecraft.item;

import net.minecraft.block.BlockBed;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBed extends Item {

   public ItemBed() {
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var3.isRemote) {
         return true;
      } else if(var7 != 1) {
         return false;
      } else {
         ++var5;
         BlockBed var11 = (BlockBed)Blocks.bed;
         int var12 = MathHelper.floor_double((double)(var2.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
         byte var13 = 0;
         byte var14 = 0;
         if(var12 == 0) {
            var14 = 1;
         }

         if(var12 == 1) {
            var13 = -1;
         }

         if(var12 == 2) {
            var14 = -1;
         }

         if(var12 == 3) {
            var13 = 1;
         }

         if(var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4 + var13, var5, var6 + var14, var7, var1)) {
            if(var3.isAirBlock(var4, var5, var6) && var3.isAirBlock(var4 + var13, var5, var6 + var14) && World.doesBlockHaveSolidTopSurface(var3, var4, var5 - 1, var6) && World.doesBlockHaveSolidTopSurface(var3, var4 + var13, var5 - 1, var6 + var14)) {
               var3.setBlock(var4, var5, var6, var11, var12, 3);
               if(var3.getBlock(var4, var5, var6) == var11) {
                  var3.setBlock(var4 + var13, var5, var6 + var14, var11, var12 + 8, 3);
               }

               --var1.stackSize;
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }
}
