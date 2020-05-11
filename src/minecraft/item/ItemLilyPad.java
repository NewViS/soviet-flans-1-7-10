package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.world.World;

public class ItemLilyPad extends ItemColored {

   public ItemLilyPad(Block var1) {
      super(var1, false);
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(var2, var3, true);
      if(var4 == null) {
         return var1;
      } else {
         if(var4.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
            int var5 = var4.blockX;
            int var6 = var4.blockY;
            int var7 = var4.blockZ;
            if(!var2.canMineBlock(var3, var5, var6, var7)) {
               return var1;
            }

            if(!var3.canPlayerEdit(var5, var6, var7, var4.sideHit, var1)) {
               return var1;
            }

            if(var2.getBlock(var5, var6, var7).getMaterial() == Material.water && var2.getBlockMetadata(var5, var6, var7) == 0 && var2.isAirBlock(var5, var6 + 1, var7)) {
               var2.setBlock(var5, var6 + 1, var7, Blocks.waterlily);
               if(!var3.capabilities.isCreativeMode) {
                  --var1.stackSize;
               }
            }
         }

         return var1;
      }
   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      return Blocks.waterlily.getRenderColor(var1.getItemDamage());
   }
}
