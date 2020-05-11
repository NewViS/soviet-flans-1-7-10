package net.minecraft.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiScreenHorseInventory extends GuiContainer {

   private static final ResourceLocation horseGuiTextures = new ResourceLocation("textures/gui/container/horse.png");
   private IInventory field_147030_v;
   private IInventory field_147029_w;
   private EntityHorse field_147034_x;
   private float field_147033_y;
   private float field_147032_z;


   public GuiScreenHorseInventory(IInventory var1, IInventory var2, EntityHorse var3) {
      super(new ContainerHorseInventory(var1, var2, var3));
      this.field_147030_v = var1;
      this.field_147029_w = var2;
      this.field_147034_x = var3;
      super.allowUserInput = false;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      super.fontRendererObj.drawString(this.field_147029_w.hasCustomInventoryName()?this.field_147029_w.getInventoryName():I18n.format(this.field_147029_w.getInventoryName(), new Object[0]), 8, 6, 4210752);
      super.fontRendererObj.drawString(this.field_147030_v.hasCustomInventoryName()?this.field_147030_v.getInventoryName():I18n.format(this.field_147030_v.getInventoryName(), new Object[0]), 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(horseGuiTextures);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
      if(this.field_147034_x.isChested()) {
         this.drawTexturedModalRect(var4 + 79, var5 + 17, 0, super.ySize, 90, 54);
      }

      if(this.field_147034_x.func_110259_cr()) {
         this.drawTexturedModalRect(var4 + 7, var5 + 35, 0, super.ySize + 54, 18, 18);
      }

      GuiInventory.func_147046_a(var4 + 51, var5 + 60, 17, (float)(var4 + 51) - this.field_147033_y, (float)(var5 + 75 - 50) - this.field_147032_z, this.field_147034_x);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.field_147033_y = (float)var1;
      this.field_147032_z = (float)var2;
      super.drawScreen(var1, var2, var3);
   }

}
