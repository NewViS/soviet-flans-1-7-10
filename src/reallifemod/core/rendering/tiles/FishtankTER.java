package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.FishtankTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class FishtankTER extends TileEntitySpecialRenderer {

   private IModelCustom model;
   private RenderManager manager;


   public FishtankTER() {
      this.manager = RenderManager.instance;
      this.model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/fishtank.obj"));
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((FishtankTE)te, x, y, z, scale);
   }

   public void doRender(FishtankTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2896);
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y + 0.001F, (float)z + 0.5F);
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/tiles/fishtank.png"));
      this.model.renderAll();
      GL11.glPopMatrix();
      if(te.isFilled) {
         GL11.glEnable(3042);
         GL11.glColorMaterial(1032, 4608);
         GL11.glBlendFunc(770, 771);
         GL11.glDisable(2884);
         GL11.glEnable(3008);
         IIcon iicon = Blocks.water.getIcon(0, 1000);
         IIcon iicon1 = Blocks.water.getIcon(1, 1000);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
         float f1 = 0.5F;
         Tessellator tessellator = Tessellator.instance;
         float f2 = 0.5F;
         float f3 = 0.0F;
         float f4 = 0.1F / f1;
         float f5 = 0.0F;
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         float f6 = 0.0F;
         int i = 0;
         tessellator.startDrawingQuads();

         while(f4 > 0.0F) {
            IIcon iicon2 = i % 2 == 0?iicon:iicon1;
            this.func_147499_a(TextureMap.locationBlocksTexture);
            float f7 = iicon2.getMinU();
            float f8 = iicon2.getMinV();
            float f9 = iicon2.getMaxU();
            float f10 = iicon2.getMaxV();
            if(i / 2 % 2 == 0) {
               float f11 = f9;
               f9 = f7;
               f7 = f11;
            }

            if(te.func_145832_p() == 1 || te.func_145832_p() == 3) {
               tessellator.setColorOpaque(140, 140, 140);
               tessellator.addVertexWithUV(-0.5D, 0.8D, -1.0D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(0.5D, 0.8D, -1.0D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(0.5D, 0.0D, -1.0D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(-0.5D, 0.0D, -1.0D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(-0.5D, 0.8D, 1.0D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(0.5D, 0.8D, 1.0D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(0.5D, 0.0D, 1.0D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(-0.5D, 0.0D, 1.0D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(0.5D, 0.8D, -1.0D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(0.5D, 0.8D, 1.0D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(0.5D, 0.0D, 1.0D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(0.5D, 0.0D, -1.0D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(-0.5D, 0.8D, -1.0D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(-0.5D, 0.8D, 1.0D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(-0.5D, 0.0D, 1.0D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(-0.5D, 0.0D, -1.0D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(-0.5D, 0.8D, -1.0D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(-0.5D, 0.8D, 1.0D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(0.5D, 0.0D, 1.0D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(0.5D, 0.0D, -1.0D, (double)f9, (double)f8);
            }

            if(te.func_145832_p() == 0 || te.func_145832_p() == 2) {
               tessellator.setColorOpaque(140, 140, 140);
               tessellator.addVertexWithUV(-1.0D, 0.8D, -0.5D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(-1.0D, 0.8D, 0.5D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(-1.0D, 0.0D, 0.5D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(-1.0D, 0.0D, -0.5D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(1.0D, 0.8D, -0.5D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.8D, 0.5D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.0D, 0.5D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(1.0D, 0.0D, -0.5D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(-1.0D, 0.8D, 0.5D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.8D, 0.5D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.0D, 0.5D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(-1.0D, 0.0D, 0.5D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(-1.0D, 0.8D, -0.5D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.8D, -0.5D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.0D, -0.5D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(-1.0D, 0.0D, -0.5D, (double)f9, (double)f8);
               tessellator.addVertexWithUV(-1.0D, 0.8D, -0.5D, (double)f9, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.8D, -0.5D, (double)f7, (double)f10);
               tessellator.addVertexWithUV(1.0D, 0.8D, 0.5D, (double)f7, (double)f8);
               tessellator.addVertexWithUV(-1.0D, 0.8D, 0.5D, (double)f9, (double)f8);
            }

            f4 -= 0.45F;
            f5 -= 0.45F;
            f2 *= 0.9F;
            f6 += 0.03F;
            ++i;
         }

         tessellator.draw();
         GL11.glPopMatrix();
      }

      GL11.glEnable(2896);
      GL11.glPopMatrix();
   }
}
