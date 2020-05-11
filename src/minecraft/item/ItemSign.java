package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemSign extends Item {

   public ItemSign() {
      super.maxStackSize = 16;
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var7 == 0) {
         return false;
      } else if(!var3.getBlock(var4, var5, var6).getMaterial().isSolid()) {
         return false;
      } else {
         if(var7 == 1) {
            ++var5;
         }

         if(var7 == 2) {
            --var6;
         }

         if(var7 == 3) {
            ++var6;
         }

         if(var7 == 4) {
            --var4;
         }

         if(var7 == 5) {
            ++var4;
         }

         if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
            return false;
         } else if(!Blocks.standing_sign.canPlaceBlockAt(var3, var4, var5, var6)) {
            return false;
         } else if(var3.isRemote) {
            return true;
         } else {
            if(var7 == 1) {
               int var11 = MathHelper.floor_double((double)((var2.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
               var3.setBlock(var4, var5, var6, Blocks.standing_sign, var11, 3);
            } else {
               var3.setBlock(var4, var5, var6, Blocks.wall_sign, var7, 3);
            }

            --var1.stackSize;
            TileEntitySign var12 = (TileEntitySign)var3.getTileEntity(var4, var5, var6);
            if(var12 != null) {
               var2.func_146100_a(var12);
            }

            return true;
         }
      }
   }
}
