package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiDispenser extends GuiContainer {

   private static final ResourceLocation dispenserGuiTextures = new ResourceLocation("textures/gui/container/dispenser.png");
   public TileEntityDispenser tileDispenser;


   public GuiDispenser(InventoryPlayer var1, TileEntityDispenser var2) {
      super(new ContainerDispenser(var1, var2));
      this.tileDispenser = var2;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      String var3 = this.tileDispenser.hasCustomInventoryName()?this.tileDispenser.getInventoryName():I18n.format(this.tileDispenser.getInventoryName(), new Object[0]);
      super.fontRendererObj.drawString(var3, super.xSize / 2 - super.fontRendererObj.getStringWidth(var3) / 2, 6, 4210752);
      super.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(dispenserGuiTextures);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
   }

}
