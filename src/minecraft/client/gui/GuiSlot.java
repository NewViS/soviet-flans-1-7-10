package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiSlot {

   private final Minecraft mc;
   protected int width;
   private int height;
   protected int top;
   protected int bottom;
   protected int right;
   protected int left;
   protected final int slotHeight;
   private int scrollUpButtonID;
   private int scrollDownButtonID;
   protected int mouseX;
   protected int mouseY;
   protected boolean field_148163_i = true;
   private float initialClickY = -2.0F;
   private float scrollMultiplier;
   private float amountScrolled;
   private int selectedElement = -1;
   private long lastClicked;
   private boolean showSelectionBox = true;
   private boolean hasListHeader;
   protected int headerPadding;
   private boolean field_148164_v = true;


   public GuiSlot(Minecraft var1, int var2, int var3, int var4, int var5, int var6) {
      this.mc = var1;
      this.width = var2;
      this.height = var3;
      this.top = var4;
      this.bottom = var5;
      this.slotHeight = var6;
      this.left = 0;
      this.right = var2;
   }

   public void func_148122_a(int var1, int var2, int var3, int var4) {
      this.width = var1;
      this.height = var2;
      this.top = var3;
      this.bottom = var4;
      this.left = 0;
      this.right = var1;
   }

   public void setShowSelectionBox(boolean var1) {
      this.showSelectionBox = var1;
   }

   protected void setHasListHeader(boolean var1, int var2) {
      this.hasListHeader = var1;
      this.headerPadding = var2;
      if(!var1) {
         this.headerPadding = 0;
      }

   }

   protected abstract int getSize();

   protected abstract void elementClicked(int var1, boolean var2, int var3, int var4);

   protected abstract boolean isSelected(int var1);

   protected int getContentHeight() {
      return this.getSize() * this.slotHeight + this.headerPadding;
   }

   protected abstract void drawBackground();

   protected abstract void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5, int var6, int var7);

   protected void drawListHeader(int var1, int var2, Tessellator var3) {}

   protected void func_148132_a(int var1, int var2) {}

   protected void func_148142_b(int var1, int var2) {}

   public int func_148124_c(int var1, int var2) {
      int var3 = this.left + this.width / 2 - this.getListWidth() / 2;
      int var4 = this.left + this.width / 2 + this.getListWidth() / 2;
      int var5 = var2 - this.top - this.headerPadding + (int)this.amountScrolled - 4;
      int var6 = var5 / this.slotHeight;
      return var1 < this.getScrollBarX() && var1 >= var3 && var1 <= var4 && var6 >= 0 && var5 >= 0 && var6 < this.getSize()?var6:-1;
   }

   public void registerScrollButtons(int var1, int var2) {
      this.scrollUpButtonID = var1;
      this.scrollDownButtonID = var2;
   }

   private void bindAmountScrolled() {
      int var1 = this.func_148135_f();
      if(var1 < 0) {
         var1 /= 2;
      }

      if(!this.field_148163_i && var1 < 0) {
         var1 = 0;
      }

      if(this.amountScrolled < 0.0F) {
         this.amountScrolled = 0.0F;
      }

      if(this.amountScrolled > (float)var1) {
         this.amountScrolled = (float)var1;
      }

   }

   public int func_148135_f() {
      return this.getContentHeight() - (this.bottom - this.top - 4);
   }

   public int getAmountScrolled() {
      return (int)this.amountScrolled;
   }

   public boolean func_148141_e(int var1) {
      return var1 >= this.top && var1 <= this.bottom;
   }

   public void scrollBy(int var1) {
      this.amountScrolled += (float)var1;
      this.bindAmountScrolled();
      this.initialClickY = -2.0F;
   }

   public void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == this.scrollUpButtonID) {
            this.amountScrolled -= (float)(this.slotHeight * 2 / 3);
            this.initialClickY = -2.0F;
            this.bindAmountScrolled();
         } else if(var1.id == this.scrollDownButtonID) {
            this.amountScrolled += (float)(this.slotHeight * 2 / 3);
            this.initialClickY = -2.0F;
            this.bindAmountScrolled();
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.mouseX = var1;
      this.mouseY = var2;
      this.drawBackground();
      int var4 = this.getSize();
      int var5 = this.getScrollBarX();
      int var6 = var5 + 6;
      int var9;
      int var10;
      int var13;
      int var19;
      if(var1 > this.left && var1 < this.right && var2 > this.top && var2 < this.bottom) {
         if(Mouse.isButtonDown(0) && this.func_148125_i()) {
            if(this.initialClickY == -1.0F) {
               boolean var16 = true;
               if(var2 >= this.top && var2 <= this.bottom) {
                  int var8 = this.width / 2 - this.getListWidth() / 2;
                  var9 = this.width / 2 + this.getListWidth() / 2;
                  var10 = var2 - this.top - this.headerPadding + (int)this.amountScrolled - 4;
                  int var11 = var10 / this.slotHeight;
                  if(var1 >= var8 && var1 <= var9 && var11 >= 0 && var10 >= 0 && var11 < var4) {
                     boolean var12 = var11 == this.selectedElement && Minecraft.getSystemTime() - this.lastClicked < 250L;
                     this.elementClicked(var11, var12, var1, var2);
                     this.selectedElement = var11;
                     this.lastClicked = Minecraft.getSystemTime();
                  } else if(var1 >= var8 && var1 <= var9 && var10 < 0) {
                     this.func_148132_a(var1 - var8, var2 - this.top + (int)this.amountScrolled - 4);
                     var16 = false;
                  }

                  if(var1 >= var5 && var1 <= var6) {
                     this.scrollMultiplier = -1.0F;
                     var19 = this.func_148135_f();
                     if(var19 < 1) {
                        var19 = 1;
                     }

                     var13 = (int)((float)((this.bottom - this.top) * (this.bottom - this.top)) / (float)this.getContentHeight());
                     if(var13 < 32) {
                        var13 = 32;
                     }

                     if(var13 > this.bottom - this.top - 8) {
                        var13 = this.bottom - this.top - 8;
                     }

                     this.scrollMultiplier /= (float)(this.bottom - this.top - var13) / (float)var19;
                  } else {
                     this.scrollMultiplier = 1.0F;
                  }

                  if(var16) {
                     this.initialClickY = (float)var2;
                  } else {
                     this.initialClickY = -2.0F;
                  }
               } else {
                  this.initialClickY = -2.0F;
               }
            } else if(this.initialClickY >= 0.0F) {
               this.amountScrolled -= ((float)var2 - this.initialClickY) * this.scrollMultiplier;
               this.initialClickY = (float)var2;
            }
         } else {
            for(; !this.mc.gameSettings.touchscreen && Mouse.next(); this.mc.currentScreen.handleMouseInput()) {
               int var7 = Mouse.getEventDWheel();
               if(var7 != 0) {
                  if(var7 > 0) {
                     var7 = -1;
                  } else if(var7 < 0) {
                     var7 = 1;
                  }

                  this.amountScrolled += (float)(var7 * this.slotHeight / 2);
               }
            }

            this.initialClickY = -1.0F;
         }
      }

      this.bindAmountScrolled();
      GL11.glDisable(2896);
      GL11.glDisable(2912);
      Tessellator var17 = Tessellator.instance;
      this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float var15 = 32.0F;
      var17.startDrawingQuads();
      var17.setColorOpaque_I(2105376);
      var17.addVertexWithUV((double)this.left, (double)this.bottom, 0.0D, (double)((float)this.left / var15), (double)((float)(this.bottom + (int)this.amountScrolled) / var15));
      var17.addVertexWithUV((double)this.right, (double)this.bottom, 0.0D, (double)((float)this.right / var15), (double)((float)(this.bottom + (int)this.amountScrolled) / var15));
      var17.addVertexWithUV((double)this.right, (double)this.top, 0.0D, (double)((float)this.right / var15), (double)((float)(this.top + (int)this.amountScrolled) / var15));
      var17.addVertexWithUV((double)this.left, (double)this.top, 0.0D, (double)((float)this.left / var15), (double)((float)(this.top + (int)this.amountScrolled) / var15));
      var17.draw();
      var9 = this.left + this.width / 2 - this.getListWidth() / 2 + 2;
      var10 = this.top + 4 - (int)this.amountScrolled;
      if(this.hasListHeader) {
         this.drawListHeader(var9, var10, var17);
      }

      this.drawSelectionBox(var9, var10, var1, var2);
      GL11.glDisable(2929);
      byte var18 = 4;
      this.overlayBackground(0, this.top, 255, 255);
      this.overlayBackground(this.bottom, this.height, 255, 255);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 0, 1);
      GL11.glDisable(3008);
      GL11.glShadeModel(7425);
      GL11.glDisable(3553);
      var17.startDrawingQuads();
      var17.setColorRGBA_I(0, 0);
      var17.addVertexWithUV((double)this.left, (double)(this.top + var18), 0.0D, 0.0D, 1.0D);
      var17.addVertexWithUV((double)this.right, (double)(this.top + var18), 0.0D, 1.0D, 1.0D);
      var17.setColorRGBA_I(0, 255);
      var17.addVertexWithUV((double)this.right, (double)this.top, 0.0D, 1.0D, 0.0D);
      var17.addVertexWithUV((double)this.left, (double)this.top, 0.0D, 0.0D, 0.0D);
      var17.draw();
      var17.startDrawingQuads();
      var17.setColorRGBA_I(0, 255);
      var17.addVertexWithUV((double)this.left, (double)this.bottom, 0.0D, 0.0D, 1.0D);
      var17.addVertexWithUV((double)this.right, (double)this.bottom, 0.0D, 1.0D, 1.0D);
      var17.setColorRGBA_I(0, 0);
      var17.addVertexWithUV((double)this.right, (double)(this.bottom - var18), 0.0D, 1.0D, 0.0D);
      var17.addVertexWithUV((double)this.left, (double)(this.bottom - var18), 0.0D, 0.0D, 0.0D);
      var17.draw();
      var19 = this.func_148135_f();
      if(var19 > 0) {
         var13 = (this.bottom - this.top) * (this.bottom - this.top) / this.getContentHeight();
         if(var13 < 32) {
            var13 = 32;
         }

         if(var13 > this.bottom - this.top - 8) {
            var13 = this.bottom - this.top - 8;
         }

         int var14 = (int)this.amountScrolled * (this.bottom - this.top - var13) / var19 + this.top;
         if(var14 < this.top) {
            var14 = this.top;
         }

         var17.startDrawingQuads();
         var17.setColorRGBA_I(0, 255);
         var17.addVertexWithUV((double)var5, (double)this.bottom, 0.0D, 0.0D, 1.0D);
         var17.addVertexWithUV((double)var6, (double)this.bottom, 0.0D, 1.0D, 1.0D);
         var17.addVertexWithUV((double)var6, (double)this.top, 0.0D, 1.0D, 0.0D);
         var17.addVertexWithUV((double)var5, (double)this.top, 0.0D, 0.0D, 0.0D);
         var17.draw();
         var17.startDrawingQuads();
         var17.setColorRGBA_I(8421504, 255);
         var17.addVertexWithUV((double)var5, (double)(var14 + var13), 0.0D, 0.0D, 1.0D);
         var17.addVertexWithUV((double)var6, (double)(var14 + var13), 0.0D, 1.0D, 1.0D);
         var17.addVertexWithUV((double)var6, (double)var14, 0.0D, 1.0D, 0.0D);
         var17.addVertexWithUV((double)var5, (double)var14, 0.0D, 0.0D, 0.0D);
         var17.draw();
         var17.startDrawingQuads();
         var17.setColorRGBA_I(12632256, 255);
         var17.addVertexWithUV((double)var5, (double)(var14 + var13 - 1), 0.0D, 0.0D, 1.0D);
         var17.addVertexWithUV((double)(var6 - 1), (double)(var14 + var13 - 1), 0.0D, 1.0D, 1.0D);
         var17.addVertexWithUV((double)(var6 - 1), (double)var14, 0.0D, 1.0D, 0.0D);
         var17.addVertexWithUV((double)var5, (double)var14, 0.0D, 0.0D, 0.0D);
         var17.draw();
      }

      this.func_148142_b(var1, var2);
      GL11.glEnable(3553);
      GL11.glShadeModel(7424);
      GL11.glEnable(3008);
      GL11.glDisable(3042);
   }

   public void func_148143_b(boolean var1) {
      this.field_148164_v = var1;
   }

   public boolean func_148125_i() {
      return this.field_148164_v;
   }

   public int getListWidth() {
      return 220;
   }

   protected void drawSelectionBox(int var1, int var2, int var3, int var4) {
      int var5 = this.getSize();
      Tessellator var6 = Tessellator.instance;

      for(int var7 = 0; var7 < var5; ++var7) {
         int var8 = var2 + var7 * this.slotHeight + this.headerPadding;
         int var9 = this.slotHeight - 4;
         if(var8 <= this.bottom && var8 + var9 >= this.top) {
            if(this.showSelectionBox && this.isSelected(var7)) {
               int var10 = this.left + (this.width / 2 - this.getListWidth() / 2);
               int var11 = this.left + this.width / 2 + this.getListWidth() / 2;
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(3553);
               var6.startDrawingQuads();
               var6.setColorOpaque_I(8421504);
               var6.addVertexWithUV((double)var10, (double)(var8 + var9 + 2), 0.0D, 0.0D, 1.0D);
               var6.addVertexWithUV((double)var11, (double)(var8 + var9 + 2), 0.0D, 1.0D, 1.0D);
               var6.addVertexWithUV((double)var11, (double)(var8 - 2), 0.0D, 1.0D, 0.0D);
               var6.addVertexWithUV((double)var10, (double)(var8 - 2), 0.0D, 0.0D, 0.0D);
               var6.setColorOpaque_I(0);
               var6.addVertexWithUV((double)(var10 + 1), (double)(var8 + var9 + 1), 0.0D, 0.0D, 1.0D);
               var6.addVertexWithUV((double)(var11 - 1), (double)(var8 + var9 + 1), 0.0D, 1.0D, 1.0D);
               var6.addVertexWithUV((double)(var11 - 1), (double)(var8 - 1), 0.0D, 1.0D, 0.0D);
               var6.addVertexWithUV((double)(var10 + 1), (double)(var8 - 1), 0.0D, 0.0D, 0.0D);
               var6.draw();
               GL11.glEnable(3553);
            }

            this.drawSlot(var7, var1, var8, var9, var6, var3, var4);
         }
      }

   }

   protected int getScrollBarX() {
      return this.width / 2 + 124;
   }

   private void overlayBackground(int var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.instance;
      this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float var6 = 32.0F;
      var5.startDrawingQuads();
      var5.setColorRGBA_I(4210752, var4);
      var5.addVertexWithUV((double)this.left, (double)var2, 0.0D, 0.0D, (double)((float)var2 / var6));
      var5.addVertexWithUV((double)(this.left + this.width), (double)var2, 0.0D, (double)((float)this.width / var6), (double)((float)var2 / var6));
      var5.setColorRGBA_I(4210752, var3);
      var5.addVertexWithUV((double)(this.left + this.width), (double)var1, 0.0D, (double)((float)this.width / var6), (double)((float)var1 / var6));
      var5.addVertexWithUV((double)this.left, (double)var1, 0.0D, 0.0D, (double)((float)var1 / var6));
      var5.draw();
   }

   public void setSlotXBoundsFromLeft(int var1) {
      this.left = var1;
      this.right = var1 + this.width;
   }

   public int getSlotHeight() {
      return this.slotHeight;
   }
}
