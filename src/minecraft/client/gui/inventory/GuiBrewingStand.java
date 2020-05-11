package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiBrewingStand extends GuiContainer {

   private static final ResourceLocation brewingStandGuiTextures = new ResourceLocation("textures/gui/container/brewing_stand.png");
   private TileEntityBrewingStand tileBrewingStand;


   public GuiBrewingStand(InventoryPlayer var1, TileEntityBrewingStand var2) {
      super(new ContainerBrewingStand(var1, var2));
      this.tileBrewingStand = var2;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      String var3 = this.tileBrewingStand.hasCustomInventoryName()?this.tileBrewingStand.getInventoryName():I18n.format(this.tileBrewingStand.getInventoryName(), new Object[0]);
      super.fontRendererObj.drawString(var3, super.xSize / 2 - super.fontRendererObj.getStringWidth(var3) / 2, 6, 4210752);
      super.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(brewingStandGuiTextures);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
      int var6 = this.tileBrewingStand.getBrewTime();
      if(var6 > 0) {
         int var7 = (int)(28.0F * (1.0F - (float)var6 / 400.0F));
         if(var7 > 0) {
            this.drawTexturedModalRect(var4 + 97, var5 + 16, 176, 0, 9, var7);
         }

         int var8 = var6 / 2 % 7;
         switch(var8) {
         case 0:
            var7 = 29;
            break;
         case 1:
            var7 = 24;
            break;
         case 2:
            var7 = 20;
            break;
         case 3:
            var7 = 16;
            break;
         case 4:
            var7 = 11;
            break;
         case 5:
            var7 = 6;
            break;
         case 6:
            var7 = 0;
         }

         if(var7 > 0) {
            this.drawTexturedModalRect(var4 + 65, var5 + 14 + 29 - var7, 185, 29 - var7, 12, var7);
         }
      }

   }

}
