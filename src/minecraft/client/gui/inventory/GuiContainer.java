package net.minecraft.client.gui.inventory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public abstract class GuiContainer extends GuiScreen {

   protected static final ResourceLocation field_147001_a = new ResourceLocation("textures/gui/container/inventory.png");
   protected int xSize = 176;
   protected int ySize = 166;
   public Container inventorySlots;
   protected int guiLeft;
   protected int guiTop;
   private Slot theSlot;
   private Slot clickedSlot;
   private boolean isRightMouseClick;
   private ItemStack draggedStack;
   private int field_147011_y;
   private int field_147010_z;
   private Slot returningStackDestSlot;
   private long returningStackTime;
   private ItemStack returningStack;
   private Slot field_146985_D;
   private long field_146986_E;
   protected final Set field_147008_s = new HashSet();
   protected boolean field_147007_t;
   private int field_146987_F;
   private int field_146988_G;
   private boolean field_146995_H;
   private int field_146996_I;
   private long field_146997_J;
   private Slot field_146998_K;
   private int field_146992_L;
   private boolean field_146993_M;
   private ItemStack field_146994_N;


   public GuiContainer(Container var1) {
      this.inventorySlots = var1;
      this.field_146995_H = true;
   }

   public void initGui() {
      super.initGui();
      super.mc.thePlayer.openContainer = this.inventorySlots;
      this.guiLeft = (super.width - this.xSize) / 2;
      this.guiTop = (super.height - this.ySize) / 2;
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      int var4 = this.guiLeft;
      int var5 = this.guiTop;
      this.drawGuiContainerBackgroundLayer(var3, var1, var2);
      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      super.drawScreen(var1, var2, var3);
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var4, (float)var5, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('\u803a');
      this.theSlot = null;
      short var6 = 240;
      short var7 = 240;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

      int var11;
      for(int var8 = 0; var8 < this.inventorySlots.inventorySlots.size(); ++var8) {
         Slot var9 = (Slot)this.inventorySlots.inventorySlots.get(var8);
         this.func_146977_a(var9);
         if(this.isMouseOverSlot(var9, var1, var2) && var9.func_111238_b()) {
            this.theSlot = var9;
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            int var10 = var9.xDisplayPosition;
            var11 = var9.yDisplayPosition;
            GL11.glColorMask(true, true, true, false);
            this.drawGradientRect(var10, var11, var10 + 16, var11 + 16, -2130706433, -2130706433);
            GL11.glColorMask(true, true, true, true);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
         }
      }

      this.drawGuiContainerForegroundLayer(var1, var2);
      InventoryPlayer var15 = super.mc.thePlayer.inventory;
      ItemStack var16 = this.draggedStack == null?var15.getItemStack():this.draggedStack;
      if(var16 != null) {
         byte var17 = 8;
         var11 = this.draggedStack == null?8:16;
         String var12 = null;
         if(this.draggedStack != null && this.isRightMouseClick) {
            var16 = var16.copy();
            var16.stackSize = MathHelper.ceiling_float_int((float)var16.stackSize / 2.0F);
         } else if(this.field_147007_t && this.field_147008_s.size() > 1) {
            var16 = var16.copy();
            var16.stackSize = this.field_146996_I;
            if(var16.stackSize == 0) {
               var12 = "" + EnumChatFormatting.YELLOW + "0";
            }
         }

         this.drawItemStack(var16, var1 - var4 - var17, var2 - var5 - var11, var12);
      }

      if(this.returningStack != null) {
         float var18 = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;
         if(var18 >= 1.0F) {
            var18 = 1.0F;
            this.returningStack = null;
         }

         var11 = this.returningStackDestSlot.xDisplayPosition - this.field_147011_y;
         int var20 = this.returningStackDestSlot.yDisplayPosition - this.field_147010_z;
         int var13 = this.field_147011_y + (int)((float)var11 * var18);
         int var14 = this.field_147010_z + (int)((float)var20 * var18);
         this.drawItemStack(this.returningStack, var13, var14, (String)null);
      }

      GL11.glPopMatrix();
      if(var15.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack()) {
         ItemStack var19 = this.theSlot.getStack();
         this.renderToolTip(var19, var1, var2);
      }

      GL11.glEnable(2896);
      GL11.glEnable(2929);
      RenderHelper.enableStandardItemLighting();
   }

   private void drawItemStack(ItemStack var1, int var2, int var3, String var4) {
      GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      super.zLevel = 200.0F;
      GuiScreen.itemRender.zLevel = 200.0F;
      GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var1, var2, var3);
      GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var1, var2, var3 - (this.draggedStack == null?0:8), var4);
      super.zLevel = 0.0F;
      GuiScreen.itemRender.zLevel = 0.0F;
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {}

   protected abstract void drawGuiContainerBackgroundLayer(float var1, int var2, int var3);

   private void func_146977_a(Slot var1) {
      int var2 = var1.xDisplayPosition;
      int var3 = var1.yDisplayPosition;
      ItemStack var4 = var1.getStack();
      boolean var5 = false;
      boolean var6 = var1 == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
      ItemStack var7 = super.mc.thePlayer.inventory.getItemStack();
      String var8 = null;
      if(var1 == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && var4 != null) {
         var4 = var4.copy();
         var4.stackSize /= 2;
      } else if(this.field_147007_t && this.field_147008_s.contains(var1) && var7 != null) {
         if(this.field_147008_s.size() == 1) {
            return;
         }

         if(Container.func_94527_a(var1, var7, true) && this.inventorySlots.canDragIntoSlot(var1)) {
            var4 = var7.copy();
            var5 = true;
            Container.func_94525_a(this.field_147008_s, this.field_146987_F, var4, var1.getStack() == null?0:var1.getStack().stackSize);
            if(var4.stackSize > var4.getMaxStackSize()) {
               var8 = EnumChatFormatting.YELLOW + "" + var4.getMaxStackSize();
               var4.stackSize = var4.getMaxStackSize();
            }

            if(var4.stackSize > var1.getSlotStackLimit()) {
               var8 = EnumChatFormatting.YELLOW + "" + var1.getSlotStackLimit();
               var4.stackSize = var1.getSlotStackLimit();
            }
         } else {
            this.field_147008_s.remove(var1);
            this.func_146980_g();
         }
      }

      super.zLevel = 100.0F;
      GuiScreen.itemRender.zLevel = 100.0F;
      if(var4 == null) {
         IIcon var9 = var1.getBackgroundIconIndex();
         if(var9 != null) {
            GL11.glDisable(2896);
            super.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
            this.drawTexturedModelRectFromIcon(var2, var3, var9, 16, 16);
            GL11.glEnable(2896);
            var6 = true;
         }
      }

      if(!var6) {
         if(var5) {
            drawRect(var2, var3, var2 + 16, var3 + 16, -2130706433);
         }

         GL11.glEnable(2929);
         GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var4, var2, var3);
         GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var4, var2, var3, var8);
      }

      GuiScreen.itemRender.zLevel = 0.0F;
      super.zLevel = 0.0F;
   }

   private void func_146980_g() {
      ItemStack var1 = super.mc.thePlayer.inventory.getItemStack();
      if(var1 != null && this.field_147007_t) {
         this.field_146996_I = var1.stackSize;

         ItemStack var4;
         int var5;
         for(Iterator var2 = this.field_147008_s.iterator(); var2.hasNext(); this.field_146996_I -= var4.stackSize - var5) {
            Slot var3 = (Slot)var2.next();
            var4 = var1.copy();
            var5 = var3.getStack() == null?0:var3.getStack().stackSize;
            Container.func_94525_a(this.field_147008_s, this.field_146987_F, var4, var5);
            if(var4.stackSize > var4.getMaxStackSize()) {
               var4.stackSize = var4.getMaxStackSize();
            }

            if(var4.stackSize > var3.getSlotStackLimit()) {
               var4.stackSize = var3.getSlotStackLimit();
            }
         }

      }
   }

   private Slot getSlotAtPosition(int var1, int var2) {
      for(int var3 = 0; var3 < this.inventorySlots.inventorySlots.size(); ++var3) {
         Slot var4 = (Slot)this.inventorySlots.inventorySlots.get(var3);
         if(this.isMouseOverSlot(var4, var1, var2)) {
            return var4;
         }
      }

      return null;
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      boolean var4 = var3 == super.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100;
      Slot var5 = this.getSlotAtPosition(var1, var2);
      long var6 = Minecraft.getSystemTime();
      this.field_146993_M = this.field_146998_K == var5 && var6 - this.field_146997_J < 250L && this.field_146992_L == var3;
      this.field_146995_H = false;
      if(var3 == 0 || var3 == 1 || var4) {
         int var8 = this.guiLeft;
         int var9 = this.guiTop;
         boolean var10 = var1 < var8 || var2 < var9 || var1 >= var8 + this.xSize || var2 >= var9 + this.ySize;
         int var11 = -1;
         if(var5 != null) {
            var11 = var5.slotNumber;
         }

         if(var10) {
            var11 = -999;
         }

         if(super.mc.gameSettings.touchscreen && var10 && super.mc.thePlayer.inventory.getItemStack() == null) {
            super.mc.displayGuiScreen((GuiScreen)null);
            return;
         }

         if(var11 != -1) {
            if(super.mc.gameSettings.touchscreen) {
               if(var5 != null && var5.getHasStack()) {
                  this.clickedSlot = var5;
                  this.draggedStack = null;
                  this.isRightMouseClick = var3 == 1;
               } else {
                  this.clickedSlot = null;
               }
            } else if(!this.field_147007_t) {
               if(super.mc.thePlayer.inventory.getItemStack() == null) {
                  if(var3 == super.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                     this.handleMouseClick(var5, var11, var3, 3);
                  } else {
                     boolean var12 = var11 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                     byte var13 = 0;
                     if(var12) {
                        this.field_146994_N = var5 != null && var5.getHasStack()?var5.getStack():null;
                        var13 = 1;
                     } else if(var11 == -999) {
                        var13 = 4;
                     }

                     this.handleMouseClick(var5, var11, var3, var13);
                  }

                  this.field_146995_H = true;
               } else {
                  this.field_147007_t = true;
                  this.field_146988_G = var3;
                  this.field_147008_s.clear();
                  if(var3 == 0) {
                     this.field_146987_F = 0;
                  } else if(var3 == 1) {
                     this.field_146987_F = 1;
                  }
               }
            }
         }
      }

      this.field_146998_K = var5;
      this.field_146997_J = var6;
      this.field_146992_L = var3;
   }

   protected void mouseClickMove(int var1, int var2, int var3, long var4) {
      Slot var6 = this.getSlotAtPosition(var1, var2);
      ItemStack var7 = super.mc.thePlayer.inventory.getItemStack();
      if(this.clickedSlot != null && super.mc.gameSettings.touchscreen) {
         if(var3 == 0 || var3 == 1) {
            if(this.draggedStack == null) {
               if(var6 != this.clickedSlot) {
                  this.draggedStack = this.clickedSlot.getStack().copy();
               }
            } else if(this.draggedStack.stackSize > 1 && var6 != null && Container.func_94527_a(var6, this.draggedStack, false)) {
               long var8 = Minecraft.getSystemTime();
               if(this.field_146985_D == var6) {
                  if(var8 - this.field_146986_E > 500L) {
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                     this.handleMouseClick(var6, var6.slotNumber, 1, 0);
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                     this.field_146986_E = var8 + 750L;
                     --this.draggedStack.stackSize;
                  }
               } else {
                  this.field_146985_D = var6;
                  this.field_146986_E = var8;
               }
            }
         }
      } else if(this.field_147007_t && var6 != null && var7 != null && var7.stackSize > this.field_147008_s.size() && Container.func_94527_a(var6, var7, true) && var6.isItemValid(var7) && this.inventorySlots.canDragIntoSlot(var6)) {
         this.field_147008_s.add(var6);
         this.func_146980_g();
      }

   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      Slot var4 = this.getSlotAtPosition(var1, var2);
      int var5 = this.guiLeft;
      int var6 = this.guiTop;
      boolean var7 = var1 < var5 || var2 < var6 || var1 >= var5 + this.xSize || var2 >= var6 + this.ySize;
      int var8 = -1;
      if(var4 != null) {
         var8 = var4.slotNumber;
      }

      if(var7) {
         var8 = -999;
      }

      Slot var10;
      Iterator var11;
      if(this.field_146993_M && var4 != null && var3 == 0 && this.inventorySlots.func_94530_a((ItemStack)null, var4)) {
         if(isShiftKeyDown()) {
            if(var4 != null && var4.inventory != null && this.field_146994_N != null) {
               var11 = this.inventorySlots.inventorySlots.iterator();

               while(var11.hasNext()) {
                  var10 = (Slot)var11.next();
                  if(var10 != null && var10.canTakeStack(super.mc.thePlayer) && var10.getHasStack() && var10.inventory == var4.inventory && Container.func_94527_a(var10, this.field_146994_N, true)) {
                     this.handleMouseClick(var10, var10.slotNumber, var3, 1);
                  }
               }
            }
         } else {
            this.handleMouseClick(var4, var8, var3, 6);
         }

         this.field_146993_M = false;
         this.field_146997_J = 0L;
      } else {
         if(this.field_147007_t && this.field_146988_G != var3) {
            this.field_147007_t = false;
            this.field_147008_s.clear();
            this.field_146995_H = true;
            return;
         }

         if(this.field_146995_H) {
            this.field_146995_H = false;
            return;
         }

         boolean var9;
         if(this.clickedSlot != null && super.mc.gameSettings.touchscreen) {
            if(var3 == 0 || var3 == 1) {
               if(this.draggedStack == null && var4 != this.clickedSlot) {
                  this.draggedStack = this.clickedSlot.getStack();
               }

               var9 = Container.func_94527_a(var4, this.draggedStack, false);
               if(var8 != -1 && this.draggedStack != null && var9) {
                  this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, var3, 0);
                  this.handleMouseClick(var4, var8, 0, 0);
                  if(super.mc.thePlayer.inventory.getItemStack() != null) {
                     this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, var3, 0);
                     this.field_147011_y = var1 - var5;
                     this.field_147010_z = var2 - var6;
                     this.returningStackDestSlot = this.clickedSlot;
                     this.returningStack = this.draggedStack;
                     this.returningStackTime = Minecraft.getSystemTime();
                  } else {
                     this.returningStack = null;
                  }
               } else if(this.draggedStack != null) {
                  this.field_147011_y = var1 - var5;
                  this.field_147010_z = var2 - var6;
                  this.returningStackDestSlot = this.clickedSlot;
                  this.returningStack = this.draggedStack;
                  this.returningStackTime = Minecraft.getSystemTime();
               }

               this.draggedStack = null;
               this.clickedSlot = null;
            }
         } else if(this.field_147007_t && !this.field_147008_s.isEmpty()) {
            this.handleMouseClick((Slot)null, -999, Container.func_94534_d(0, this.field_146987_F), 5);
            var11 = this.field_147008_s.iterator();

            while(var11.hasNext()) {
               var10 = (Slot)var11.next();
               this.handleMouseClick(var10, var10.slotNumber, Container.func_94534_d(1, this.field_146987_F), 5);
            }

            this.handleMouseClick((Slot)null, -999, Container.func_94534_d(2, this.field_146987_F), 5);
         } else if(super.mc.thePlayer.inventory.getItemStack() != null) {
            if(var3 == super.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
               this.handleMouseClick(var4, var8, var3, 3);
            } else {
               var9 = var8 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
               if(var9) {
                  this.field_146994_N = var4 != null && var4.getHasStack()?var4.getStack():null;
               }

               this.handleMouseClick(var4, var8, var3, var9?1:0);
            }
         }
      }

      if(super.mc.thePlayer.inventory.getItemStack() == null) {
         this.field_146997_J = 0L;
      }

      this.field_147007_t = false;
   }

   private boolean isMouseOverSlot(Slot var1, int var2, int var3) {
      return this.func_146978_c(var1.xDisplayPosition, var1.yDisplayPosition, 16, 16, var2, var3);
   }

   protected boolean func_146978_c(int var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = this.guiLeft;
      int var8 = this.guiTop;
      var5 -= var7;
      var6 -= var8;
      return var5 >= var1 - 1 && var5 < var1 + var3 + 1 && var6 >= var2 - 1 && var6 < var2 + var4 + 1;
   }

   protected void handleMouseClick(Slot var1, int var2, int var3, int var4) {
      if(var1 != null) {
         var2 = var1.slotNumber;
      }

      super.mc.playerController.windowClick(this.inventorySlots.windowId, var2, var3, var4, super.mc.thePlayer);
   }

   protected void keyTyped(char var1, int var2) {
      if(var2 == 1 || var2 == super.mc.gameSettings.keyBindInventory.getKeyCode()) {
         super.mc.thePlayer.closeScreen();
      }

      this.checkHotbarKeys(var2);
      if(this.theSlot != null && this.theSlot.getHasStack()) {
         if(var2 == super.mc.gameSettings.keyBindPickBlock.getKeyCode()) {
            this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
         } else if(var2 == super.mc.gameSettings.keyBindDrop.getKeyCode()) {
            this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown()?1:0, 4);
         }
      }

   }

   protected boolean checkHotbarKeys(int var1) {
      if(super.mc.thePlayer.inventory.getItemStack() == null && this.theSlot != null) {
         for(int var2 = 0; var2 < 9; ++var2) {
            if(var1 == super.mc.gameSettings.keyBindsHotbar[var2].getKeyCode()) {
               this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, var2, 2);
               return true;
            }
         }
      }

      return false;
   }

   public void onGuiClosed() {
      if(super.mc.thePlayer != null) {
         this.inventorySlots.onContainerClosed(super.mc.thePlayer);
      }
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      if(!super.mc.thePlayer.isEntityAlive() || super.mc.thePlayer.isDead) {
         super.mc.thePlayer.closeScreen();
      }

   }

}
