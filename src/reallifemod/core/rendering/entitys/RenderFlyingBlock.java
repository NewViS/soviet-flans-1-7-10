package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.core.entitys.EntityFlyingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderFlyingBlock extends Render {

   private final RenderBlocks field_147920_a = new RenderBlocks();
   private static final String __OBFID = "CL_00000994";
   RenderBlocks blockRenderer = RenderBlocks.getInstance();


   public RenderFlyingBlock() {
      this.field_76989_e = 0.5F;
   }

   public void doRender(EntityFlyingBlock p_76986_1_, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
      World world = p_76986_1_.func_145807_e();
      Block block = p_76986_1_.func_145805_f();
      int i = MathHelper.floor_double(p_76986_1_.field_70165_t);
      int j = MathHelper.floor_double(p_76986_1_.field_70163_u);
      int k = MathHelper.floor_double(p_76986_1_.field_70161_v);
      if(block != null && block != world.getBlock(i, j, k)) {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)x, (float)y, (float)z);
         this.func_110777_b(p_76986_1_);
         GL11.glDisable(2896);
         Tessellator tessellator = Tessellator.instance;
         TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityChest(), (double)i, (double)j, (double)k, 1.0F);
         tessellator.startDrawing(7);
         tessellator.draw();
         if(block instanceof BlockAnvil) {
            this.field_147920_a.blockAccess = world;
            tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setTranslation((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
            this.field_147920_a.renderBlockAnvilMetadata((BlockAnvil)block, i, j, k, p_76986_1_.field_145814_a);
            tessellator.setTranslation(0.0D, 0.0D, 0.0D);
            tessellator.draw();
         } else if(block instanceof BlockDragonEgg) {
            this.field_147920_a.blockAccess = world;
            tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setTranslation((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
            this.field_147920_a.renderBlockDragonEgg((BlockDragonEgg)block, i, j, k);
            tessellator.setTranslation(0.0D, 0.0D, 0.0D);
            tessellator.draw();
         } else {
            this.field_147920_a.setRenderBoundsFromBlock(block);
            TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityChest(), (double)i, (double)j, (double)k, 1.0F);
         }

         GL11.glEnable(2896);
         GL11.glPopMatrix();
      }

   }

   protected ResourceLocation getEntityTexture(EntityFlyingBlock p_110775_1_) {
      return TextureMap.locationBlocksTexture;
   }

   protected ResourceLocation func_110775_a(Entity p_110775_1_) {
      return this.getEntityTexture((EntityFlyingBlock)p_110775_1_);
   }

   public void func_76986_a(Entity p_76986_1_, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
      this.doRender((EntityFlyingBlock)p_76986_1_, x, y, z, p_76986_8_, p_76986_9_);
   }
}
