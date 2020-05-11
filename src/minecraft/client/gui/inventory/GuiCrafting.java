package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiCrafting extends GuiContainer {

   private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");


   public GuiCrafting(InventoryPlayer var1, World var2, int var3, int var4, int var5) {
      super(new ContainerWorkbench(var1, var2, var3, var4, var5));
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      super.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, 4210752);
      super.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
   }

}
