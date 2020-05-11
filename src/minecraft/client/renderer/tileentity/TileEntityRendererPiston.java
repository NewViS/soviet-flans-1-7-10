package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileEntityRendererPiston extends TileEntitySpecialRenderer {

   private RenderBlocks field_147516_b;


   public void renderTileEntityAt(TileEntityPiston var1, double var2, double var4, double var6, float var8) {
      Block var9 = var1.getStoredBlockID();
      if(var9.getMaterial() != Material.air && var1.func_145860_a(var8) < 1.0F) {
         Tessellator var10 = Tessellator.instance;
         this.bindTexture(TextureMap.locationBlocksTexture);
         RenderHelper.disableStandardItemLighting();
         GL11.glBlendFunc(770, 771);
         GL11.glEnable(3042);
         GL11.glDisable(2884);
         if(Minecraft.isAmbientOcclusionEnabled()) {
            GL11.glShadeModel(7425);
         } else {
            GL11.glShadeModel(7424);
         }

         var10.startDrawingQuads();
         var10.setTranslation((double)((float)var2 - (float)var1.xCoord + var1.func_145865_b(var8)), (double)((float)var4 - (float)var1.yCoord + var1.func_145862_c(var8)), (double)((float)var6 - (float)var1.zCoord + var1.func_145859_d(var8)));
         var10.setColorOpaque_F(1.0F, 1.0F, 1.0F);
         if(var9 == Blocks.piston_head && var1.func_145860_a(var8) < 0.5F) {
            this.field_147516_b.renderPistonExtensionAllFaces(var9, var1.xCoord, var1.yCoord, var1.zCoord, false);
         } else if(var1.func_145867_d() && !var1.isExtending()) {
            Blocks.piston_head.func_150086_a(((BlockPistonBase)var9).getPistonExtensionTexture());
            this.field_147516_b.renderPistonExtensionAllFaces(Blocks.piston_head, var1.xCoord, var1.yCoord, var1.zCoord, var1.func_145860_a(var8) < 0.5F);
            Blocks.piston_head.func_150087_e();
            var10.setTranslation((double)((float)var2 - (float)var1.xCoord), (double)((float)var4 - (float)var1.yCoord), (double)((float)var6 - (float)var1.zCoord));
            this.field_147516_b.renderPistonBaseAllFaces(var9, var1.xCoord, var1.yCoord, var1.zCoord);
         } else {
            this.field_147516_b.renderBlockAllFaces(var9, var1.xCoord, var1.yCoord, var1.zCoord);
         }

         var10.setTranslation(0.0D, 0.0D, 0.0D);
         var10.draw();
         RenderHelper.enableStandardItemLighting();
      }
   }

   public void func_147496_a(World var1) {
      this.field_147516_b = new RenderBlocks(var1);
   }
}
