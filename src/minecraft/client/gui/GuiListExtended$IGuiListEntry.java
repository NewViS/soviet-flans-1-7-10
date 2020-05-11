package net.minecraft.client.gui;

import net.minecraft.client.renderer.Tessellator;

public interface GuiListExtended$IGuiListEntry {

   void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9);

   boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6);

   void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6);
}
