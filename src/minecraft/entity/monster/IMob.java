package net.minecraft.entity.monster;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.monster.IMob$1;
import net.minecraft.entity.passive.IAnimals;

public interface IMob extends IAnimals {

   IEntitySelector mobSelector = new IMob$1();


}
