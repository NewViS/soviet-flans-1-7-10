package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.PillarTE;
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

public class PillarTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/pillar.obj"));


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((PillarTE)te, x, y, z, scale);
   }

   public void doRender(PillarTE te, double x, double y, double z, float scale) {
      float s = te.scale;
      GL11.glPushMatrix();
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      GL11.glScalef(s, 1.0F, s);
      switch(null.$SwitchMap$de$ItsAMysterious$mods$reallifemod$core$blocks$outdoor$pillarBlock$pillarType[te.type.ordinal()]) {
      case 1:
         this.func_147499_a(new ResourceLocation("reallifemod:textures/blocks/Marmor.png"));
         break;
      case 2:
         this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/brick.png"));
         break;
      case 3:
         this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/iron_block.png"));
      }

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