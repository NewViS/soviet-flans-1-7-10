package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class GuiButtonLanguage extends GuiButton {

   public GuiButtonLanguage(int var1, int var2, int var3) {
      super(var1, var2, var3, 20, 20, "");
   }

   public void drawButton(Minecraft var1, int var2, int var3) {
      if(super.visible) {
         var1.getTextureManager().bindTexture(GuiButton.buttonTextures);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         boolean var4 = var2 >= super.xPosition && var3 >= super.yPosition && var2 < super.xPosition + super.width && var3 < super.yPosition + super.height;
         int var5 = 106;
         if(var4) {
            var5 += super.height;
         }

         this.drawTexturedModalRect(super.xPosition, super.yPosition, 0, var5, super.width, super.height);
      }
   }
}
