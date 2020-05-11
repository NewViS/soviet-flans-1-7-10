package net.minecraft.command;

import net.minecraft.command.IEntitySelector$1;
import net.minecraft.command.IEntitySelector$2;
import net.minecraft.command.IEntitySelector$3;
import net.minecraft.entity.Entity;

public interface IEntitySelector {

   IEntitySelector selectAnything = new IEntitySelector$1();
   IEntitySelector field_152785_b = new IEntitySelector$2();
   IEntitySelector selectInventories = new IEntitySelector$3();


   boolean isEntityApplicable(Entity var1);

}
