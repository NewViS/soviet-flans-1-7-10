package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

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

public class MirrorTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/Bathroom/blocks/Mirror.obj"));


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      Tessellator t = Tessellator.instance;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)x, (float)y, (float)z);
      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/Mirror.png"));
      GL11.glRotatef((float)(te.getBlockMetadata() * 90), 0.0F, 1.0F, 0.0F);
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      this.model.renderAll();
      GL11.glPushMatrix();
      GL11.glEnable(2928);
      GL11.glDepthRange(0.0D, -30.0D);
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glEnable(7);
      GL11.glVertex2f(0.0F, 0.0F);
      GL11.glVertex2f(10.0F, 0.0F);
      GL11.glVertex2f(10.0F, 10.0F);
      GL11.glVertex2f(0.0F, 10.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
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
