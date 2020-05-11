package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRedstone extends Item {

   public ItemRedstone() {
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var3.getBlock(var4, var5, var6) != Blocks.snow_layer) {
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

         if(!var3.isAirBlock(var4, var5, var6)) {
            return false;
         }
      }

      if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
         return false;
      } else {
         if(Blocks.redstone_wire.canPlaceBlockAt(var3, var4, var5, var6)) {
            --var1.stackSize;
            var3.setBlock(var4, var5, var6, Blocks.redstone_wire);
         }

         return true;
      }
   }
}
