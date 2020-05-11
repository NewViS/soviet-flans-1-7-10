package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.atmTE;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class AtmTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/atm.obj"));


   private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      GL11.glPushMatrix();
      GL11.glRotatef((float)(meta * -90), 0.0F, 0.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((atmTE)te, x, y, z, scale);
   }

   public void doRender(atmTE te, double x, double y, double z, float scale) {
      Tessellator tesselator = Tessellator.instance;
      GL11.glPushMatrix();
      if(te.func_145831_w() != null) {
         GL11.glDisable(2884);
      }

      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      tesselator.startDrawingQuads();
      tesselator.addVertex(-0.5D, y - 0.5D, z);
      tesselator.addVertex(0.5D, y - 0.5D, z);
      tesselator.addVertex(0.5D, y + 0.5D, z);
      tesselator.addVertex(-0.5D, y - 0.5D, z);
      tesselator.draw();
      GL11.glScalef(1.5F, 1.5F, 1.5F);
      GL11.glScalef(0.3F, 0.3F, 0.3F);
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/atm.png"));
      this.model.renderAll();
      GL11.glPopMatrix();
   }
}
