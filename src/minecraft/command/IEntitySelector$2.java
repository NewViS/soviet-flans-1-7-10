package net.minecraft.command;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

final class IEntitySelector$2 implements IEntitySelector {

   public boolean isEntityApplicable(Entity var1) {
      return var1.isEntityAlive() && var1.riddenByEntity == null && var1.ridingEntity == null;
   }
}
