package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBeacon$Button;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;

class GuiBeacon$PowerButton extends GuiBeacon$Button {

   private final int field_146149_p;
   private final int field_146148_q;
   // $FF: synthetic field
   final GuiBeacon field_146150_o;


   public GuiBeacon$PowerButton(GuiBeacon var1, int var2, int var3, int var4, int var5, int var6) {
      super(var2, var3, var4, GuiContainer.field_147001_a, 0 + Potion.potionTypes[var5].getStatusIconIndex() % 8 * 18, 198 + Potion.potionTypes[var5].getStatusIconIndex() / 8 * 18);
      this.field_146150_o = var1;
      this.field_146149_p = var5;
      this.field_146148_q = var6;
   }

   public void func_146111_b(int var1, int var2) {
      String var3 = I18n.format(Potion.potionTypes[this.field_146149_p].getName(), new Object[0]);
      if(this.field_146148_q >= 3 && this.field_146149_p != Potion.regeneration.id) {
         var3 = var3 + " II";
      }

      GuiBeacon.access$100(this.field_146150_o, var3, var1, var2);
   }
}
