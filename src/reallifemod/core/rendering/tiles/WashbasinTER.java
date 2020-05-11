package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.models.ModelWashingBasin;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class WashbasinTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/Bathroom/blocks/Basin.obj"));
   private ModelWashingBasin modelMC = new ModelWashingBasin();
   private ResourceLocation texture = new ResourceLocation("reallifemod:textures/tiles/Washbasin.png");


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glPushMatrix();
      if(!RealLifeModConfig.minecraftstyle) {
         this.func_147499_a(this.texture);
         GL11.glTranslated(x + 0.5D, y + 0.4D, z + 0.5D);
         GL11.glRotatef((float)(te.getBlockMetadata() * 90), 0.0F, 1.0F, 0.0F);
         if(te.getBlockMetadata() * 90 == 180 || te.getBlockMetadata() * 90 == 0) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         }

         this.model.renderPart("basin");
         GL11.glTranslated(0.0D, 0.085D, 0.125D);
         GL11.glRotated(-10.0D, 1.0D, 0.0D, 0.0D);
         this.model.renderPart("handle");
      } else {
         GL11.glTranslatef((float)x + 0.5F, (float)y + 1.4F, (float)z + 0.5F);
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef((float)(te.getBlockMetadata() * 90), 0.0F, 1.0F, 0.0F);
         this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/WashingBasin_MC.png"));
         this.modelMC.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      }

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
