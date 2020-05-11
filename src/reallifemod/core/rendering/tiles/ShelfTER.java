package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.bankBlock;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ShelfTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ShelfTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/shelf.obj"));
   private bankBlock theblock;


   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((ShelfTE)te, x, y, z, scale);
   }

   public void doRender(ShelfTE te, double x, double y, double z, float scale) {
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glEnable(3008);
      GL11.glTranslated((double)((float)x + 0.5F), (double)((float)y), (double)((float)z + 0.5F));
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/shelf.png"));
      this.model.renderAll();
      this.renderContents(te);
      GL11.glEnable(2884);
      GL11.glDisable(3008);
      GL11.glPopMatrix();
   }

   private void renderContents(ShelfTE te) {
      for(int i = 0; i < te.items.size(); ++i) {
         EntityItem entity = new EntityItem((World)null);
         entity.setEntityItemStack((ItemStack)te.items.get(i));
         entity.func_70107_b((double)te.field_145851_c, (double)te.field_145848_d, (double)te.field_145849_e);
         GL11.glPushMatrix();
         this.renderEntity(entity);
         GL11.glPopMatrix();
      }

   }

   private void renderEntity(Entity entity) {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glPushMatrix();
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glPushMatrix();
      RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      GL11.glPopMatrix();
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
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
