package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import org.lwjgl.opengl.GL11;

public class RenderItemFrame extends Render {

   private static final ResourceLocation mapBackgroundTextures = new ResourceLocation("textures/map/map_background.png");
   private final RenderBlocks field_147916_f = new RenderBlocks();
   private final Minecraft field_147917_g = Minecraft.getMinecraft();
   private IIcon field_94147_f;


   public void updateIcons(IIconRegister var1) {
      this.field_94147_f = var1.registerIcon("itemframe_background");
   }

   public void doRender(EntityItemFrame var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      double var10 = var1.posX - var2 - 0.5D;
      double var12 = var1.posY - var4 - 0.5D;
      double var14 = var1.posZ - var6 - 0.5D;
      int var16 = var1.field_146063_b + Direction.offsetX[var1.hangingDirection];
      int var17 = var1.field_146064_c;
      int var18 = var1.field_146062_d + Direction.offsetZ[var1.hangingDirection];
      GL11.glTranslated((double)var16 - var10, (double)var17 - var12, (double)var18 - var14);
      if(var1.getDisplayedItem() != null && var1.getDisplayedItem().getItem() == Items.filled_map) {
         this.func_147915_b(var1);
      } else {
         this.renderFrameItemAsBlock(var1);
      }

      this.func_82402_b(var1);
      GL11.glPopMatrix();
      this.func_147914_a(var1, var2 + (double)((float)Direction.offsetX[var1.hangingDirection] * 0.3F), var4 - 0.25D, var6 + (double)((float)Direction.offsetZ[var1.hangingDirection] * 0.3F));
   }

   protected ResourceLocation getEntityTexture(EntityItemFrame var1) {
      return null;
   }

