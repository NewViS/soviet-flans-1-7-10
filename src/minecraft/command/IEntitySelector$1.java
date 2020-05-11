package net.minecraft.command;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

final class IEntitySelector$1 implements IEntitySelector {

   public boolean isEntityApplicable(Entity var1) {
      return var1.isEntityAlive();
   }
}
