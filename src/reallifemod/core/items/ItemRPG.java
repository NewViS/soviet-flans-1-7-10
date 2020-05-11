package de.ItsAMysterious.mods.reallifemod.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemRPG extends Item {

   public ItemRPG() {
      this.func_77664_n();
      this.func_111206_d("reallifemod:RPG");
      this.func_77655_b("RPG");
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
      list.add(EnumChatFormatting.GOLD + "Function:");
      list.add(EnumChatFormatting.AQUA + "Easily build roads and stuff");
   }
}
