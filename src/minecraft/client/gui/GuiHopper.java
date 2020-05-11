package net.minecraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiHopper extends GuiContainer {

   private static final ResourceLocation field_147085_u = new ResourceLocation("textures/gui/container/hopper.png");
   private IInventory field_147084_v;
   private IInventory field_147083_w;


   public GuiHopper(InventoryPlayer var1, IInventory var2) {
      super(new ContainerHopper(var1, var2));
      this.field_147084_v = var1;
      this.field_147083_w = var2;
      super.allowUserInput = false;
      super.ySize = 133;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      super.fontRendererObj.drawString(this.field_147083_w.hasCustomInventoryName()?this.field_147083_w.getInventoryName():I18n.format(this.field_147083_w.getInventoryName(), new Object[0]), 8, 6, 4210752);
      super.fontRendererObj.drawString(this.field_147084_v.hasCustomInventoryName()?this.field_147084_v.getInventoryName():I18n.format(this.field_147084_v.getInventoryName(), new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(field_147085_u);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
   }

}
