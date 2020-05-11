package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.models.ModelChristmasPyramid;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChristmaspyramidTE;
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

public class ChristmaspyramidTER extends TileEntitySpecialRenderer {

   private IModelCustom stand = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/seasonal/christmaspyramid_stand.obj"));
   private IModelCustom rotating = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/seasonal/christmaspyramid_rest.obj"));
   private ModelChristmasPyramid modelMC = new ModelChristmasPyramid();
   private ResourceLocation textures = new ResourceLocation("reallifemod:textures/tiles/christmaspyramid.png");


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((ChristmaspyramidTE)te, x, y, z, scale);
   }

   public void doRender(ChristmaspyramidTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      if(!RealLifeModConfig.minecraftstyle) {
         this.func_147499_a(this.textures);
         GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
         GL11.glPushMatrix();
         GL11.glRotatef(te.getRotation(), 0.0F, 1.0F, 0.0F);
         this.rotating.renderAll();
         GL11.glPopMatrix();
         this.stand.renderAll();
      } else {
         GL11.glTranslatef((float)x + 0.5F, (float)y + 1.4F, (float)z + 0.5F);
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
         this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/ChristmasPyramid_MC.png"));
         this.modelMC.rotor1.rotateAngleY = te.getRotation();
         this.modelMC.rotor2.rotateAngleY = te.getRotation();
         this.modelMC.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
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
