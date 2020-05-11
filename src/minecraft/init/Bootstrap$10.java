package net.minecraft.init;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

final class Bootstrap$10 extends BehaviorDefaultDispenseItem {

   private final BehaviorDefaultDispenseItem field_150841_b = new BehaviorDefaultDispenseItem();


   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      ItemBucket var3 = (ItemBucket)var2.getItem();
      int var4 = var1.getXInt();
      int var5 = var1.getYInt();
      int var6 = var1.getZInt();
      EnumFacing var7 = BlockDispenser.func_149937_b(var1.getBlockMetadata());
      if(var3.tryPlaceContainedLiquid(var1.getWorld(), var4 + var7.getFrontOffsetX(), var5 + var7.getFrontOffsetY(), var6 + var7.getFrontOffsetZ())) {
         var2.func_150996_a(Items.bucket);
         var2.stackSize = 1;
         return var2;
      } else {
         return this.field_150841_b.dispense(var1, var2);
      }
   }
}
