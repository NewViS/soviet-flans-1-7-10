package de.ItsAMysterious.mods.reallifemod.core.items.industrial;

import de.ItsAMysterious.mods.reallifemod.api.customtools.KruppAxe;
import net.minecraft.item.Item.ToolMaterial;

public class ItemSteelAxe extends KruppAxe {

   public ItemSteelAxe(ToolMaterial toolmaterial) {
      super(toolmaterial);
      this.func_77655_b("steelAxe");
      this.func_111206_d("reallifemod:steelAxe");
   }
}
