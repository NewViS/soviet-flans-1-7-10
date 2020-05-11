package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

class GuiContainerCreative$CreativeSlot extends Slot {

   private final Slot field_148332_b;
   // $FF: synthetic field
   final GuiContainerCreative field_148333_a;


   public GuiContainerCreative$CreativeSlot(GuiContainerCreative var1, Slot var2, int var3) {
      super(var2.inventory, var3, 0, 0);
      this.field_148333_a = var1;
      this.field_148332_b = var2;
   }

   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      this.field_148332_b.onPickupFromSlot(var1, var2);
   }

   public boolean isItemValid(ItemStack var1) {
      return this.field_148332_b.isItemValid(var1);
   }

   public ItemStack getStack() {
      return this.field_148332_b.getStack();
   }

   public boolean getHasStack() {
      return this.field_148332_b.getHasStack();
   }

   public void putStack(ItemStack var1) {
      this.field_148332_b.putStack(var1);
   }

   public void onSlotChanged() {
      this.field_148332_b.onSlotChanged();
   }

   public int getSlotStackLimit() {
      return this.field_148332_b.getSlotStackLimit();
   }

   public IIcon getBackgroundIconIndex() {
      return this.field_148332_b.getBackgroundIconIndex();
   }

   public ItemStack decrStackSize(int var1) {
      return this.field_148332_b.decrStackSize(var1);
   }

   public boolean isSlotInInventory(IInventory var1, int var2) {
      return this.field_148332_b.isSlotInInventory(var1, var2);
   }

   // $FF: synthetic method
   static Slot access$100(GuiContainerCreative$CreativeSlot var0) {
      return var0.field_148332_b;
   }
}
