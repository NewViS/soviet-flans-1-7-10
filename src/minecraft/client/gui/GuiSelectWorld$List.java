package net.minecraft.client.gui;

import java.util.Date;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.SaveFormatComparator;

class GuiSelectWorld$List extends GuiSlot {

   // $FF: synthetic field
   final GuiSelectWorld field_148207_k;


   public GuiSelectWorld$List(GuiSelectWorld var1) {
      super(var1.mc, var1.width, var1.height, 32, var1.height - 64, 36);
      this.field_148207_k = var1;
   }

   protected int getSize() {
      return GuiSelectWorld.access$000(this.field_148207_k).size();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      GuiSelectWorld.access$102(this.field_148207_k, var1);
      boolean var5 = GuiSelectWorld.access$100(this.field_148207_k) >= 0 && GuiSelectWorld.access$100(this.field_148207_k) < this.getSize();
      GuiSelectWorld.access$200(this.field_148207_k).enabled = var5;
      GuiSelectWorld.access$300(this.field_148207_k).enabled = var5;
      GuiSelectWorld.access$400(this.field_148207_k).enabled = var5;
      GuiSelectWorld.access$500(this.field_148207_k).enabled = var5;
      if(var2 && var5) {
         this.field_148207_k.func_146615_e(var1);
      }

   }

   protected boolean isSelected(int var1) {
      return var1 == GuiSelectWorld.access$100(this.field_148207_k);
   }

   protected int getContentHeight() {
      return GuiSelectWorld.access$000(this.field_148207_k).size() * 36;
   }

   protected void drawBackground() {
      this.field_148207_k.drawDefaultBackground();
   }

   protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7) {
      SaveFormatComparator var8 = (SaveFormatComparator)GuiSelectWorld.access$000(this.field_148207_k).get(var1);
      String var9 = var8.getDisplayName();
      if(var9 == null || MathHelper.stringNullOrLengthZero(var9)) {
         var9 = GuiSelectWorld.access$600(this.field_148207_k) + " " + (var1 + 1);
      }

      String var10 = var8.getFileName();
      var10 = var10 + " (" + GuiSelectWorld.access$700(this.field_148207_k).format(new Date(var8.getLastTimePlayed()));
      var10 = var10 + ")";
      String var11 = "";
      if(var8.requiresConversion()) {
         var11 = GuiSelectWorld.access$800(this.field_148207_k) + " " + var11;
      } else {
         var11 = GuiSelectWorld.access$900(this.field_148207_k)[var8.getEnumGameType().getID()];
         if(var8.isHardcoreModeEnabled()) {
            var11 = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore", new Object[0]) + EnumChatFormatting.RESET;
         }

         if(var8.getCheatsEnabled()) {
            var11 = var11 + ", " + I18n.format("selectWorld.cheats", new Object[0]);
         }
      }

      this.field_148207_k.drawString(this.field_148207_k.fontRendererObj, var9, var2 + 2, var3 + 1, 16777215);
      this.field_148207_k.drawString(this.field_148207_k.fontRendererObj, var10, var2 + 2, var3 + 12, 8421504);
      this.field_148207_k.drawString(this.field_148207_k.fontRendererObj, var11, var2 + 2, var3 + 12 + 10, 8421504);
   }
}
