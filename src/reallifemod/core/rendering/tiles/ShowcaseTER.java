package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.config.Config;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ShowcaseTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class ShowcaseTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/Vitrine.obj"));
   private RenderItem itemRenderer;
   private RenderManager renderManager;
   IItemRenderer customRenderer;
   private ItemStack stackToDisplay;
   private boolean fancyGraphics;


   public ShowcaseTER() {
      this.renderManager = RenderManager.instance;
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float scale) {
      this.doRender((ShowcaseTE)te, x, y, z, scale);
   }

   public void doRender(ShowcaseTE te, double x, double y, double z, float scale) {
      this.itemRenderer = new RenderItem() {
         public byte getMiniBlockCount(ItemStack stack, byte original) {
            return (byte)1;
         }
         public byte getMiniItemCount(ItemStack stack, byte original) {
            return (byte)1;
         }
         public boolean shouldBob() {
            return false;
         }
         public boolean shouldSpreadItems() {
            return false;
         }
      };
      this.itemRenderer.func_76976_a(this.renderManager);
      GL11.glPushMatrix();
      GL11.glEnable(3008);
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/Vitrine.png"));
      this.model.renderAll();
      this.fancyGraphics = this.renderManager.options.fancyGraphics;
      ItemStack slot1 = te.func_70301_a(0);
      if(te != null) {
         this.renderSlotItem(slot1, -0.35D, -0.05D, -0.35D, te);
      }

      GL11.glPopMatrix();
   }

   public void renderSlotItem(ItemStack stack, double AdjustmentX, double AdjustmentY, double AdjustmentZ, ShowcaseTE te) {
      if(stack != null) {
         EntityItem slotEntity = new EntityItem(te.func_145831_w(), 0.0D, 0.0D, 0.0D, stack);
         slotEntity.hoverStart = 0.0F;
         GL11.glPushMatrix();
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glTranslated((double)((float)AdjustmentX), AdjustmentY, AdjustmentZ);
         if(!stack.getDisplayName().toLowerCase().contains("map")) {
            RenderItem _tmp = this.itemRenderer;
            RenderItem.renderInFrame = true;
         }

         GL11.glEnable(3008);
         GL11.glPushMatrix();
         GL11.glTranslated(0.025D, 0.35D, 0.0D);
         this.itemRenderer.doRender(slotEntity, 0.3225D, 0.3225D, 0.3225D, 180.0F, 0.0F);
         GL11.glPopMatrix();
         if(!this.fancyGraphics && !Config.isBlock(stack)) {
            this.itemRenderer.doRender(slotEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
         }

         GL11.glPopMatrix();
      }

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
