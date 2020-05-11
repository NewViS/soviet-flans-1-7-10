package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSeeds extends Item {

   private Block field_150925_a;
   private Block soilBlockID;


   public ItemSeeds(Block var1, Block var2) {
      this.field_150925_a = var1;
      this.soilBlockID = var2;
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var7 != 1) {
         return false;
      } else if(var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1)) {
         if(var3.getBlock(var4, var5, var6) == this.soilBlockID && var3.isAirBlock(var4, var5 + 1, var6)) {
            var3.setBlock(var4, var5 + 1, var6, this.field_150925_a);
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
