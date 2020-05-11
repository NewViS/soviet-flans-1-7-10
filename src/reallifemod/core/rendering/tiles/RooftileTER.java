package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.RooftileTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RooftileTER extends TileEntitySpecialRenderer {

   private IModelCustom model1 = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/rooftile1.obj"));
   private IModelCustom model2 = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/rooftile2.obj"));
   private IModelCustom model3 = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/rooftile3.obj"));
   private ResourceLocation textures = new ResourceLocation("reallifemod:textures/tiles/rooftile.png");


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((RooftileTE)te, x, y, z, scale);
   }

   public void doRender(RooftileTE tile, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      GL11.glRotatef((float)(tile.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(tile.func_145832_p() * 90 == 180 || tile.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(this.textures);
      if(tile.type >= 0) {
         switch(tile.type) {
         case 0:
            this.model1.renderAll();
            break;
         case 1:
            this.model2.renderAll();
            break;
         case 2:
            this.model3.renderAll();
         }
      }

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
