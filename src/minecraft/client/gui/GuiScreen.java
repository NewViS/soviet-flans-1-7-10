package net.minecraft.client.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiScreen extends Gui {

   protected static RenderItem itemRender = new RenderItem();
   protected Minecraft mc;
   public int width;
   public int height;
   protected List buttonList = new ArrayList();
   protected List labelList = new ArrayList();
   public boolean allowUserInput;
   protected FontRenderer fontRendererObj;
   private GuiButton selectedButton;
   private int eventButton;
   private long lastMouseEvent;
   private int field_146298_h;


   public void drawScreen(int var1, int var2, float var3) {
      int var4;
      for(var4 = 0; var4 < this.buttonList.size(); ++var4) {
         ((GuiButton)this.buttonList.get(var4)).drawButton(this.mc, var1, var2);
      }

      for(var4 = 0; var4 < this.labelList.size(); ++var4) {
         ((GuiLabel)this.labelList.get(var4)).func_146159_a(this.mc, var1, var2);
      }

   }

   protected void keyTyped(char var1, int var2) {
      if(var2 == 1) {
         this.mc.displayGuiScreen((GuiScreen)null);
         this.mc.setIngameFocus();
      }

   }

   public static String getClipboardString() {
      try {
         Transferable var0 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object)null);
         if(var0 != null && var0.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return (String)var0.getTransferData(DataFlavor.stringFlavor);
         }
      } catch (Exception var1) {
         ;
      }

      return "";
   }

   public static void setClipboardString(String var0) {
      try {
         StringSelection var1 = new StringSelection(var0);
         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var1, (ClipboardOwner)null);
      } catch (Exception var2) {
         ;
      }

   }

   protected void renderToolTip(ItemStack var1, int var2, int var3) {
      List var4 = var1.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

      for(int var5 = 0; var5 < var4.size(); ++var5) {
         if(var5 == 0) {
            var4.set(var5, var1.getRarity().rarityColor + (String)var4.get(var5));
         } else {
            var4.set(var5, EnumChatFormatting.GRAY + (String)var4.get(var5));
         }
      }

      this.func_146283_a(var4, var2, var3);
   }

   protected void drawCreativeTabHoveringText(String var1, int var2, int var3) {
      this.func_146283_a(Arrays.asList(new String[]{var1}), var2, var3);
   }

   protected void func_146283_a(List var1, int var2, int var3) {
      if(!var1.isEmpty()) {
         GL11.glDisable('\u803a');
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         int var4 = 0;
         Iterator var5 = var1.iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            int var7 = this.fontRendererObj.getStringWidth(var6);
            if(var7 > var4) {
               var4 = var7;
            }
         }

         int var14 = var2 + 12;
         int var15 = var3 - 12;
         int var8 = 8;
         if(var1.size() > 1) {
            var8 += 2 + (var1.size() - 1) * 10;
         }

         if(var14 + var4 > this.width) {
            var14 -= 28 + var4;
         }

         if(var15 + var8 + 6 > this.height) {
            var15 = this.height - var8 - 6;
         }

         super.zLevel = 300.0F;
         itemRender.zLevel = 300.0F;
         int var9 = -267386864;
         this.drawGradientRect(var14 - 3, var15 - 4, var14 + var4 + 3, var15 - 3, var9, var9);
         this.drawGradientRect(var14 - 3, var15 + var8 + 3, var14 + var4 + 3, var15 + var8 + 4, var9, var9);
         this.drawGradientRect(var14 - 3, var15 - 3, var14 + var4 + 3, var15 + var8 + 3, var9, var9);
         this.drawGradientRect(var14 - 4, var15 - 3, var14 - 3, var15 + var8 + 3, var9, var9);
         this.drawGradientRect(var14 + var4 + 3, var15 - 3, var14 + var4 + 4, var15 + var8 + 3, var9, var9);
         int var10 = 1347420415;
         int var11 = (var10 & 16711422) >> 1 | var10 & -16777216;
         this.drawGradientRect(var14 - 3, var15 - 3 + 1, var14 - 3 + 1, var15 + var8 + 3 - 1, var10, var11);
         this.drawGradientRect(var14 + var4 + 2, var15 - 3 + 1, var14 + var4 + 3, var15 + var8 + 3 - 1, var10, var11);
         this.drawGradientRect(var14 - 3, var15 - 3, var14 + var4 + 3, var15 - 3 + 1, var10, var10);
         this.drawGradientRect(var14 - 3, var15 + var8 + 2, var14 + var4 + 3, var15 + var8 + 3, var11, var11);

         for(int var12 = 0; var12 < var1.size(); ++var12) {
            String var13 = (String)var1.get(var12);
            this.fontRendererObj.drawStringWithShadow(var13, var14, var15, -1);
            if(var12 == 0) {
               var15 += 2;
            }

            var15 += 10;
         }

         super.zLevel = 0.0F;
         itemRender.zLevel = 0.0F;
         GL11.glEnable(2896);
         GL11.glEnable(2929);
         RenderHelper.enableStandardItemLighting();
         GL11.glEnable('\u803a');
      }
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      if(var3 == 0) {
         for(int var4 = 0; var4 < this.buttonList.size(); ++var4) {
            GuiButton var5 = (GuiButton)this.buttonList.get(var4);
            if(var5.mousePressed(this.mc, var1, var2)) {
               this.selectedButton = var5;
               var5.func_146113_a(this.mc.getSoundHandler());
               this.actionPerformed(var5);
            }
         }
      }

   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      if(this.selectedButton != null && var3 == 0) {
         this.selectedButton.mouseReleased(var1, var2);
         this.selectedButton = null;
      }

   }

   protected void mouseClickMove(int var1, int var2, int var3, long var4) {}

   protected void actionPerformed(GuiButton var1) {}

   public void setWorldAndResolution(Minecraft var1, int var2, int var3) {
      this.mc = var1;
      this.fontRendererObj = var1.fontRenderer;
      this.width = var2;
      this.height = var3;
      this.buttonList.clear();
      this.initGui();
   }

   public void initGui() {}

   public void handleInput() {
      if(Mouse.isCreated()) {
         while(Mouse.next()) {
            this.handleMouseInput();
         }
      }

      if(Keyboard.isCreated()) {
         while(Keyboard.next()) {
            this.handleKeyboardInput();
         }
      }

   }

   public void handleMouseInput() {
      int var1 = Mouse.getEventX() * this.width / this.mc.displayWidth;
      int var2 = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
      int var3 = Mouse.getEventButton();
      if(Mouse.getEventButtonState()) {
         if(this.mc.gameSettings.touchscreen && this.field_146298_h++ > 0) {
            return;
         }

         this.eventButton = var3;
         this.lastMouseEvent = Minecraft.getSystemTime();
         this.mouseClicked(var1, var2, this.eventButton);
      } else if(var3 != -1) {
         if(this.mc.gameSettings.touchscreen && --this.field_146298_h > 0) {
            return;
         }

         this.eventButton = -1;
         this.mouseMovedOrUp(var1, var2, var3);
      } else if(this.eventButton != -1 && this.lastMouseEvent > 0L) {
         long var4 = Minecraft.getSystemTime() - this.lastMouseEvent;
         this.mouseClickMove(var1, var2, this.eventButton, var4);
      }

   }

   public void handleKeyboardInput() {
      if(Keyboard.getEventKeyState()) {
         this.keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());
      }

      this.mc.func_152348_aa();
   }

   public void updateScreen() {}

   public void onGuiClosed() {}

   public void drawDefaultBackground() {
      this.drawWorldBackground(0);
   }

   public void drawWorldBackground(int var1) {
      if(this.mc.theWorld != null) {
         this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
      } else {
         this.drawBackground(var1);
      }

   }

   public void drawBackground(int var1) {
      GL11.glDisable(2896);
      GL11.glDisable(2912);
      Tessellator var2 = Tessellator.instance;
      this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float var3 = 32.0F;
      var2.startDrawingQuads();
      var2.setColorOpaque_I(4210752);
      var2.addVertexWithUV(0.0D, (double)this.height, 0.0D, 0.0D, (double)((float)this.height / var3 + (float)var1));
      var2.addVertexWithUV((double)this.width, (double)this.height, 0.0D, (double)((float)this.width / var3), (double)((float)this.height / var3 + (float)var1));
      var2.addVertexWithUV((double)this.width, 0.0D, 0.0D, (double)((float)this.width / var3), (double)var1);
      var2.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, (double)var1);
      var2.draw();
   }

   public boolean doesGuiPauseGame() {
      return true;
   }

   public void confirmClicked(boolean var1, int var2) {}

   public static boolean isCtrlKeyDown() {
      return Minecraft.isRunningOnMac?Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220):Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
   }

   public static boolean isShiftKeyDown() {
      return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
   }

}
