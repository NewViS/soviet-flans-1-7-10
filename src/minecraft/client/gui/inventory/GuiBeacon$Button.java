package net.minecraft.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

class GuiBeacon$Button extends GuiButton {

   private final ResourceLocation field_146145_o;
   private final int field_146144_p;
   private final int field_146143_q;
   private boolean field_146142_r;


   protected GuiBeacon$Button(int var1, int var2, int var3, ResourceLocation var4, int var5, int var6) {
      super(var1, var2, var3, 22, 22, "");
      this.field_146145_o = var4;
      this.field_146144_p = var5;
      this.field_146143_q = var6;
   }

   public void drawButton(Minecraft var1, int var2, int var3) {
      if(super.visible) {
         var1.getTextureManager().bindTexture(GuiBeacon.access$000());
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         super.field_146123_n = var2 >= super.xPosition && var3 >= super.yPosition && var2 < super.xPosition + super.width && var3 < super.yPosition + super.height;
         short var4 = 219;
         int var5 = 0;
         if(!super.enabled) {
            var5 += super.width * 2;
         } else if(this.field_146142_r) {
            var5 += super.width * 1;
         } else if(super.field_146123_n) {
            var5 += super.width * 3;
         }

         this.drawTexturedModalRect(super.xPosition, super.yPosition, var5, var4, super.width, super.height);
         if(!GuiBeacon.access$000().equals(this.field_146145_o)) {
            var1.getTextureManager().bindTexture(this.field_146145_o);
         }

         this.drawTexturedModalRect(super.xPosition + 2, super.yPosition + 2, this.field_146144_p, this.field_146143_q, 18, 18);
      }
   }

   public boolean func_146141_c() {
      return this.field_146142_r;
   }

   public void func_146140_b(boolean var1) {
      this.field_146142_r = var1;
   }
}
