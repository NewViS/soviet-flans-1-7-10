package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;

public class ItemBattleAxe extends ItemSword {

   public ItemBattleAxe() {
      super(ToolMaterial.EMERALD);
      this.func_77625_d(1);
      this.func_77655_b("ironBattleaxe");
      this.func_111206_d("reallifemod:ironBattleaxe");
      this.func_77637_a(RealLifeMod.Work);
   }

   public ItemStack func_77659_a(ItemStack stack, World p_77659_2_, EntityPlayer p_77659_3_) {
      return stack;
   }
}
