package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreenBook;
import org.lwjgl.opengl.GL11;

class GuiScreenBook$NextPageButton extends GuiButton {

   private final boolean field_146151_o;


   public GuiScreenBook$NextPageButton(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, 23, 13, "");
      this.field_146151_o = var4;
   }

   public void drawButton(Minecraft var1, int var2, int var3) {
      if(super.visible) {
         boolean var4 = var2 >= super.xPosition && var3 >= super.yPosition && var2 < super.xPosition + super.width && var3 < super.yPosition + super.height;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         var1.getTextureManager().bindTexture(GuiScreenBook.access$000());
         int var5 = 0;
         int var6 = 192;
         if(var4) {
            var5 += 23;
         }

         if(!this.field_146151_o) {
            var6 += 13;
         }

         this.drawTexturedModalRect(super.xPosition, super.yPosition, var5, var6, 23, 13);
      }
   }
}
