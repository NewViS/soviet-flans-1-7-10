package de.ItsAMysterious.mods.reallifemod.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemMP3Player extends Item {

   public ItemMP3Player() {
      this.func_77655_b("MP3Player");
      this.func_111206_d("reallifemod:MP3Player");
      this.func_77637_a(RealLifeMod.RLMTechnologie);
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
      list.add(EnumChatFormatting.GOLD + "Function:");
      list.add(EnumChatFormatting.AQUA + "Easily build roads and stuff");
   }
}
