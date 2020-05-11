package net.minecraft.client.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMerchant$MerchantButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class GuiMerchant extends GuiContainer {

   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation field_147038_v = new ResourceLocation("textures/gui/container/villager.png");
   private IMerchant field_147037_w;
   private GuiMerchant$MerchantButton field_147043_x;
   private GuiMerchant$MerchantButton field_147042_y;
   private int field_147041_z;
   private String field_147040_A;


   public GuiMerchant(InventoryPlayer var1, IMerchant var2, World var3, String var4) {
      super(new ContainerMerchant(var1, var2, var3));
      this.field_147037_w = var2;
      this.field_147040_A = var4 != null && var4.length() >= 1?var4:I18n.format("entity.Villager.name", new Object[0]);
   }

   public void initGui() {
      super.initGui();
      int var1 = (super.width - super.xSize) / 2;
      int var2 = (super.height - super.ySize) / 2;
      super.buttonList.add(this.field_147043_x = new GuiMerchant$MerchantButton(1, var1 + 120 + 27, var2 + 24 - 1, true));
      super.buttonList.add(this.field_147042_y = new GuiMerchant$MerchantButton(2, var1 + 36 - 19, var2 + 24 - 1, false));
      this.field_147043_x.enabled = false;
      this.field_147042_y.enabled = false;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      super.fontRendererObj.drawString(this.field_147040_A, super.xSize / 2 - super.fontRendererObj.getStringWidth(this.field_147040_A) / 2, 6, 4210752);
      super.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   public void updateScreen() {
      super.updateScreen();
      MerchantRecipeList var1 = this.field_147037_w.getRecipes(super.mc.thePlayer);
      if(var1 != null) {
         this.field_147043_x.enabled = this.field_147041_z < var1.size() - 1;
         this.field_147042_y.enabled = this.field_147041_z > 0;
      }

   }

   protected void actionPerformed(GuiButton var1) {
      boolean var2 = false;
      if(var1 == this.field_147043_x) {
         ++this.field_147041_z;
         var2 = true;
      } else if(var1 == this.field_147042_y) {
         --this.field_147041_z;
         var2 = true;
      }

      if(var2) {
         ((ContainerMerchant)super.inventorySlots).setCurrentRecipeIndex(this.field_147041_z);
         ByteBuf var3 = Unpooled.buffer();

         try {
            var3.writeInt(this.field_147041_z);
            super.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|TrSel", var3));
         } catch (Exception var8) {
            logger.error("Couldn\'t send trade info", var8);
         } finally {
            var3.release();
         }
      }

   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(field_147038_v);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
      MerchantRecipeList var6 = this.field_147037_w.getRecipes(super.mc.thePlayer);
      if(var6 != null && !var6.isEmpty()) {
         int var7 = this.field_147041_z;
         MerchantRecipe var8 = (MerchantRecipe)var6.get(var7);
         if(var8.isRecipeDisabled()) {
            super.mc.getTextureManager().bindTexture(field_147038_v);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            this.drawTexturedModalRect(super.guiLeft + 83, super.guiTop + 21, 212, 0, 28, 21);
            this.drawTexturedModalRect(super.guiLeft + 83, super.guiTop + 51, 212, 0, 28, 21);
         }
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      MerchantRecipeList var4 = this.field_147037_w.getRecipes(super.mc.thePlayer);
      if(var4 != null && !var4.isEmpty()) {
         int var5 = (super.width - super.xSize) / 2;
         int var6 = (super.height - super.ySize) / 2;
         int var7 = this.field_147041_z;
         MerchantRecipe var8 = (MerchantRecipe)var4.get(var7);
         GL11.glPushMatrix();
         ItemStack var9 = var8.getItemToBuy();
         ItemStack var10 = var8.getSecondItemToBuy();
         ItemStack var11 = var8.getItemToSell();
         RenderHelper.enableGUIStandardItemLighting();
         GL11.glDisable(2896);
         GL11.glEnable('\u803a');
         GL11.glEnable(2903);
         GL11.glEnable(2896);
         GuiScreen.itemRender.zLevel = 100.0F;
         GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var9, var5 + 36, var6 + 24);
         GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var9, var5 + 36, var6 + 24);
         if(var10 != null) {
            GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var10, var5 + 62, var6 + 24);
            GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var10, var5 + 62, var6 + 24);
         }

         GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var11, var5 + 120, var6 + 24);
         GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var11, var5 + 120, var6 + 24);
         GuiScreen.itemRender.zLevel = 0.0F;
         GL11.glDisable(2896);
         if(this.func_146978_c(36, 24, 16, 16, var1, var2)) {
            this.renderToolTip(var9, var1, var2);
         } else if(var10 != null && this.func_146978_c(62, 24, 16, 16, var1, var2)) {
            this.renderToolTip(var10, var1, var2);
         } else if(this.func_146978_c(120, 24, 16, 16, var1, var2)) {
            this.renderToolTip(var11, var1, var2);
         }

         GL11.glPopMatrix();
         GL11.glEnable(2896);
         GL11.glEnable(2929);
         RenderHelper.enableStandardItemLighting();
      }

   }

   public IMerchant func_147035_g() {
      return this.field_147037_w;
   }

   // $FF: synthetic method
   static ResourceLocation access$000() {
      return field_147038_v;
   }

}
