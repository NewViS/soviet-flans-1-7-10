package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.models.ModelPissoir;
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

public class UrinalTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/Bathroom/blocks/Pissoir.obj"));
   private ModelPissoir modelMC = new ModelPissoir();


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      if(RealLifeModConfig.minecraftstyle) {
         GL11.glTranslatef(0.0F, 1.4F, 0.0F);
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef((float)(te.getBlockMetadata() * 90), 0.0F, 1.0F, 0.0F);
         this.func_147499_a(new ResourceLocation("reallifemod:textures/blocks/marmor.png"));
         this.modelMC.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      } else if(!RealLifeModConfig.minecraftstyle) {
         GL11.glRotatef((float)(te.getBlockMetadata() * 90), 0.0F, 1.0F, 0.0F);
         if(te.getBlockMetadata() * 90 == 180 || te.getBlockMetadata() * 90 == 0) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         }

         this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/pissoir.png"));
         this.model.renderAll();
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
