package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiChest extends GuiContainer {

   private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
   private IInventory upperChestInventory;
   private IInventory lowerChestInventory;
   private int inventoryRows;


   public GuiChest(IInventory var1, IInventory var2) {
      super(new ContainerChest(var1, var2));
      this.upperChestInventory = var1;
      this.lowerChestInventory = var2;
      super.allowUserInput = false;
      short var3 = 222;
      int var4 = var3 - 108;
      this.inventoryRows = var2.getSizeInventory() / 9;
      super.ySize = var4 + this.inventoryRows * 18;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      super.fontRendererObj.drawString(this.lowerChestInventory.hasCustomInventoryName()?this.lowerChestInventory.getInventoryName():I18n.format(this.lowerChestInventory.getInventoryName(), new Object[0]), 8, 6, 4210752);
      super.fontRendererObj.drawString(this.upperChestInventory.hasCustomInventoryName()?this.upperChestInventory.getInventoryName():I18n.format(this.upperChestInventory.getInventoryName(), new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(field_147017_u);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, this.inventoryRows * 18 + 17);
      this.drawTexturedModalRect(var4, var5 + this.inventoryRows * 18 + 17, 0, 126, super.xSize, 96);
   }

}
