package net.minecraft.client.gui.inventory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.Iterator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBeacon$CancelButton;
import net.minecraft.client.gui.inventory.GuiBeacon$ConfirmButton;
import net.minecraft.client.gui.inventory.GuiBeacon$PowerButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class GuiBeacon extends GuiContainer {

   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation beaconGuiTextures = new ResourceLocation("textures/gui/container/beacon.png");
   private TileEntityBeacon tileBeacon;
   private GuiBeacon$ConfirmButton beaconConfirmButton;
   private boolean buttonsNotDrawn;


   public GuiBeacon(InventoryPlayer var1, TileEntityBeacon var2) {
      super(new ContainerBeacon(var1, var2));
      this.tileBeacon = var2;
      super.xSize = 230;
      super.ySize = 219;
   }

   public void initGui() {
      super.initGui();
      super.buttonList.add(this.beaconConfirmButton = new GuiBeacon$ConfirmButton(this, -1, super.guiLeft + 164, super.guiTop + 107));
      super.buttonList.add(new GuiBeacon$CancelButton(this, -2, super.guiLeft + 190, super.guiTop + 107));
      this.buttonsNotDrawn = true;
      this.beaconConfirmButton.enabled = false;
   }

   public void updateScreen() {
      super.updateScreen();
      if(this.buttonsNotDrawn && this.tileBeacon.getLevels() >= 0) {
         this.buttonsNotDrawn = false;

         int var2;
         int var3;
         int var4;
         int var5;
         GuiBeacon$PowerButton var6;
         for(int var1 = 0; var1 <= 2; ++var1) {
            var2 = TileEntityBeacon.effectsList[var1].length;
            var3 = var2 * 22 + (var2 - 1) * 2;

            for(var4 = 0; var4 < var2; ++var4) {
               var5 = TileEntityBeacon.effectsList[var1][var4].id;
               var6 = new GuiBeacon$PowerButton(this, var1 << 8 | var5, super.guiLeft + 76 + var4 * 24 - var3 / 2, super.guiTop + 22 + var1 * 25, var5, var1);
               super.buttonList.add(var6);
               if(var1 >= this.tileBeacon.getLevels()) {
                  var6.enabled = false;
               } else if(var5 == this.tileBeacon.getPrimaryEffect()) {
                  var6.func_146140_b(true);
               }
            }
         }

         byte var7 = 3;
         var2 = TileEntityBeacon.effectsList[var7].length + 1;
         var3 = var2 * 22 + (var2 - 1) * 2;

         for(var4 = 0; var4 < var2 - 1; ++var4) {
            var5 = TileEntityBeacon.effectsList[var7][var4].id;
            var6 = new GuiBeacon$PowerButton(this, var7 << 8 | var5, super.guiLeft + 167 + var4 * 24 - var3 / 2, super.guiTop + 47, var5, var7);
            super.buttonList.add(var6);
            if(var7 >= this.tileBeacon.getLevels()) {
               var6.enabled = false;
            } else if(var5 == this.tileBeacon.getSecondaryEffect()) {
               var6.func_146140_b(true);
            }
         }

         if(this.tileBeacon.getPrimaryEffect() > 0) {
            GuiBeacon$PowerButton var8 = new GuiBeacon$PowerButton(this, var7 << 8 | this.tileBeacon.getPrimaryEffect(), super.guiLeft + 167 + (var2 - 1) * 24 - var3 / 2, super.guiTop + 47, this.tileBeacon.getPrimaryEffect(), var7);
            super.buttonList.add(var8);
            if(var7 >= this.tileBeacon.getLevels()) {
               var8.enabled = false;
            } else if(this.tileBeacon.getPrimaryEffect() == this.tileBeacon.getSecondaryEffect()) {
               var8.func_146140_b(true);
            }
         }
      }

      this.beaconConfirmButton.enabled = this.tileBeacon.getStackInSlot(0) != null && this.tileBeacon.getPrimaryEffect() > 0;
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == -2) {
         super.mc.displayGuiScreen((GuiScreen)null);
      } else if(var1.id == -1) {
         String var2 = "MC|Beacon";
         ByteBuf var3 = Unpooled.buffer();

         try {
            var3.writeInt(this.tileBeacon.getPrimaryEffect());
            var3.writeInt(this.tileBeacon.getSecondaryEffect());
            super.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload(var2, var3));
         } catch (Exception var8) {
            logger.error("Couldn\'t send beacon info", var8);
         } finally {
            var3.release();
         }

         super.mc.displayGuiScreen((GuiScreen)null);
      } else if(var1 instanceof GuiBeacon$PowerButton) {
         if(((GuiBeacon$PowerButton)var1).func_146141_c()) {
            return;
         }

         int var10 = var1.id;
         int var11 = var10 & 255;
         int var4 = var10 >> 8;
         if(var4 < 3) {
            this.tileBeacon.setPrimaryEffect(var11);
         } else {
            this.tileBeacon.setSecondaryEffect(var11);
         }

         super.buttonList.clear();
         this.initGui();
         this.updateScreen();
      }

   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      RenderHelper.disableStandardItemLighting();
      this.drawCenteredString(super.fontRendererObj, I18n.format("tile.beacon.primary", new Object[0]), 62, 10, 14737632);
      this.drawCenteredString(super.fontRendererObj, I18n.format("tile.beacon.secondary", new Object[0]), 169, 10, 14737632);
      Iterator var3 = super.buttonList.iterator();

      while(var3.hasNext()) {
         GuiButton var4 = (GuiButton)var3.next();
         if(var4.func_146115_a()) {
            var4.func_146111_b(var1 - super.guiLeft, var2 - super.guiTop);
            break;
         }
      }

      RenderHelper.enableGUIStandardItemLighting();
   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(beaconGuiTextures);
      int var4 = (super.width - super.xSize) / 2;
      int var5 = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, super.xSize, super.ySize);
      GuiScreen.itemRender.zLevel = 100.0F;
      GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), new ItemStack(Items.emerald), var4 + 42, var5 + 109);
      GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), new ItemStack(Items.diamond), var4 + 42 + 22, var5 + 109);
      GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), new ItemStack(Items.gold_ingot), var4 + 42 + 44, var5 + 109);
      GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), new ItemStack(Items.iron_ingot), var4 + 42 + 66, var5 + 109);
      GuiScreen.itemRender.zLevel = 0.0F;
   }

   // $FF: synthetic method
   static ResourceLocation access$000() {
      return beaconGuiTextures;
   }

   // $FF: synthetic method
   static void access$100(GuiBeacon var0, String var1, int var2, int var3) {
      var0.drawCreativeTabHoveringText(var1, var2, var3);
   }

   // $FF: synthetic method
   static void access$200(GuiBeacon var0, String var1, int var2, int var3) {
      var0.drawCreativeTabHoveringText(var1, var2, var3);
   }

   // $FF: synthetic method
   static void access$300(GuiBeacon var0, String var1, int var2, int var3) {
      var0.drawCreativeTabHoveringText(var1, var2, var3);
   }

}
