package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings$Options;
import org.lwjgl.opengl.GL11;

public class GuiOptionSlider extends GuiButton {

   private float field_146134_p;
   public boolean field_146135_o;
   private GameSettings$Options field_146133_q;
   private final float field_146132_r;
   private final float field_146131_s;


   public GuiOptionSlider(int var1, int var2, int var3, GameSettings$Options var4) {
      this(var1, var2, var3, var4, 0.0F, 1.0F);
   }

   public GuiOptionSlider(int var1, int var2, int var3, GameSettings$Options var4, float var5, float var6) {
      super(var1, var2, var3, 150, 20, "");
      this.field_146134_p = 1.0F;
      this.field_146133_q = var4;
      this.field_146132_r = var5;
      this.field_146131_s = var6;
      Minecraft var7 = Minecraft.getMinecraft();
      this.field_146134_p = var4.normalizeValue(var7.gameSettings.getOptionFloatValue(var4));
      super.displayString = var7.gameSettings.getKeyBinding(var4);
   }

   public int getHoverState(boolean var1) {
      return 0;
   }

   protected void mouseDragged(Minecraft var1, int var2, int var3) {
      if(super.visible) {
         if(this.field_146135_o) {
            this.field_146134_p = (float)(var2 - (super.xPosition + 4)) / (float)(super.width - 8);
            if(this.field_146134_p < 0.0F) {
               this.field_146134_p = 0.0F;
            }

            if(this.field_146134_p > 1.0F) {
               this.field_146134_p = 1.0F;
            }

            float var4 = this.field_146133_q.denormalizeValue(this.field_146134_p);
            var1.gameSettings.setOptionFloatValue(this.field_146133_q, var4);
            this.field_146134_p = this.field_146133_q.normalizeValue(var4);
            super.displayString = var1.gameSettings.getKeyBinding(this.field_146133_q);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(super.xPosition + (int)(this.field_146134_p * (float)(super.width - 8)), super.yPosition, 0, 66, 4, 20);
         this.drawTexturedModalRect(super.xPosition + (int)(this.field_146134_p * (float)(super.width - 8)) + 4, super.yPosition, 196, 66, 4, 20);
      }
   }

   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if(super.mousePressed(var1, var2, var3)) {
         this.field_146134_p = (float)(var2 - (super.xPosition + 4)) / (float)(super.width - 8);
         if(this.field_146134_p < 0.0F) {
            this.field_146134_p = 0.0F;
         }

         if(this.field_146134_p > 1.0F) {
            this.field_146134_p = 1.0F;
         }

         var1.gameSettings.setOptionFloatValue(this.field_146133_q, this.field_146133_q.denormalizeValue(this.field_146134_p));
         super.displayString = var1.gameSettings.getKeyBinding(this.field_146133_q);
         this.field_146135_o = true;
         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int var1, int var2) {
      this.field_146135_o = false;
   }
}
