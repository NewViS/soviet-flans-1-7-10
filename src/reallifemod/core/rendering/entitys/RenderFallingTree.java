package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import de.ItsAMysterious.mods.reallifemod.core.tiles.FirTreeTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFallingTree extends Render {

   private TileEntity tile;


   public RenderFallingTree(TileEntity entity) {
      this.tile = entity;
   }

   public void func_76986_a(Entity log, double x, double y, double z, float rotationPitch, float rotationYaw) {
      GL11.glPushMatrix();
      GL11.glTranslated(x, y, z);
      GL11.glRotated((double)log.rotationYaw, 0.0D, 1.0D, 0.0D);
      GL11.glRotated((double)log.rotationPitch, 1.0D, 0.0D, 0.0D);
      GL11.glPushMatrix();
      if(this.tile instanceof FirTreeTE) {
         FirTreeTE te = (FirTreeTE)this.tile;
         GL11.glScaled(te.scale, te.scale, te.scale);
      }

      Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/tiles/SpruceTree.png"));
      TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tile, 0.0D, 0.0D, 0.0D, 0.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   protected ResourceLocation func_110775_a(Entity log) {
      return null;
   }
}
