package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderFallingBlock extends Render {

   private final RenderBlocks field_147920_a = new RenderBlocks();


   public RenderFallingBlock() {
      super.shadowSize = 0.5F;
   }

   public void doRender(EntityFallingBlock var1, double var2, double var4, double var6, float var8, float var9) {
      World var10 = var1.func_145807_e();
      Block var11 = var1.func_145805_f();
      int var12 = MathHelper.floor_double(var1.posX);
      int var13 = MathHelper.floor_double(var1.posY);
      int var14 = MathHelper.floor_double(var1.posZ);
      if(var11 != null && var11 != var10.getBlock(var12, var13, var14)) {
         GL11.glPushMatrix();
         GL11.glTranslatef((float)var2, (float)var4, (float)var6);
         this.bindEntityTexture(var1);
         GL11.glDisable(2896);
         Tessellator var15;
         if(var11 instanceof BlockAnvil) {
            this.field_147920_a.blockAccess = var10;
            var15 = Tessellator.instance;
            var15.startDrawingQuads();
            var15.setTranslation((double)((float)(-var12) - 0.5F), (double)((float)(-var13) - 0.5F), (double)((float)(-var14) - 0.5F));
            this.field_147920_a.renderBlockAnvilMetadata((BlockAnvil)var11, var12, var13, var14, var1.field_145814_a);
            var15.setTranslation(0.0D, 0.0D, 0.0D);
            var15.draw();
         } else if(var11 instanceof BlockDragonEgg) {
            this.field_147920_a.blockAccess = var10;
            var15 = Tessellator.instance;
            var15.startDrawingQuads();
            var15.setTranslation((double)((float)(-var12) - 0.5F), (double)((float)(-var13) - 0.5F), (double)((float)(-var14) - 0.5F));
            this.field_147920_a.renderBlockDragonEgg((BlockDragonEgg)var11, var12, var13, var14);
            var15.setTranslation(0.0D, 0.0D, 0.0D);
            var15.draw();
         } else {
            this.field_147920_a.setRenderBoundsFromBlock(var11);
            this.field_147920_a.renderBlockSandFalling(var11, var10, var12, var13, var14, var1.field_145814_a);
         }

         GL11.glEnable(2896);
         GL11.glPopMatrix();
      }

   }

   protected ResourceLocation getEntityTexture(EntityFallingBlock var1) {
      return TextureMap.locationBlocksTexture;
   }
}
