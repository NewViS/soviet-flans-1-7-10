package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMerchant;
import org.lwjgl.opengl.GL11;

class GuiMerchant$MerchantButton extends GuiButton {

   private final boolean field_146157_o;


   public GuiMerchant$MerchantButton(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, 12, 19, "");
      this.field_146157_o = var4;
   }

   public void drawButton(Minecraft var1, int var2, int var3) {
      if(super.visible) {
         var1.getTextureManager().bindTexture(GuiMerchant.access$000());
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         boolean var4 = var2 >= super.xPosition && var3 >= super.yPosition && var2 < super.xPosition + super.width && var3 < super.yPosition + super.height;
         int var5 = 0;
         int var6 = 176;
         if(!super.enabled) {
            var6 += super.width * 2;
         } else if(var4) {
            var6 += super.width;
         }

         if(!this.field_146157_o) {
            var5 += super.height;
         }

         this.drawTexturedModalRect(super.xPosition, super.yPosition, var6, var5, super.width, super.height);
      }
   }
}
