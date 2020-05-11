package de.ItsAMysterious.mods.reallifemod.core.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;

public class GuiImageSelector extends GuiListExtended {

   protected final Minecraft minecraft;


   public GuiImageSelector(Minecraft mc, int p_i45010_2_, int p_i45010_3_, int p_i45010_4_, int p_i45010_5_, int p_i45010_6_) {
      super(mc, p_i45010_2_, p_i45010_3_, p_i45010_4_, p_i45010_5_, p_i45010_6_);
      this.minecraft = mc;
   }

   public IGuiListEntry func_148180_b(int p_148180_1_) {
      return null;
   }

   protected int func_148127_b() {
      return 0;
   }

   public String getImages() {
      return this.minecraft.getResourcePackRepository().toString().replace("resourcepacks", "Images");
   }

   protected void func_148129_a(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
      String s = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.BOLD + this.getImages();
      this.minecraft.fontRenderer.drawString(s, 20, 20, 16777215);
   }
}
