package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.config.Config;
import de.ItsAMysterious.mods.reallifemod.core.tiles.SideboardTE;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class SideboardTER extends TileEntitySpecialRenderer {

   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/Sideboard.obj"));
   private RenderItem itemRenderer;
   private RenderManager renderManager;
   IItemRenderer customRenderer;
   private ItemStack stackToDisplay;
   private float degreeAngle;
   private boolean fancyGraphics;


   public SideboardTER() {
      this.renderManager = RenderManager.instance;
   }

   public void func_147500_a(TileEntity te, double x, double y, double z, float tick) {
      this.doRender((SideboardTE)te, x, y, z, tick);
   }

   public void doRender(SideboardTE te, double x, double y, double z, float tick) {
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
      GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
      GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/planks_spruce.png"));
      this.model.renderAll();
      GL11.glPopMatrix();
      boolean fancyGraphics = this.renderManager.options.fancyGraphics;
      ItemStack slot1 = te.func_70301_a(0);
      ItemStack slot2 = te.func_70301_a(1);
      ItemStack slot3 = te.func_70301_a(2);
      switch(te.func_145832_p()) {
      case 0:
         this.renderSlotItem(slot1, x + 1.0D, y + 1.05D, z + 0.75D, te);
         this.renderSlotItem(slot2, x + 0.5D, y + 1.05D, z + 0.75D, te);
         this.renderSlotItem(slot3, x, y + 1.05D, z + 0.75D, te);
         break;
      case 1:
         this.renderSlotItem(slot1, x + 0.25D, y + 1.05D, z + 1.0D, te);
         this.renderSlotItem(slot2, x + 0.25D, y + 1.05D, z + 0.5D, te);
         this.renderSlotItem(slot3, x + 0.25D, y + 1.05D, z, te);
         break;
      case 2:
         this.renderSlotItem(slot1, x, y + 1.05D, z + 0.25D, te);
         this.renderSlotItem(slot2, x + 0.5D, y + 1.05D, z + 0.25D, te);
         this.renderSlotItem(slot3, x + 1.0D, y + 1.05D, z + 0.25D, te);
         break;
      case 3:
         this.renderSlotItem(slot1, x + 0.75D, y + 1.05D, z, te);
         this.renderSlotItem(slot2, x + 0.75D, y + 1.05D, z + 0.5D, te);
         this.renderSlotItem(slot3, x + 0.75D, y + 1.05D, z + 1.0D, te);
      }

   }

   public void renderSlotItem(ItemStack stack, double AdjustmentX, double AdjustmentY, double AdjustmentZ, SideboardTE te) {
      if(stack != null) {
         GL11.glDisable(2884);
         EntityItem slotEntity = new EntityItem(te.func_145831_w(), 0.0D, 0.0D, 0.0D, stack);
         slotEntity.hoverStart = 0.0F;
         GL11.glPushMatrix();
         GL11.glTranslated((double)((float)AdjustmentX), AdjustmentY, AdjustmentZ);
         GL11.glScaled(1.25D, 1.25D, 1.25D);
         if(!stack.getDisplayName().toLowerCase().contains("map")) {
            RenderItem i = this.itemRenderer;
            RenderItem.renderInFrame = true;
         }

         int j;
         if(stack.getItem() == Items.book) {
            GL11.glDisable(2896);
            GL11.glEnable(3008);
            Tessellator var14 = Tessellator.instance;
            GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
            if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GL11.glTranslated(-0.35D, -0.08D, 0.025D);
            var14.startDrawingQuads();
            GL11.glScaled(0.425D, 0.6D, 0.75D);

            for(j = 0; j < stack.stackSize; ++j) {
               double k = 0.4D;
               this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/wool_colored_red.png"));
               var14.addVertexWithUV((double)j * 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + k, 0.15D, 0.0D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0426D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D + k, 0.15D, 0.2D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0426D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D, 0.15D, 0.2D, 0.8D);
               var14.addVertexWithUV((double)j * 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D, 0.15D, 0.0D, 0.8D);
               var14.addVertexWithUV((double)j * 0.0427D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + k, 0.15D, 0.0D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0427D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + k, -0.15D, 0.6D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0427D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D, -0.15D, 0.6D, 0.8D);
               var14.addVertexWithUV((double)j * 0.0427D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D, 0.15D, 0.0D, 0.8D);
               var14.addVertexWithUV((double)j * 0.0427D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + k, 0.15D, 0.0D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0427D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + k, -0.15D, 0.6D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0427D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D, -0.15D, 0.6D, 0.8D);
               var14.addVertexWithUV((double)j * 0.0427D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D, 0.15D, 0.0D, 0.8D);
            }

            var14.draw();
            var14.startDrawingQuads();

            for(j = 0; j < stack.stackSize; ++j) {
               this.func_147499_a(new ResourceLocation("minecraft:textures/blocks/wool_colored_white.png"));
               var14.addVertexWithUV(0.0D + (double)j * 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.39D, -0.14D, 0.0D, 0.0D);
               var14.addVertexWithUV(0.0D + (double)j * 0.0426D - 1.36D * (double)(j / 32) + 0.0425D, (double)(j / 32) * 0.4D + 0.39D, -0.14D, 1.6D, 0.0D);
               var14.addVertexWithUV(0.0D + (double)j * 0.0426D - 1.36D * (double)(j / 32) + 0.0425D, (double)(j / 32) * 0.4D + 0.0D, -0.14D, 1.6D, 0.04D);
               var14.addVertexWithUV(0.0D + (double)j * 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.0D, -0.14D, 0.0D, 0.04D);
               var14.addVertexWithUV((double)j * 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.39D, 0.15D, 0.0D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0426D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.39D, 0.15D, 1.6D, 0.0D);
               var14.addVertexWithUV((double)j * 0.0426D + 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.39D, -0.14D, 1.6D, 0.04D);
               var14.addVertexWithUV((double)j * 0.0426D - 1.3632D * (double)(j / 32), (double)(j / 32) * 0.4D + 0.39D, -0.14D, 0.0D, 0.04D);
            }

            var14.draw();
            GL11.glEnable(2896);
            GL11.glTranslated(-0.035D, 0.04D, -0.025D);
            GL11.glScaled(0.6D, 0.75D, 0.6D);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
         } else {
            GL11.glEnable(3008);

            for(int var15 = 0; var15 < 4; ++var15) {
               for(j = 0; j < 4; ++j) {
                  for(int var16 = 0; var16 < 4; ++var16) {
                     ;
                  }
               }
            }

            GL11.glPushMatrix();
            GL11.glTranslated(0.0D, 0.05D, 0.0D);
            GL11.glRotated((double)(te.func_145832_p() * 90), 0.0D, 1.0D, 0.0D);
            this.itemRenderer.doRender(slotEntity, 0.0D, 0.0D, 0.0D, 180.0F, 0.0F);
            GL11.glPopMatrix();
         }

         if(!this.fancyGraphics && !Config.isBlock(stack)) {
            this.itemRenderer.doRender(slotEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
         }

         GL11.glPopMatrix();
      }

   }

   public void renderText(String text, double xadjust, double yadjust, double zadjust) {
      FontRenderer fontRender = this.func_147498_b();
      GL11.glDepthMask(false);
      GL11.glScalef(0.005F, 0.005F, 0.005F);
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glTranslated(0.0D + xadjust, 100.0D + yadjust, -1.0D + zadjust);
      int adjust = fontRender.getStringWidth(text) / 2;
      fontRender.drawString(text, -adjust, 0, Color.white.getRGB(), true);
      GL11.glDepthMask(true);
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
