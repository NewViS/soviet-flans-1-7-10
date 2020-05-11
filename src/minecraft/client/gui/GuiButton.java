package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiButton extends Gui {

   protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
   protected int width;
   protected int height;
   public int xPosition;
   public int yPosition;
   public String displayString;
   public int id;
   public boolean enabled;
   public boolean visible;
   protected boolean field_146123_n;


   public GuiButton(int var1, int var2, int var3, String var4) {
      this(var1, var2, var3, 200, 20, var4);
   }

   public GuiButton(int var1, int var2, int var3, int var4, int var5, String var6) {
      this.width = 200;
      this.height = 20;
      this.enabled = true;
      this.visible = true;
      this.id = var1;
      this.xPosition = var2;
      this.yPosition = var3;
      this.width = var4;
      this.height = var5;
      this.displayString = var6;
   }

   public int getHoverState(boolean var1) {
      byte var2 = 1;
      if(!this.enabled) {
         var2 = 0;
      } else if(var1) {
         var2 = 2;
      }

      return var2;
   }

   public void drawButton(Minecraft var1, int var2, int var3) {
      if(this.visible) {
         FontRenderer var4 = var1.fontRenderer;
         var1.getTextureManager().bindTexture(buttonTextures);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = var2 >= this.xPosition && var3 >= this.yPosition && var2 < this.xPosition + this.width && var3 < this.yPosition + this.height;
         int var5 = this.getHoverState(this.field_146123_n);
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glBlendFunc(770, 771);
         this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + var5 * 20, this.width / 2, this.height);
         this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + var5 * 20, this.width / 2, this.height);
         this.mouseDragged(var1, var2, var3);
         int var6 = 14737632;
         if(!this.enabled) {
            var6 = 10526880;
         } else if(this.field_146123_n) {
            var6 = 16777120;
         }

         this.drawCenteredString(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, var6);
      }
   }

   protected void mouseDragged(Minecraft var1, int var2, int var3) {}

   public void mouseReleased(int var1, int var2) {}

   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      return this.enabled && this.visible && var2 >= this.xPosition && var3 >= this.yPosition && var2 < this.xPosition + this.width && var3 < this.yPosition + this.height;
   }

   public boolean func_146115_a() {
      return this.field_146123_n;
   }

   public void func_146111_b(int var1, int var2) {}

   public void func_146113_a(SoundHandler var1) {
      var1.playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
   }

   public int getButtonWidth() {
      return this.width;
   }

   public int func_154310_c() {
      return this.height;
   }

}
