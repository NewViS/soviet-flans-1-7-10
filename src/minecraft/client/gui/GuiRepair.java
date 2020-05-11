package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.apache.commons.io.Charsets;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiRepair extends GuiContainer implements ICrafting {

   private static final ResourceLocation field_147093_u = new ResourceLocation("textures/gui/container/anvil.png");
   private ContainerRepair field_147092_v;
   private GuiTextField field_147091_w;
   private InventoryPlayer field_147094_x;


   public GuiRepair(InventoryPlayer var1, World var2, int var3, int var4, int var5) {
      super(new ContainerRepair(var1, var2, var3, var4, var5, Minecraft.getMinecraft().thePlayer));
      this.field_147094_x = var1;
      this.field_147092_v = (ContainerRepair)super.inventorySlots;
   }

   public void initGui() {
      super.initGui();
      Keyboard.enableRepeatEvents(true);
      int var1 = (super.width - super.xSize) / 2;
      int var2 = (super.height - super.ySize) / 2;
      this.field_147091_w = new GuiTextField(super.fontRendererObj, var1 + 62, var2 + 24, 103, 12);
      this.field_147091_w.setTextColor(-1);
      this.field_147091_w.setDisabledTextColour(-1);
      this.field_147091_w.setEnableBackgroundDrawing(false);
      this.field_147091_w.setMaxStringLength(40);
      super.inventorySlots.removeCraftingFromCrafters(this);
      super.inventorySlots.addCraftingToCrafters(this);
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      Keyboard.enableRepeatEvents(false);
      super.inventorySlots.removeCraftingFromCrafters(this);
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      GL11.glDisable(2896);
      GL11.glDisable(3042);
      super.fontRendererObj.drawString(I18n.format("container.repair", new Object[0]), 60, 6, 4210752);
      if(this.field_147092_v.maximumCost > 0) {
         int var3 = 8453920;
         boolean var4 = true;
         String var5 = I18n.format("container.repair.cost", new Object[]{Integer.valueOf(this.field_147092_v.maximumCost)});
         if(this.field_147092_v.maximumCost >= 40 && !super.mc.thePlayer.capabilities.isCreativeMode) {
            var5 = I18n.format("container.repair.expensive", new Object[0]);
            var3 = 16736352;
         } else if(!this.field_147092_v.getSlot(2).getHasStack()) {
            var4 = false;
         } else if(!this.field_147092_v.getSlot(2).canTakeStack(this.field_147094_x.player)) {
            var3 = 16736352;
         }

         if(var4) {
            int var6 = -16777216 | (var3 & 16579836) >> 2 | var3 & -16777216;
            int var7 = super.xSize - 8 - super.fontRendererObj.getStringWidth(var5);
            byte var8 = 67;
            if(super.fontRendererObj.getUnicodeFlag()) {
               drawRect(var7 - 3, var8 - 2, super.xSize - 7, var8 + 10, -16777216);
               drawRect(var7 - 2, var8 - 1, super.xSize - 8, var8 + 9, -12895429);
            } else {
               super.fontRendererObj.drawString(var5, var7, var8 + 1, var6);
               super.fontRendererObj.drawString(var5, var7 + 1, var8, var6);
               super.fontRendererObj.drawString(var5, var7 + 1, var8 + 1, var6);
            }

            super.fontRendererObj.drawString(var5, var7, var8, var3);
         }
      }

      GL11.glEnable(2896);
   }

   protected void keyTyped(char var1, int var2) {
      if(this.field_147091_w.textboxKeyTyped(var1, var2)) {
         this.func_147090_g();
      } else {
         super.keyTyped(var1, var2);
      }

   }

   private void func_147090_g() {
      String var1 = this.field_147091_w.getText();
      Slot var2 = this.field_147092_v.getSlot(0);
      if(var2 != null && var2.getHasStack() && !var2.getStack().hasDisplayName() && var1.equals(var2.getStack().getDisplayName())) {
         var1 = "";
      }

      this.field_147092_v.updateItemName(var1);
      super.mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|ItemName", var1.getBytes(Charsets.UTF_8)));
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_147091_w.mouseClicked(var1, var2, var3);
   }

   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      GL11.glDisable(2896);
      GL11.glDisable(3042);
      this.field_147091_w.drawTextBox();
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(field_147093_u);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
      this.drawTexturedModalRect(var4 + 59, var5 + 20, 0, super.ySize + (this.field_147092_v.getSlot(0).getHasStack()?0:16), 110, 16);
      if((this.field_147092_v.getSlot(0).getHasStack() || this.field_147092_v.getSlot(1).getHasStack()) && !this.field_147092_v.getSlot(2).getHasStack()) {
         this.drawTexturedModalRect(var4 + 99, var5 + 45, super.xSize, 0, 28, 21);
      }

   }

   public void sendContainerAndContentsToPlayer(Container var1, List var2) {
      this.sendSlotContents(var1, 0, var1.getSlot(0).getStack());
   }

   public void sendSlotContents(Container var1, int var2, ItemStack var3) {
      if(var2 == 0) {
         this.field_147091_w.setText(var3 == null?"":var3.getDisplayName());
         this.field_147091_w.setEnabled(var3 != null);
         if(var3 != null) {
            this.func_147090_g();
         }
      }

   }

   public void sendProgressBarUpdate(Container var1, int var2, int var3) {}

}
