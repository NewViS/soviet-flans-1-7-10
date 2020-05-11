package de.ItsAMysterious.mods.reallifemod.api.gui;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;

public class GuiDropDownButton extends GuiButtonExt {

   public List entries = new ArrayList();
   public boolean isDropped = false;


   public GuiDropDownButton(int id, int xPos, int yPos, String displayString) {
      super(id, xPos, yPos, displayString);
   }

   public GuiDropDownButton(int id, int xPos, int yPos, int width, int height, String displayString) {
      super(id, xPos, yPos, width, height, displayString);
   }

   public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
      if(this.field_146125_m) {
         this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
         int k = this.func_146114_a(this.field_146123_n);
         GuiUtils.drawContinuousTexturedBox(field_146122_a, this.field_146128_h, this.field_146129_i, 0, 46 + k * 20, this.field_146120_f, this.field_146121_g, 200, 20, 2, 3, 2, 2, this.field_73735_i);
         this.func_146119_b(mc, mouseX, mouseY);
         int color = 14737632;
         if(this.packedFGColour != 0) {
            color = this.packedFGColour;
         } else if(!this.field_146124_l) {
            color = 10526880;
         } else if(this.field_146123_n) {
            color = 16777120;
         }

         String buttonText = this.field_146126_j;
         int strWidth = mc.fontRenderer.getStringWidth(buttonText);
         int ellipsisWidth = mc.fontRenderer.getStringWidth("...");
         if(strWidth > this.field_146120_f - 6 && strWidth > ellipsisWidth) {
            buttonText = mc.fontRenderer.trimStringToWidth(buttonText, this.field_146120_f - 6 - ellipsisWidth).trim() + "...";
         }

         this.func_73732_a(mc.fontRenderer, buttonText, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, color);
      }

   }

   public boolean func_146116_c(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
      this.isDropped = !this.isDropped;
      return this.field_146124_l && this.field_146125_m && p_146116_2_ >= this.field_146128_h && p_146116_3_ >= this.field_146129_i && p_146116_2_ < this.field_146128_h + this.field_146120_f && p_146116_3_ < this.field_146129_i + this.field_146121_g;
   }
}
