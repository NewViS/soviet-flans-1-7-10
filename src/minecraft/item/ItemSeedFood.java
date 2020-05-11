package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSeedFood extends ItemFood {

   private Block field_150908_b;
   private Block soilId;


   public ItemSeedFood(int var1, float var2, Block var3, Block var4) {
      super(var1, var2, false);
      this.field_150908_b = var3;
      this.soilId = var4;
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var7 != 1) {
         return false;
      } else if(var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1)) {
         if(var3.getBlock(var4, var5, var6) == this.soilId && var3.isAirBlock(var4, var5 + 1, var6)) {
            var3.setBlock(var4, var5 + 1, var6, this.field_150908_b);
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
