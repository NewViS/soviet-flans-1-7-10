package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.HeatingTE;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class HeatingTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/heating.obj"));


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((HeatingTE)te, x, y, z, scale);
   }

   public void doRender(HeatingTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/heating.png"));
      this.model.renderAll();
      GL11.glPopMatrix();
   }
}
