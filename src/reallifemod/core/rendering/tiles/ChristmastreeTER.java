package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.models.ModelChristmasTree;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChristmasTreeTE;
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

public class ChristmastreeTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/seasonal/christmastree.obj"));
   private ModelChristmasTree modelMC = new ModelChristmasTree();
   private ResourceLocation textures = new ResourceLocation("reallifemod:models/seasonal/christmastree.png");


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((ChristmasTreeTE)te, x, y, z, scale);
   }

   public void doRender(ChristmasTreeTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      GL11.glScalef(0.7F, 0.7F, 0.7F);
      int facing = te.getFacingDirection();
      short k = 0;
      if(facing == 2) {
         k = 0;
      }

      if(facing == 3) {
         k = 180;
      }

      if(facing == 4) {
         k = -90;
      }

      if(facing == 5) {
         k = 90;
      }

      GL11.glRotatef((float)k, 0.0F, 1.0F, 0.0F);
      this.func_147499_a(this.textures);
      if(!RealLifeModConfig.minecraftstyle) {
         this.model.renderAll();
      } else {
         GL11.glTranslated(0.0D, 1.5D, 0.0D);
         GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
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
