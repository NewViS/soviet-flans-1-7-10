package net.minecraft.tileentity;

import net.minecraft.tileentity.TileEntityDispenser;

public class TileEntityDropper extends TileEntityDispenser {

   public String getInventoryName() {
      return this.hasCustomInventoryName()?super.field_146020_a:"container.dropper";
   }
}
