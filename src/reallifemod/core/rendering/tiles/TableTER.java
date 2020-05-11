package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TableTE;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class TableTER extends TileEntitySpecialRenderer {

   private IModelCustom model;
   Tessellator tessellator;


   public TableTER() {
      this.tessellator = Tessellator.instance;
      this.model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/tableleg.obj"));
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((TableTE)te, x, y, z, scale);
   }

   public void doRender(TableTE tile, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glPushMatrix();
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawing(1);
      this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));

      for(float i = 0.0F; i < 360.0F; ++i) {
         tessellator.addVertexWithUV(Math.sin((double)i) * 3.141592653589793D / 180.0D * 25.0D, 0.5799999833106995D, Math.cos((double)i) * 3.141592653589793D / 180.0D * 22.5D, Math.sin((double)i) * 3.141592653589793D / 180.0D * 22.5D, Math.cos((double)i) * 3.141592653589793D / 180.0D * 22.5D);
      }

      tessellator.draw();
      tessellator.startDrawing(9);
      GL11.glDisable(2884);

      int var11;
      for(var11 = 0; var11 < TableTE.edges.size() - 1; ++var11) {
         tessellator.addVertexWithUV((double)((Vector3f)TableTE.edges.get(var11)).x, (double)((Vector3f)TableTE.edges.get(var11)).y, (double)((Vector3f)TableTE.edges.get(var11)).z, 0.0D, 0.0D);
      }

      tessellator.draw();
      GL11.glPopMatrix();
      GL11.glTranslated(x + 0.5D, y, z + 0.5D);
      GL11.glRotatef((float)(tile.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(tile.func_145832_p() * 90 == 180 || tile.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
      this.model.renderAll();
      this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
      tessellator.startDrawing(9);

      for(var11 = 0; var11 < TableTE.edges.size(); ++var11) {
         tessellator.addVertexWithUV((double)((Vector3f)TableTE.edges.get(var11)).x, (double)((Vector3f)TableTE.edges.get(var11)).y, (double)((Vector3f)TableTE.edges.get(var11)).z, 0.0D, 0.0D);
      }

      tessellator.draw();
      GL11.glPopMatrix();
   }

   public void drawTheLine(double x, double y, double z) {
      GL11.glPushMatrix();
      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
      this.tessellator.startDrawing(7);
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      this.tessellator.addVertexWithUV(x, y + 1.0D, z, x, z);
      this.tessellator.addVertexWithUV(x + 1.0D, y + 1.0D, z, x + 1.0D, z);
      this.tessellator.addVertexWithUV(0.5D, y + 1.0D, 0.0D, 1.0D, 1.0D);
      this.tessellator.addVertexWithUV(-0.5D, y + 1.0D, 0.0D, 0.0D, 1.0D);
      this.tessellator.addVertexWithUV(x, y + 0.9D, z, x, z);
      this.tessellator.addVertexWithUV(x + 1.0D, y + 0.9D, z, x + 1.0D, z);
      this.tessellator.addVertexWithUV(0.5D, y + 0.9D, 0.0D, 1.0D, 1.0D);
      this.tessellator.addVertexWithUV(-0.5D, y + 0.9D, 0.0D, 0.0D, 1.0D);
      this.tessellator.addVertexWithUV(x + 1.0D, y + 1.0D, z, x + 1.0D, y + 1.0D);
      this.tessellator.addVertexWithUV(0.5D, y + 1.0D, 0.0D, 0.0D, y + 1.0D);
      this.tessellator.addVertexWithUV(0.5D, y + 0.9D, 0.0D, x, y + 0.9D);
      this.tessellator.addVertexWithUV(x + 1.0D, y + 0.9D, z, 0.0D, 1.0D);
      this.tessellator.addVertexWithUV(x, y + 1.0D, z, x, y);
      this.tessellator.addVertexWithUV(-0.5D, y + 1.0D, 0.0D, x, 0.0D);
      this.tessellator.addVertexWithUV(-0.5D, y + 0.9D, 0.0D, x, 0.0D);
      this.tessellator.addVertexWithUV(x, y + 0.9D, z, x, y);
      this.tessellator.draw();
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
