package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.models.ModelShield;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BilboardTE;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class BilboardTER extends TileEntitySpecialRenderer {

   private final ModelShield model = new ModelShield();


   private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      GL11.glPushMatrix();
      GL11.glRotatef((float)(meta * -90), 0.0F, 0.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public void renderTileEntityAt(BilboardTE te, double x, double y, double z, float scale) {
      Tessellator tessellator = Tessellator.instance;
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      GL11.glPushMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/general/Iron.png"));
      this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      GL11.glPopMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture(te.image());
      tessellator.startDrawing(7);
      tessellator.addVertexWithUV(-1.3D, -0.85D, 0.0D, 0.0D, 0.0D);
      tessellator.addVertexWithUV(1.3D, -0.85D, 0.0D, 1.0D, 0.0D);
      tessellator.addVertexWithUV(1.3D, 1.15D, 0.0D, 1.0D, 1.0D);
      tessellator.addVertexWithUV(-1.3D, 1.15D, 0.0D, 0.0D, 1.0D);
      tessellator.draw();
      GL11.glPushMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/blocks/planks_oak.png"));
      tessellator.startDrawing(7);
      tessellator.addVertexWithUV(-1.3D, -0.85D, 0.01D, 0.0D, 0.0D);
      tessellator.addVertexWithUV(1.3D, -0.85D, 0.01D, 1.0D, 0.0D);
      tessellator.addVertexWithUV(1.3D, 1.15D, 0.01D, 1.0D, 1.0D);
      tessellator.addVertexWithUV(-1.3D, 1.15D, 0.01D, 0.0D, 1.0D);
      tessellator.draw();
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

   public void func_147500_a(TileEntity p_147500_1_, double x, double y, double z, float s) {
      this.renderTileEntityAt((BilboardTE)p_147500_1_, x, y, z, s);
   }
}
