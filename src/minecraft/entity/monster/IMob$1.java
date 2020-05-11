package net.minecraft.entity.monster;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;

final class IMob$1 implements IEntitySelector {

   public boolean isEntityApplicable(Entity var1) {
      return var1 instanceof IMob;
   }
}
