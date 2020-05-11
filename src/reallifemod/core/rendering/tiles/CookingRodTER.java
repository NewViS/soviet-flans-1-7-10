package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.core.config.Config;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CookingRodTE;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class CookingRodTER extends TileEntitySpecialRenderer {

   private RenderItem itemRenderer;
   private RenderManager renderManager;
   IItemRenderer customRenderer;
   private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/CookingRod.obj"));
   private ResourceLocation texture = new ResourceLocation("reallifemod:textures/tiles/CoockingRod.png");
   private boolean fancyGraphics;


   public CookingRodTER() {
      this.renderManager = RenderManager.instance;
   }

   public void func_147500_a(TileEntity tile, double x, double y, double z, float f1) {
      this.renderCookingRod((CookingRodTE)tile, x, y, z, f1);
   }

   public void renderCookingRod(CookingRodTE tile, double x, double y, double z, float f1) {
      if(!tile.isEmpty()) {
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
         boolean fancyGraphics = this.renderManager.options.fancyGraphics;
         ItemStack slot1 = tile.func_70301_a(0);
         ItemStack slot2 = tile.func_70301_a(1);
         ItemStack slot3 = tile.func_70301_a(2);
         ItemStack slot4 = tile.func_70301_a(3);
         GL11.glPushMatrix();
         GL11.glTranslated(x + 0.5D, y + 0.6D, z + 0.5D);
         GL11.glPushMatrix();
         GL11.glTranslated(Math.sin(-tile.rodRotation * 3.141592653589793D / 180.0D) * 0.1D, -Math.cos(-tile.rodRotation * 3.141592653589793D / 180.0D) * 0.1D, 0.0D);
         GL11.glRotated(-tile.rodRotation, 0.0D, 0.0D, 1.0D);
         this.renderSlotItem(slot1, 0.0D, 0.0D, 0.0D, tile);
         this.renderSlotItem(slot2, 0.0D, 0.0D, 0.5D, tile);
         this.renderSlotItem(slot3, 0.0D, 0.0D, 1.0D, tile);
         this.renderSlotItem(slot4, 0.0D, 0.0D, -0.5D, tile);
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }

      GL11.glPushMatrix();
      GL11.glTranslated(x + 0.5D, y - 1.0D, z + 0.5D);
      GL11.glRotatef((float)(tile.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
      this.func_147499_a(new ResourceLocation("reallifemod:textures/tiles/CoockingRod.png"));
      if(tile.func_145832_p() * 90 == 180 || tile.func_145832_p() * 90 == 0) {
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
      }

      this.model.renderPart("stand");
      GL11.glPushMatrix();
      GL11.glTranslated(0.0D, 1.6D, 0.0D);
      GL11.glRotated(tile.rodRotation, 0.0D, 0.0D, 1.0D);
      this.model.renderPart("rod");
      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   public void renderSlotItem(ItemStack stack, double AdjustmentX, double AdjustmentY, double AdjustmentZ, CookingRodTE te) {
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

         if(stack.getItem() == Items.book) {
            GL11.glDisable(2896);
            GL11.glEnable(3008);
            Tessellator var13 = Tessellator.instance;
            GL11.glRotatef((float)(te.func_145832_p() * 90), 0.0F, 1.0F, 0.0F);
            if(te.func_145832_p() * 90 == 180 || te.func_145832_p() * 90 == 0) {
               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GL11.glTranslated(-0.35D, -0.08D, 0.025D);
            GL11.glEnable(2896);
            GL11.glTranslated(-0.035D, 0.04D, -0.025D);
            GL11.glScaled(0.6D, 0.75D, 0.6D);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
         } else {
            GL11.glEnable(3008);

            for(int var14 = 0; var14 < 4; ++var14) {
               for(int j = 0; j < 4; ++j) {
                  for(int k = 0; k < 4; ++k) {
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
}