   private void func_147915_b(EntityItemFrame var1) {
      GL11.glPushMatrix();
      GL11.glRotatef(var1.rotationYaw, 0.0F, 1.0F, 0.0F);
      super.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      Block var2 = Blocks.planks;
      float var3 = 0.0625F;
      float var4 = 1.0F;
      float var5 = var4 / 2.0F;
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5 + 0.0625F), (double)(0.5F - var5 + 0.0625F), (double)var3, (double)(0.5F + var5 - 0.0625F), (double)(0.5F + var5 - 0.0625F));
      this.field_147916_f.setOverrideBlockTexture(this.field_94147_f);
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      this.field_147916_f.clearOverrideBlockTexture();
      this.field_147916_f.unlockBlockBounds();
      GL11.glPopMatrix();
      this.field_147916_f.setOverrideBlockTexture(Blocks.planks.getIcon(1, 2));
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5), (double)(0.5F - var5), (double)(var3 + 1.0E-4F), (double)(var3 + 0.5F - var5), (double)(0.5F + var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F + var5 - var3), (double)(0.5F - var5), (double)(var3 + 1.0E-4F), (double)(0.5F + var5), (double)(0.5F + var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5), (double)(0.5F - var5), (double)var3, (double)(0.5F + var5), (double)(var3 + 0.5F - var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5), (double)(0.5F + var5 - var3), (double)var3, (double)(0.5F + var5), (double)(0.5F + var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      this.field_147916_f.unlockBlockBounds();
      this.field_147916_f.clearOverrideBlockTexture();
      GL11.glPopMatrix();
   }

   private void renderFrameItemAsBlock(EntityItemFrame var1) {
      GL11.glPushMatrix();
      GL11.glRotatef(var1.rotationYaw, 0.0F, 1.0F, 0.0F);
      super.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
      Block var2 = Blocks.planks;
      float var3 = 0.0625F;
      float var4 = 0.75F;
      float var5 = var4 / 2.0F;
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5 + 0.0625F), (double)(0.5F - var5 + 0.0625F), (double)(var3 * 0.5F), (double)(0.5F + var5 - 0.0625F), (double)(0.5F + var5 - 0.0625F));
      this.field_147916_f.setOverrideBlockTexture(this.field_94147_f);
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      this.field_147916_f.clearOverrideBlockTexture();
      this.field_147916_f.unlockBlockBounds();
      GL11.glPopMatrix();
      this.field_147916_f.setOverrideBlockTexture(Blocks.planks.getIcon(1, 2));
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5), (double)(0.5F - var5), (double)(var3 + 1.0E-4F), (double)(var3 + 0.5F - var5), (double)(0.5F + var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F + var5 - var3), (double)(0.5F - var5), (double)(var3 + 1.0E-4F), (double)(0.5F + var5), (double)(0.5F + var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5), (double)(0.5F - var5), (double)var3, (double)(0.5F + var5), (double)(var3 + 0.5F - var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      this.field_147916_f.overrideBlockBounds(0.0D, (double)(0.5F - var5), (double)(0.5F + var5 - var3), (double)var3, (double)(0.5F + var5), (double)(0.5F + var5));
      this.field_147916_f.renderBlockAsItem(var2, 0, 1.0F);
      GL11.glPopMatrix();
      this.field_147916_f.unlockBlockBounds();
      this.field_147916_f.clearOverrideBlockTexture();
      GL11.glPopMatrix();
   }

   private void func_82402_b(EntityItemFrame var1) {
      ItemStack var2 = var1.getDisplayedItem();
      if(var2 != null) {
         EntityItem var3 = new EntityItem(var1.worldObj, 0.0D, 0.0D, 0.0D, var2);
         Item var4 = var3.getEntityItem().getItem();
         var3.getEntityItem().stackSize = 1;
         var3.hoverStart = 0.0F;
         GL11.glPushMatrix();
         GL11.glTranslatef(-0.453125F * (float)Direction.offsetX[var1.hangingDirection], -0.18F, -0.453125F * (float)Direction.offsetZ[var1.hangingDirection]);
         GL11.glRotatef(180.0F + var1.rotationYaw, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef((float)(-90 * var1.getRotation()), 0.0F, 0.0F, 1.0F);
         switch(var1.getRotation()) {
         case 1:
            GL11.glTranslatef(-0.16F, -0.16F, 0.0F);
            break;
         case 2:
            GL11.glTranslatef(0.0F, -0.32F, 0.0F);
            break;
         case 3:
            GL11.glTranslatef(0.16F, -0.16F, 0.0F);
         }

         if(var4 == Items.filled_map) {
            super.renderManager.renderEngine.bindTexture(mapBackgroundTextures);
            Tessellator var5 = Tessellator.instance;
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            float var6 = 0.0078125F;
            GL11.glScalef(var6, var6, var6);
            switch(var1.getRotation()) {
            case 0:
               GL11.glTranslatef(-64.0F, -87.0F, -1.5F);
               break;
            case 1:
               GL11.glTranslatef(-66.5F, -84.5F, -1.5F);
               break;
            case 2:
               GL11.glTranslatef(-64.0F, -82.0F, -1.5F);
               break;
            case 3:
               GL11.glTranslatef(-61.5F, -84.5F, -1.5F);
            }

            GL11.glNormal3f(0.0F, 0.0F, -1.0F);
            MapData var7 = Items.filled_map.getMapData(var3.getEntityItem(), var1.worldObj);
            GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            if(var7 != null) {
               this.field_147917_g.entityRenderer.getMapItemRenderer().func_148250_a(var7, true);
            }
         } else {
            if(var4 == Items.compass) {
               TextureManager var12 = Minecraft.getMinecraft().getTextureManager();
               var12.bindTexture(TextureMap.locationItemsTexture);
               TextureAtlasSprite var14 = ((TextureMap)var12.getTexture(TextureMap.locationItemsTexture)).getAtlasSprite(Items.compass.getIconIndex(var3.getEntityItem()).getIconName());
               if(var14 instanceof TextureCompass) {
                  TextureCompass var15 = (TextureCompass)var14;
                  double var8 = var15.currentAngle;
                  double var10 = var15.angleDelta;
                  var15.currentAngle = 0.0D;
                  var15.angleDelta = 0.0D;
                  var15.updateCompass(var1.worldObj, var1.posX, var1.posZ, (double)MathHelper.wrapAngleTo180_float((float)(180 + var1.hangingDirection * 90)), false, true);
                  var15.currentAngle = var8;
                  var15.angleDelta = var10;
               }
            }

            RenderItem.renderInFrame = true;
            RenderManager.instance.renderEntityWithPosYaw(var3, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderItem.renderInFrame = false;
            if(var4 == Items.compass) {
               TextureAtlasSprite var13 = ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationItemsTexture)).getAtlasSprite(Items.compass.getIconIndex(var3.getEntityItem()).getIconName());
               if(var13.getFrameCount() > 0) {
                  var13.updateAnimation();
               }
            }
         }

         GL11.glPopMatrix();
      }
   }

   protected void func_147914_a(EntityItemFrame var1, double var2, double var4, double var6) {
      if(Minecraft.isGuiEnabled() && var1.getDisplayedItem() != null && var1.getDisplayedItem().hasDisplayName() && super.renderManager.field_147941_i == var1) {
         float var8 = 1.6F;
         float var9 = 0.016666668F * var8;
         double var10 = var1.getDistanceSqToEntity(super.renderManager.livingPlayer);
         float var12 = var1.isSneaking()?32.0F:64.0F;
         if(var10 < (double)(var12 * var12)) {
            String var13 = var1.getDisplayedItem().getDisplayName();
            if(var1.isSneaking()) {
               FontRenderer var14 = this.getFontRendererFromRenderManager();
               GL11.glPushMatrix();
               GL11.glTranslatef((float)var2 + 0.0F, (float)var4 + var1.height + 0.5F, (float)var6);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-super.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(super.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
               GL11.glScalef(-var9, -var9, var9);
               GL11.glDisable(2896);
               GL11.glTranslatef(0.0F, 0.25F / var9, 0.0F);
               GL11.glDepthMask(false);
               GL11.glEnable(3042);
               GL11.glBlendFunc(770, 771);
               Tessellator var15 = Tessellator.instance;
               GL11.glDisable(3553);
               var15.startDrawingQuads();
               int var16 = var14.getStringWidth(var13) / 2;
               var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
               var15.addVertex((double)(-var16 - 1), -1.0D, 0.0D);
               var15.addVertex((double)(-var16 - 1), 8.0D, 0.0D);
               var15.addVertex((double)(var16 + 1), 8.0D, 0.0D);
               var15.addVertex((double)(var16 + 1), -1.0D, 0.0D);
               var15.draw();
               GL11.glEnable(3553);
               GL11.glDepthMask(true);
               var14.drawString(var13, -var14.getStringWidth(var13) / 2, 0, 553648127);
               GL11.glEnable(2896);
               GL11.glDisable(3042);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glPopMatrix();
            } else {
               this.func_147906_a(var1, var13, var2, var4, var6, 64);
            }
         }
      }

   }

}
