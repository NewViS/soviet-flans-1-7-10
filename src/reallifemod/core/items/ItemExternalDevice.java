package de.ItsAMysterious.mods.reallifemod.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemExternalDevice extends Item {

   private int size;


   public ItemExternalDevice() {
      this.func_77655_b("SDCard");
      this.func_111206_d("reallifemod:MicroSD");
      this.func_77637_a(RealLifeMod.RLMTechnologie);
   }

   public void setCapabillities(int size) {
      this.size = size;
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
      list.add(EnumChatFormatting.GOLD + "Function:");
      list.add(EnumChatFormatting.AQUA + "Store and share files(e.g Books)");
   }
}
