package de.ItsAMysterious.mods.reallifemod.core.gui.containers.slots;

import de.ItsAMysterious.mods.reallifemod.core.gui.containers.ContainerDrawer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDrawer extends Slot {

   final ContainerDrawer Drawer;


   public SlotDrawer(ContainerDrawer drawer, IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
      super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
      this.Drawer = drawer;
   }

   public boolean func_75214_a(ItemStack stack) {
      return true;
   }

   public int func_75219_a() {
      return 64;
   }
}
