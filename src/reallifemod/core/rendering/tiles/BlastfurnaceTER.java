package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.BlastfurnaceTE;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class BlastfurnaceTER extends TileEntitySpecialRenderer {

   private final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/industrial/blastfurnace.obj"));
   private ResourceLocation ovenTexture = new ResourceLocation("reallifemod:textures/tiles/OvenTexture.png");


   private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      GL11.glPushMatrix();
      GL11.glRotatef((float)(meta * -90), 0.0F, 0.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public void func_147500_a(TileEntity tileEntity, double var1, double var2, double var3, float var4) {
      this.renderModelAt((BlastfurnaceTE)tileEntity, var1, var2, var3, var4);
   }

   public void renderModelAt(BlastfurnaceTE tileEntity, double x, double y, double z, float f) {
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      GL11.glDisable(2884);
      GL11.glRotatef((float)(tileEntity.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(tileEntity.func_145832_p() * 90 == 180 || tileEntity.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      GL11.glScaled(0.75D, 0.75D, 0.75D);
      this.func_147499_a(this.ovenTexture);
      this.model.renderAll();
      GL11.glPopMatrix();
   }
}
