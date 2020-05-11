package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiKeyBindingList$1;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;

public class GuiKeyBindingList$KeyEntry implements GuiListExtended$IGuiListEntry {

   private final KeyBinding field_148282_b;
   private final String field_148283_c;
   private final GuiButton btnChangeKeyBinding;
   private final GuiButton btnReset;
   // $FF: synthetic field
   final GuiKeyBindingList field_148284_a;


   private GuiKeyBindingList$KeyEntry(GuiKeyBindingList var1, KeyBinding var2) {
      this.field_148284_a = var1;
      this.field_148282_b = var2;
      this.field_148283_c = I18n.format(var2.getKeyDescription(), new Object[0]);
      this.btnChangeKeyBinding = new GuiButton(0, 0, 0, 75, 18, I18n.format(var2.getKeyDescription(), new Object[0]));
      this.btnReset = new GuiButton(0, 0, 0, 50, 18, I18n.format("controls.reset", new Object[0]));
   }

   public void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9) {
      boolean var10 = GuiKeyBindingList.access$200(this.field_148284_a).buttonId == this.field_148282_b;
      GuiKeyBindingList.access$100(this.field_148284_a).fontRenderer.drawString(this.field_148283_c, var2 + 90 - GuiKeyBindingList.access$300(this.field_148284_a), var3 + var5 / 2 - GuiKeyBindingList.access$100(this.field_148284_a).fontRenderer.FONT_HEIGHT / 2, 16777215);
      this.btnReset.xPosition = var2 + 190;
      this.btnReset.yPosition = var3;
      this.btnReset.enabled = this.field_148282_b.getKeyCode() != this.field_148282_b.getKeyCodeDefault();
      this.btnReset.drawButton(GuiKeyBindingList.access$100(this.field_148284_a), var7, var8);
      this.btnChangeKeyBinding.xPosition = var2 + 105;
      this.btnChangeKeyBinding.yPosition = var3;
      this.btnChangeKeyBinding.displayString = GameSettings.getKeyDisplayString(this.field_148282_b.getKeyCode());
      boolean var11 = false;
      if(this.field_148282_b.getKeyCode() != 0) {
         KeyBinding[] var12 = GuiKeyBindingList.access$100(this.field_148284_a).gameSettings.keyBindings;
         int var13 = var12.length;

         for(int var14 = 0; var14 < var13; ++var14) {
            KeyBinding var15 = var12[var14];
            if(var15 != this.field_148282_b && var15.getKeyCode() == this.field_148282_b.getKeyCode()) {
               var11 = true;
               break;
            }
         }
      }

      if(var10) {
         this.btnChangeKeyBinding.displayString = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.btnChangeKeyBinding.displayString + EnumChatFormatting.WHITE + " <";
      } else if(var11) {
         this.btnChangeKeyBinding.displayString = EnumChatFormatting.RED + this.btnChangeKeyBinding.displayString;
      }

      this.btnChangeKeyBinding.drawButton(GuiKeyBindingList.access$100(this.field_148284_a), var7, var8);
   }

   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      if(this.btnChangeKeyBinding.mousePressed(GuiKeyBindingList.access$100(this.field_148284_a), var2, var3)) {
         GuiKeyBindingList.access$200(this.field_148284_a).buttonId = this.field_148282_b;
         return true;
      } else if(this.btnReset.mousePressed(GuiKeyBindingList.access$100(this.field_148284_a), var2, var3)) {
         GuiKeyBindingList.access$100(this.field_148284_a).gameSettings.setOptionKeyBinding(this.field_148282_b, this.field_148282_b.getKeyCodeDefault());
         KeyBinding.resetKeyBindingArrayAndHash();
         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.btnChangeKeyBinding.mouseReleased(var2, var3);
      this.btnReset.mouseReleased(var2, var3);
   }

   // $FF: synthetic method
   GuiKeyBindingList$KeyEntry(GuiKeyBindingList var1, KeyBinding var2, GuiKeyBindingList$1 var3) {
      this(var1, var2);
   }
}
