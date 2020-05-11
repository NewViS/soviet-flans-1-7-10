package net.minecraft.client.gui;

import java.util.Iterator;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.MapItemRenderer$1;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapData$MapCoord;
import org.lwjgl.opengl.GL11;

class MapItemRenderer$Instance {

   private final MapData field_148242_b;
   private final DynamicTexture field_148243_c;
   private final ResourceLocation field_148240_d;
   private final int[] field_148241_e;
   // $FF: synthetic field
   final MapItemRenderer field_148244_a;


   private MapItemRenderer$Instance(MapItemRenderer var1, MapData var2) {
      this.field_148244_a = var1;
      this.field_148242_b = var2;
      this.field_148243_c = new DynamicTexture(128, 128);
      this.field_148241_e = this.field_148243_c.getTextureData();
      this.field_148240_d = MapItemRenderer.access$400(var1).getDynamicTextureLocation("map/" + var2.mapName, this.field_148243_c);

      for(int var3 = 0; var3 < this.field_148241_e.length; ++var3) {
         this.field_148241_e[var3] = 0;
      }

   }

   private void func_148236_a() {
      for(int var1 = 0; var1 < 16384; ++var1) {
         int var2 = this.field_148242_b.colors[var1] & 255;
         if(var2 / 4 == 0) {
            this.field_148241_e[var1] = (var1 + var1 / 128 & 1) * 8 + 16 << 24;
         } else {
            this.field_148241_e[var1] = MapColor.mapColorArray[var2 / 4].func_151643_b(var2 & 3);
         }
      }

      this.field_148243_c.updateDynamicTexture();
   }

   private void func_148237_a(boolean var1) {
      byte var2 = 0;
      byte var3 = 0;
      Tessellator var4 = Tessellator.instance;
      float var5 = 0.0F;
      MapItemRenderer.access$400(this.field_148244_a).bindTexture(this.field_148240_d);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(1, 771, 0, 1);
      GL11.glDisable(3008);
      var4.startDrawingQuads();
      var4.addVertexWithUV((double)((float)(var2 + 0) + var5), (double)((float)(var3 + 128) - var5), -0.009999999776482582D, 0.0D, 1.0D);
      var4.addVertexWithUV((double)((float)(var2 + 128) - var5), (double)((float)(var3 + 128) - var5), -0.009999999776482582D, 1.0D, 1.0D);
      var4.addVertexWithUV((double)((float)(var2 + 128) - var5), (double)((float)(var3 + 0) + var5), -0.009999999776482582D, 1.0D, 0.0D);
      var4.addVertexWithUV((double)((float)(var2 + 0) + var5), (double)((float)(var3 + 0) + var5), -0.009999999776482582D, 0.0D, 0.0D);
      var4.draw();
      GL11.glEnable(3008);
      GL11.glDisable(3042);
      MapItemRenderer.access$400(this.field_148244_a).bindTexture(MapItemRenderer.access$500());
      int var6 = 0;
      Iterator var7 = this.field_148242_b.playersVisibleOnMap.values().iterator();

      while(var7.hasNext()) {
         MapData$MapCoord var8 = (MapData$MapCoord)var7.next();
         if(!var1 || var8.iconSize == 1) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)var2 + (float)var8.centerX / 2.0F + 64.0F, (float)var3 + (float)var8.centerZ / 2.0F + 64.0F, -0.02F);
            GL11.glRotatef((float)(var8.iconRotation * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(4.0F, 4.0F, 3.0F);
            GL11.glTranslatef(-0.125F, 0.125F, 0.0F);
            float var9 = (float)(var8.iconSize % 4 + 0) / 4.0F;
            float var10 = (float)(var8.iconSize / 4 + 0) / 4.0F;
            float var11 = (float)(var8.iconSize % 4 + 1) / 4.0F;
            float var12 = (float)(var8.iconSize / 4 + 1) / 4.0F;
            var4.startDrawingQuads();
            var4.addVertexWithUV(-1.0D, 1.0D, (double)((float)var6 * 0.001F), (double)var9, (double)var10);
            var4.addVertexWithUV(1.0D, 1.0D, (double)((float)var6 * 0.001F), (double)var11, (double)var10);
            var4.addVertexWithUV(1.0D, -1.0D, (double)((float)var6 * 0.001F), (double)var11, (double)var12);
            var4.addVertexWithUV(-1.0D, -1.0D, (double)((float)var6 * 0.001F), (double)var9, (double)var12);
            var4.draw();
            GL11.glPopMatrix();
            ++var6;
         }
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.0F, -0.04F);
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   // $FF: synthetic method
   static void access$000(MapItemRenderer$Instance var0) {
      var0.func_148236_a();
   }

   // $FF: synthetic method
   static void access$100(MapItemRenderer$Instance var0, boolean var1) {
      var0.func_148237_a(var1);
   }

   // $FF: synthetic method
   MapItemRenderer$Instance(MapItemRenderer var1, MapData var2, MapItemRenderer$1 var3) {
      this(var1, var2);
   }

   // $FF: synthetic method
   static ResourceLocation access$300(MapItemRenderer$Instance var0) {
      return var0.field_148240_d;
   }
}
