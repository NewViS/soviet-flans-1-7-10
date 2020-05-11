package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiFurnace extends GuiContainer {

   private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
   private TileEntityFurnace tileFurnace;


   public GuiFurnace(InventoryPlayer var1, TileEntityFurnace var2) {
      super(new ContainerFurnace(var1, var2));
      this.tileFurnace = var2;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      String var3 = this.tileFurnace.hasCustomInventoryName()?this.tileFurnace.getInventoryName():I18n.format(this.tileFurnace.getInventoryName(), new Object[0]);
      super.fontRendererObj.drawString(var3, super.xSize / 2 - super.fontRendererObj.getStringWidth(var3) / 2, 6, 4210752);
      super.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(furnaceGuiTextures);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
      if(this.tileFurnace.isBurning()) {
         int var6 = this.tileFurnace.getBurnTimeRemainingScaled(13);
         this.drawTexturedModalRect(var4 + 56, var5 + 36 + 12 - var6, 176, 12 - var6, 14, var6 + 1);
         var6 = this.tileFurnace.getCookProgressScaled(24);
         this.drawTexturedModalRect(var4 + 79, var5 + 34, 176, 14, var6 + 1, 16);
      }

   }

}
