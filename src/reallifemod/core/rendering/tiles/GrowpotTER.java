package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.GrowpotTE;
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

public class GrowpotTER extends TileEntitySpecialRenderer {

   private IModelCustom model;
   private Tessellator tesselator;


   public GrowpotTER() {
      this.tesselator = Tessellator.instance;
      this.model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/growpot.obj"));
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y + 0.01F, (float)z + 0.5F);
      GL11.glRotatef((float)(te.getBlockMetadata() * 90), 0.0F, 1.0F, 0.0F);
      if(te instanceof GrowpotTE && ((GrowpotTE)te).isFilled) {
         GL11.glPushMatrix();
         GL11.glEnable(2884);
         GL11.glDisable(3008);
         this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/dirt.png"));
         this.tesselator.startDrawing(9);

         for(float i = 0.0F; i < 360.0F; ++i) {
            this.tesselator.addVertexWithUV(Math.sin((double)i) * 3.141592653589793D / 180.0D * 22.5D, 0.5799999833106995D, Math.cos((double)i) * 3.141592653589793D / 180.0D * 22.5D, Math.sin((double)i) * 3.141592653589793D / 180.0D * 22.5D, Math.cos((double)i) * 3.141592653589793D / 180.0D * 22.5D);
         }

         this.tesselator.draw();
         GL11.glPopMatrix();
      }

      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/growpot.png"));
      this.model.renderAll();
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
