package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityEnderChestRenderer extends TileEntitySpecialRenderer {

   private static final ResourceLocation field_147520_b = new ResourceLocation("textures/entity/chest/ender.png");
   private ModelChest field_147521_c = new ModelChest();


   public void renderTileEntityAt(TileEntityEnderChest var1, double var2, double var4, double var6, float var8) {
      int var9 = 0;
      if(var1.hasWorldObj()) {
         var9 = var1.getBlockMetadata();
      }

      this.bindTexture(field_147520_b);
      GL11.glPushMatrix();
      GL11.glEnable('\u803a');
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef((float)var2, (float)var4 + 1.0F, (float)var6 + 1.0F);
      GL11.glScalef(1.0F, -1.0F, -1.0F);
      GL11.glTranslatef(0.5F, 0.5F, 0.5F);
      short var10 = 0;
      if(var9 == 2) {
         var10 = 180;
      }

      if(var9 == 3) {
         var10 = 0;
      }

      if(var9 == 4) {
         var10 = 90;
      }

      if(var9 == 5) {
         var10 = -90;
      }

      GL11.glRotatef((float)var10, 0.0F, 1.0F, 0.0F);
      GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
      float var11 = var1.field_145975_i + (var1.field_145972_a - var1.field_145975_i) * var8;
      var11 = 1.0F - var11;
      var11 = 1.0F - var11 * var11 * var11;
      this.field_147521_c.chestLid.rotateAngleX = -(var11 * 3.1415927F / 2.0F);
      this.field_147521_c.renderAll();
      GL11.glDisable('\u803a');
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

}
