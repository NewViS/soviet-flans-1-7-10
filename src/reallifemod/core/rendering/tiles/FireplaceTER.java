package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.FireplaceTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class FireplaceTER extends TileEntitySpecialRenderer {

   private IModelCustom model;
   private RenderManager manager;


   public FireplaceTER() {
      this.manager = RenderManager.instance;
      this.model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/fireplace.obj"));
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((FireplaceTE)te, x, y, z, scale);
   }

   public void doRender(FireplaceTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      this.renderCookedItem(te);
      if(te.burning) {
         GL11.glDisable(2896);
         IIcon iicon = Blocks.fire.getFireIcon(0);
         IIcon iicon1 = Blocks.fire.getFireIcon(1);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
         float f1 = 0.5F;
         GL11.glScalef(f1, f1, f1);
         Tessellator tessellator = Tessellator.instance;
         float f2 = 0.5F;
         float f3 = 0.0F;
         float f4 = 0.1F / f1;
         float f5 = 0.0F;
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         GL11.glRotatef(-this.manager.playerViewY, 0.0F, 1.0F, 0.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
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

            tessellator.addVertexWithUV((double)(f2 - f3), (double)(0.0F - f5), (double)f6, (double)f9, (double)f10);
            tessellator.addVertexWithUV((double)(-f2 - f3), (double)(0.0F - f5), (double)f6, (double)f7, (double)f10);
            tessellator.addVertexWithUV((double)(-f2 - f3), (double)(1.4F - f5), (double)f6, (double)f7, (double)f8);
            tessellator.addVertexWithUV((double)(f2 - f3), (double)(1.4F - f5), (double)f6, (double)f9, (double)f8);
            f4 -= 0.45F;
            f5 -= 0.45F;
            f2 *= 0.9F;
            f6 += 0.03F;
            ++i;
         }

         tessellator.draw();
         GL11.glEnable(2896);
         GL11.glPopMatrix();
      }

      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslated((double)((float)x + 0.5F), (double)((float)y) - te.getHeightToSubstract(), (double)((float)z + 0.5F));
      GL11.glScalef(0.2F, 0.2F, 0.2F);
      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/fireplace.png"));
      this.model.renderAll();
      GL11.glEnable(2884);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   private void renderCookedItem(FireplaceTE te) {
      GL11.glPushMatrix();
      GL11.glPopMatrix();
   }

   private void adjustLightFixture(World world, int i, int j, int k, Block block) {
      Tessellator tess = Tessellator.instance;
      float brightness = (float)block.getMixedBrightnessForBlock(world, i, j, k);
      int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
      int modulousModifier = skyLight % 65536;
      int divModifier = skyLight / 65536;
      tess.setColorOpaque_F(brightness, brightness, brightness);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)modulousModifier, (float)divModifier);
   }
}
