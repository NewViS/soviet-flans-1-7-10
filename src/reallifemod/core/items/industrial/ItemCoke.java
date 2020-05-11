package de.ItsAMysterious.mods.reallifemod.core.items.industrial;

import cpw.mods.fml.common.IFuelHandler;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import net.minecraft.item.ItemStack;

public class ItemCoke extends ExtendedItem implements IFuelHandler {

   public ItemCoke() {
      this.func_77655_b("coke");
      this.func_111206_d("reallifemod:itemCoke");
      this.func_77656_e(0);
      this.setPrice(15.0D);
   }

   public int getBurnTime(ItemStack fuel) {
      return 10000;
   }
}
