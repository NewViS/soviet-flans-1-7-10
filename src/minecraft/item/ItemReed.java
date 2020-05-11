package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemReed extends Item {

   private Block field_150935_a;


   public ItemReed(Block var1) {
      this.field_150935_a = var1;
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      Block var11 = var3.getBlock(var4, var5, var6);
      if(var11 == Blocks.snow_layer && (var3.getBlockMetadata(var4, var5, var6) & 7) < 1) {
         var7 = 1;
      } else if(var11 != Blocks.vine && var11 != Blocks.tallgrass && var11 != Blocks.deadbush) {
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
      }

      if(!var2.canPlayerEdit(var4, var5, var6, var7, var1)) {
         return false;
      } else if(var1.stackSize == 0) {
         return false;
      } else {
         if(var3.canPlaceEntityOnSide(this.field_150935_a, var4, var5, var6, false, var7, (Entity)null, var1)) {
            int var12 = this.field_150935_a.onBlockPlaced(var3, var4, var5, var6, var7, var8, var9, var10, 0);
            if(var3.setBlock(var4, var5, var6, this.field_150935_a, var12, 3)) {
               if(var3.getBlock(var4, var5, var6) == this.field_150935_a) {
                  this.field_150935_a.onBlockPlacedBy(var3, var4, var5, var6, var2, var1);
                  this.field_150935_a.onPostBlockPlaced(var3, var4, var5, var6, var12);
               }

               var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), this.field_150935_a.stepSound.func_150496_b(), (this.field_150935_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150935_a.stepSound.getPitch() * 0.8F);
               --var1.stackSize;
            }
         }

         return true;
      }
   }
}
