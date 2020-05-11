package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.util.EnumChatFormatting;

public abstract class GuiResourcePackList extends GuiListExtended {

   protected final Minecraft field_148205_k;
   protected final List field_148204_l;


   public GuiResourcePackList(Minecraft var1, int var2, int var3, List var4) {
      super(var1, var2, var3, 32, var3 - 55 + 4, 36);
      this.field_148205_k = var1;
      this.field_148204_l = var4;
      super.field_148163_i = false;
      this.setHasListHeader(true, (int)((float)var1.fontRenderer.FONT_HEIGHT * 1.5F));
   }

   protected void drawListHeader(int var1, int var2, Tessellator var3) {
      String var4 = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.BOLD + this.func_148202_k();
      this.field_148205_k.fontRenderer.drawString(var4, var1 + super.width / 2 - this.field_148205_k.fontRenderer.getStringWidth(var4) / 2, Math.min(super.top + 3, var2), 16777215);
   }

   protected abstract String func_148202_k();

   public List func_148201_l() {
      return this.field_148204_l;
   }

   protected int getSize() {
      return this.func_148201_l().size();
   }

   public ResourcePackListEntry getListEntry(int var1) {
      return (ResourcePackListEntry)this.func_148201_l().get(var1);
   }

   public int getListWidth() {
      return super.width;
   }

   protected int getScrollBarX() {
      return super.right - 6;
   }

   // $FF: synthetic method
   public GuiListExtended$IGuiListEntry getListEntry(int var1) {
      return this.getListEntry(var1);
   }
}
