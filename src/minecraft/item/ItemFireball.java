package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFireball extends Item {

   public ItemFireball() {
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var3.isRemote) {
         return true;
      } else {
         if(var7 == 0) {
            --var5;
         }

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
         } else {
            if(var3.getBlock(var4, var5, var6).getMaterial() == Material.air) {
               var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "fire.ignite", 1.0F, Item.itemRand.nextFloat() * 0.4F + 0.8F);
               var3.setBlock(var4, var5, var6, Blocks.fire);
            }

            if(!var2.capabilities.isCreativeMode) {
               --var1.stackSize;
            }

            return true;
         }
      }
   }
}
