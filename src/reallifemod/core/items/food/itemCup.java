package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.items.CupType;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ExtendedItemFood;

public class itemCup extends ExtendedItemFood {

   public CupType theType;


   public itemCup(int p_i45340_1_, boolean p_i45340_2_, CupType type) {
      super(p_i45340_1_, p_i45340_2_);
      this.theType = type;
      this.func_111206_d("reallifemod:item" + type.name().toLowerCase());
      this.func_77655_b(type.name().toLowerCase());
      this.func_77637_a(RealLifeMod.rlmfood);
      this.setPrice(1.5D);
   }
}
