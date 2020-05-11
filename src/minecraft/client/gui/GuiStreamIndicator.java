package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiStreamIndicator {

   private static final ResourceLocation field_152441_a = new ResourceLocation("textures/gui/stream_indicator.png");
   private final Minecraft field_152442_b;
   private float field_152443_c = 1.0F;
   private int field_152444_d = 1;


   public GuiStreamIndicator(Minecraft var1) {
      this.field_152442_b = var1;
   }

   public void func_152437_a(int var1, int var2) {
      if(this.field_152442_b.func_152346_Z().func_152934_n()) {
         GL11.glEnable(3042);
         int var3 = this.field_152442_b.func_152346_Z().func_152920_A();
         if(var3 > 0) {
            String var4 = "" + var3;
            int var5 = this.field_152442_b.fontRenderer.getStringWidth(var4);
            boolean var6 = true;
            int var7 = var1 - var5 - 1;
            int var8 = var2 + 20 - 1;
            int var10 = var2 + 20 + this.field_152442_b.fontRenderer.FONT_HEIGHT - 1;
            GL11.glDisable(3553);
            Tessellator var11 = Tessellator.instance;
            GL11.glColor4f(0.0F, 0.0F, 0.0F, (0.65F + 0.35000002F * this.field_152443_c) / 2.0F);
            var11.startDrawingQuads();
            var11.addVertex((double)var7, (double)var10, 0.0D);
            var11.addVertex((double)var1, (double)var10, 0.0D);
            var11.addVertex((double)var1, (double)var8, 0.0D);
            var11.addVertex((double)var7, (double)var8, 0.0D);
            var11.draw();
            GL11.glEnable(3553);
            this.field_152442_b.fontRenderer.drawString(var4, var1 - var5, var2 + 20, 16777215);
         }

         this.func_152436_a(var1, var2, this.func_152440_b(), 0);
         this.func_152436_a(var1, var2, this.func_152438_c(), 17);
      }

   }

   private void func_152436_a(int var1, int var2, int var3, int var4) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F + 0.35000002F * this.field_152443_c);
      this.field_152442_b.getTextureManager().bindTexture(field_152441_a);
      float var5 = 150.0F;
      float var6 = 0.0F;
      float var7 = (float)var3 * 0.015625F;
      float var8 = 1.0F;
      float var9 = (float)(var3 + 16) * 0.015625F;
      Tessellator var10 = Tessellator.instance;
      var10.startDrawingQuads();
      var10.addVertexWithUV((double)(var1 - 16 - var4), (double)(var2 + 16), (double)var5, (double)var6, (double)var9);
      var10.addVertexWithUV((double)(var1 - var4), (double)(var2 + 16), (double)var5, (double)var8, (double)var9);
      var10.addVertexWithUV((double)(var1 - var4), (double)(var2 + 0), (double)var5, (double)var8, (double)var7);
      var10.addVertexWithUV((double)(var1 - 16 - var4), (double)(var2 + 0), (double)var5, (double)var6, (double)var7);
      var10.draw();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private int func_152440_b() {
      return this.field_152442_b.func_152346_Z().func_152919_o()?16:0;
   }

   private int func_152438_c() {
      return this.field_152442_b.func_152346_Z().func_152929_G()?48:32;
   }

   public void func_152439_a() {
      if(this.field_152442_b.func_152346_Z().func_152934_n()) {
         this.field_152443_c += 0.025F * (float)this.field_152444_d;
         if(this.field_152443_c < 0.0F) {
            this.field_152444_d *= -1;
            this.field_152443_c = 0.0F;
         } else if(this.field_152443_c > 1.0F) {
            this.field_152444_d *= -1;
            this.field_152443_c = 1.0F;
         }
      } else {
         this.field_152443_c = 1.0F;
         this.field_152444_d = 1;
      }

   }

}
