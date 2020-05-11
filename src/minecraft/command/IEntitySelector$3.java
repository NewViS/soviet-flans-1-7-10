package net.minecraft.command;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;

final class IEntitySelector$3 implements IEntitySelector {

   public boolean isEntityApplicable(Entity var1) {
      return var1 instanceof IInventory && var1.isEntityAlive();
   }
}
