package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

public abstract class GuiListExtended extends GuiSlot {

   public GuiListExtended(Minecraft var1, int var2, int var3, int var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {}

   protected boolean isSelected(int var1) {
      return false;
   }

   protected void drawBackground() {}

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      this.getListEntry(var1).drawEntry(var1, var2, var3, this.getListWidth(), var4, var5, var6, var7, this.func_148124_c(var6, var7) == var1);
   }

   public boolean func_148179_a(int var1, int var2, int var3) {
      if(this.func_148141_e(var2)) {
         int var4 = this.func_148124_c(var1, var2);
         if(var4 >= 0) {
            int var5 = super.left + super.width / 2 - this.getListWidth() / 2 + 2;
            int var6 = super.top + 4 - this.getAmountScrolled() + var4 * super.slotHeight + super.headerPadding;
            int var7 = var1 - var5;
            int var8 = var2 - var6;
            if(this.getListEntry(var4).mousePressed(var4, var1, var2, var3, var7, var8)) {
               this.func_148143_b(false);
               return true;
            }
         }
      }

      return false;
   }

   public boolean func_148181_b(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.getSize(); ++var4) {
         int var5 = super.left + super.width / 2 - this.getListWidth() / 2 + 2;
         int var6 = super.top + 4 - this.getAmountScrolled() + var4 * super.slotHeight + super.headerPadding;
         int var7 = var1 - var5;
         int var8 = var2 - var6;
         this.getListEntry(var4).mouseReleased(var4, var1, var2, var3, var7, var8);
      }

      this.func_148143_b(true);
      return false;
   }

   public abstract GuiListExtended$IGuiListEntry getListEntry(int var1);
}
