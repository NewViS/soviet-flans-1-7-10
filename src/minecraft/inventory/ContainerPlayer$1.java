package net.minecraft.inventory;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

class ContainerPlayer$1 extends Slot {

   // $FF: synthetic field
   final int armorType;
   // $FF: synthetic field
   final ContainerPlayer parent;


   ContainerPlayer$1(ContainerPlayer var1, IInventory var2, int var3, int var4, int var5, int var6) {
      super(var2, var3, var4, var5);
      this.parent = var1;
      this.armorType = var6;
   }

   public int getSlotStackLimit() {
      return 1;
   }

   public boolean isItemValid(ItemStack var1) {
      return var1 == null?false:(var1.getItem() instanceof ItemArmor?((ItemArmor)var1.getItem()).armorType == this.armorType:(var1.getItem() != Item.getItemFromBlock(Blocks.pumpkin) && var1.getItem() != Items.skull?false:this.armorType == 0));
   }

   public IIcon getBackgroundIconIndex() {
      return ItemArmor.func_94602_b(this.armorType);
   }
}
