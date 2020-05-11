package net.minecraft.init;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

final class Bootstrap$12 extends BehaviorDefaultDispenseItem {

   private boolean field_150839_b = true;


   protected ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.func_149937_b(var1.getBlockMetadata());
      World var4 = var1.getWorld();
      int var5 = var1.getXInt() + var3.getFrontOffsetX();
      int var6 = var1.getYInt() + var3.getFrontOffsetY();
      int var7 = var1.getZInt() + var3.getFrontOffsetZ();
      if(var4.isAirBlock(var5, var6, var7)) {
         var4.setBlock(var5, var6, var7, Blocks.fire);
         if(var2.attemptDamageItem(1, var4.rand)) {
            var2.stackSize = 0;
         }
      } else if(var4.getBlock(var5, var6, var7) == Blocks.tnt) {
         Blocks.tnt.onBlockDestroyedByPlayer(var4, var5, var6, var7, 1);
         var4.setBlockToAir(var5, var6, var7);
      } else {
         this.field_150839_b = false;
      }

      return var2;
   }

   protected void playDispenseSound(IBlockSource var1) {
      if(this.field_150839_b) {
         var1.getWorld().playAuxSFX(1000, var1.getXInt(), var1.getYInt(), var1.getZInt(), 0);
      } else {
         var1.getWorld().playAuxSFX(1001, var1.getXInt(), var1.getYInt(), var1.getZInt(), 0);
      }

   }
}
