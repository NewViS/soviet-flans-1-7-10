package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

class GuiScreenOptionsSounds$Button extends GuiButton {

   private final SoundCategory field_146153_r;
   private final String field_146152_s;
   public float field_146156_o;
   public boolean field_146155_p;
   // $FF: synthetic field
   final GuiScreenOptionsSounds field_146154_q;


   public GuiScreenOptionsSounds$Button(GuiScreenOptionsSounds var1, int var2, int var3, int var4, SoundCategory var5, boolean var6) {
      super(var2, var3, var4, var6?310:150, 20, "");
      this.field_146154_q = var1;
      this.field_146156_o = 1.0F;
      this.field_146153_r = var5;
      this.field_146152_s = I18n.format("soundCategory." + var5.getCategoryName(), new Object[0]);
      super.displayString = this.field_146152_s + ": " + var1.func_146504_a(var5);
      this.field_146156_o = GuiScreenOptionsSounds.access$000(var1).getSoundLevel(var5);
   }

   public int getHoverState(boolean var1) {
      return 0;
   }

   protected void mouseDragged(Minecraft var1, int var2, int var3) {
      if(super.visible) {
         if(this.field_146155_p) {
            this.field_146156_o = (float)(var2 - (super.xPosition + 4)) / (float)(super.width - 8);
            if(this.field_146156_o < 0.0F) {
               this.field_146156_o = 0.0F;
            }

            if(this.field_146156_o > 1.0F) {
               this.field_146156_o = 1.0F;
            }

            var1.gameSettings.setSoundLevel(this.field_146153_r, this.field_146156_o);
            var1.gameSettings.saveOptions();
            super.displayString = this.field_146152_s + ": " + this.field_146154_q.func_146504_a(this.field_146153_r);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(super.xPosition + (int)(this.field_146156_o * (float)(super.width - 8)), super.yPosition, 0, 66, 4, 20);
         this.drawTexturedModalRect(super.xPosition + (int)(this.field_146156_o * (float)(super.width - 8)) + 4, super.yPosition, 196, 66, 4, 20);
      }
   }

   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if(super.mousePressed(var1, var2, var3)) {
         this.field_146156_o = (float)(var2 - (super.xPosition + 4)) / (float)(super.width - 8);
         if(this.field_146156_o < 0.0F) {
            this.field_146156_o = 0.0F;
         }

         if(this.field_146156_o > 1.0F) {
            this.field_146156_o = 1.0F;
         }

         var1.gameSettings.setSoundLevel(this.field_146153_r, this.field_146156_o);
         var1.gameSettings.saveOptions();
         super.displayString = this.field_146152_s + ": " + this.field_146154_q.func_146504_a(this.field_146153_r);
         this.field_146155_p = true;
         return true;
      } else {
         return false;
      }
   }

   public void func_146113_a(SoundHandler var1) {}

   public void mouseReleased(int var1, int var2) {
      if(this.field_146155_p) {
         if(this.field_146153_r == SoundCategory.MASTER) {
            float var10000 = 1.0F;
         } else {
            GuiScreenOptionsSounds.access$000(this.field_146154_q).getSoundLevel(this.field_146153_r);
         }

         this.field_146154_q.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
      }

      this.field_146155_p = false;
   }
}
