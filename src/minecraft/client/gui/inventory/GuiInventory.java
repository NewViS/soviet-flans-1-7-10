package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class GuiInventory extends InventoryEffectRenderer {

   private float xSizeFloat;
   private float ySizeFloat;


   public GuiInventory(EntityPlayer var1) {
      super(var1.inventoryContainer);
      super.allowUserInput = true;
   }

   public void updateScreen() {
      if(super.mc.playerController.isInCreativeMode()) {
         super.mc.displayGuiScreen(new GuiContainerCreative(super.mc.thePlayer));
      }

   }

   public void initGui() {
      super.buttonList.clear();
      if(super.mc.playerController.isInCreativeMode()) {
         super.mc.displayGuiScreen(new GuiContainerCreative(super.mc.thePlayer));
      } else {
         super.initGui();
      }

   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      super.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 86, 16, 4210752);
   }

   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      this.xSizeFloat = (float)var1;
      this.ySizeFloat = (float)var2;
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(GuiContainer.field_147001_a);
      int var4 = super.guiLeft;
      int var5 = super.guiTop;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
      func_147046_a(var4 + 51, var5 + 75, 30, (float)(var4 + 51) - this.xSizeFloat, (float)(var5 + 75 - 50) - this.ySizeFloat, super.mc.thePlayer);
   }

   public static void func_147046_a(int var0, int var1, int var2, float var3, float var4, EntityLivingBase var5) {
      GL11.glEnable(2903);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var0, (float)var1, 50.0F);
      GL11.glScalef((float)(-var2), (float)var2, (float)var2);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float var6 = var5.renderYawOffset;
      float var7 = var5.rotationYaw;
      float var8 = var5.rotationPitch;
      float var9 = var5.prevRotationYawHead;
      float var10 = var5.rotationYawHead;
      GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(var4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      var5.renderYawOffset = (float)Math.atan((double)(var3 / 40.0F)) * 20.0F;
      var5.rotationYaw = (float)Math.atan((double)(var3 / 40.0F)) * 40.0F;
      var5.rotationPitch = -((float)Math.atan((double)(var4 / 40.0F))) * 20.0F;
      var5.rotationYawHead = var5.rotationYaw;
      var5.prevRotationYawHead = var5.rotationYaw;
      GL11.glTranslatef(0.0F, var5.yOffset, 0.0F);
      RenderManager.instance.playerViewY = 180.0F;
      RenderManager.instance.renderEntityWithPosYaw(var5, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      var5.renderYawOffset = var6;
      var5.rotationYaw = var7;
      var5.rotationPitch = var8;
      var5.prevRotationYawHead = var9;
      var5.rotationYawHead = var10;
      GL11.glPopMatrix();
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 0) {
         super.mc.displayGuiScreen(new GuiAchievements(this, super.mc.thePlayer.getStatFileWriter()));
      }

      if(var1.id == 1) {
         super.mc.displayGuiScreen(new GuiStats(this, super.mc.thePlayer.getStatFileWriter()));
      }

   }
}
