package de.ItsAMysterious.mods.reallifemod.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBinoculars extends Item {

   public ItemBinoculars() {
      this.func_77655_b("itemBinoculars");
      this.func_111206_d("reallifemod:binoculars");
      this.func_77637_a(RealLifeMod.Work);
   }

   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
      return stack;
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
      list.add(EnumChatFormatting.GOLD + "Function:");
      list.add(EnumChatFormatting.AQUA + "Easily build roads and stuff");
   }
}
