package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.FirTreeTE;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class FirTreeTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/Trees/firtree.obj"));
   Tessellator tessellator;


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((FirTreeTE)te, x, y, z, scale);
   }

   public void doRender(FirTreeTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glEnable(3008);
      GL11.glDisable(2884);
      GL11.glTranslated((double)((float)x) + 0.5D, (double)((float)y), (double)((float)z + 0.5F));
      GL11.glScaled(te.scale, te.scale, te.scale);
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/firtree.png"));
      if(te.isTrunk) {
         this.model.renderPart("Stump");
      } else {
         GL11.glRotated(FirTreeTE.windRotation, 0.0D, 1.6D, 0.0D);
         this.model.renderPart("Stump");
         this.model.renderPart("Trunk_Trunk.001");
      }

      GL11.glPopMatrix();
   }
}
