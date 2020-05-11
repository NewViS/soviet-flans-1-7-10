package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.BabybedTE;
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

public class BabybedTER extends TileEntitySpecialRenderer {

   private IModelCustom modelHD = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/babybed.obj"));
   private final ResourceLocation texture = new ResourceLocation("reallifemod:textures/tiles/BabyBed.png");


   private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      GL11.glPushMatrix();
      GL11.glRotatef((float)(meta * -90), 0.0F, 0.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      BabybedTE tile = (BabybedTE)te;
      GL11.glPushMatrix();
      GL11.glDisable(2896);
      GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
      GL11.glRotatef((float)(te.getBlockMetadata() * 90), 0.0F, 1.0F, 0.0F);
      GL11.glPushMatrix();
      GL11.glScaled(5.0D, 5.0D, 5.0D);
      this.func_147499_a(this.texture);
      this.modelHD.renderAll();
      GL11.glEnable(2896);
      GL11.glPopMatrix();
      if(tile.lyingbaby != null) {
         GL11.glTranslated(0.5D, 0.25D, 0.0D);
         GL11.glRotated((double)(-tile.lyingbaby.field_70177_z), 1.0D, 0.0D, 0.0D);
         GL11.glRotated(90.0D, 0.0D, 0.0D, 1.0D);
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
