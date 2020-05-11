package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSnow extends ItemBlockWithMetadata {

   public ItemSnow(Block var1, Block var2) {
      super(var1, var2);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var1.stackSize == 0) {
         return false;
      } else if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
         return false;
      } else {
         Block var11 = var3.getBlock(var4, var5, var6);
         if(var11 == Blocks.snow_layer) {
            int var12 = var3.getBlockMetadata(var4, var5, var6);
            int var13 = var12 & 7;
            if(var13 <= 6 && var3.checkNoEntityCollision(super.field_150939_a.getCollisionBoundingBoxFromPool(var3, var4, var5, var6)) && var3.setBlockMetadataWithNotify(var4, var5, var6, var13 + 1 | var12 & -8, 2)) {
               var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), super.field_150939_a.stepSound.func_150496_b(), (super.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, super.field_150939_a.stepSound.getPitch() * 0.8F);
               --var1.stackSize;
               return true;
            }
         }

         return super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
      }
   }
}
