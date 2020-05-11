package de.ItsAMysterious.mods.reallifemod.core.items.industrial;

import de.ItsAMysterious.mods.reallifemod.core.gui.CustomBlockGui;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.TileEntityCBO;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCBO extends ExtendedItem {

   public Minecraft mc;


   public ItemCBO() {
      this.func_111206_d("reallifemod:ItemCBO");
      this.func_77655_b("cboItem");
      this.setPrice(23.99D);
   }

   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
      int x = Minecraft.getMinecraft().objectMouseOver.blockX;
      int y = Minecraft.getMinecraft().objectMouseOver.blockY;
      int z = Minecraft.getMinecraft().objectMouseOver.blockZ;
      Minecraft.getMinecraft().displayGuiScreen(new CustomBlockGui(x, y, z, Minecraft.getMinecraft().thePlayer.field_71071_by, new TileEntityCBO()));
      return itemStack;
   }
}
