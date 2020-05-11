package net.minecraft.init;

import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Bootstrap$5$1;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

final class Bootstrap$5 implements IBehaviorDispenseItem {

   private final BehaviorDefaultDispenseItem field_150843_b = new BehaviorDefaultDispenseItem();


   public ItemStack dispense(IBlockSource var1, ItemStack var2) {
      return ItemPotion.isSplash(var2.getItemDamage())?(new Bootstrap$5$1(this, var2)).dispense(var1, var2):this.field_150843_b.dispense(var1, var2);
   }
}
