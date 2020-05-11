package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.bankBlock;
import de.ItsAMysterious.mods.reallifemod.core.tiles.FridgeTE;
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

public class FridgeTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/fridge.obj"));
   private bankBlock theblock;


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((FridgeTE)te, x, y, z, scale);
   }

   public void doRender(FridgeTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslated((double)((float)x) + 0.5D, (double)((float)y), (double)((float)z) + 0.5D);
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/fridge.png"));
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
