package net.minecraft.client.gui.inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.client.gui.inventory.GuiContainerCreative$ContainerCreative;
import net.minecraft.client.gui.inventory.GuiContainerCreative$CreativeSlot;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiContainerCreative extends InventoryEffectRenderer {

   private static final ResourceLocation field_147061_u = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   private static InventoryBasic field_147060_v = new InventoryBasic("tmp", true, 45);
   private static int selectedTabIndex = CreativeTabs.tabBlock.getTabIndex();
   private float currentScroll;
   private boolean isScrolling;
   private boolean wasClicking;
   private GuiTextField searchField;
   private List field_147063_B;
   private Slot field_147064_C;
   private boolean field_147057_D;
   private CreativeCrafting field_147059_E;


   public GuiContainerCreative(EntityPlayer var1) {
      super(new GuiContainerCreative$ContainerCreative(var1));
      var1.openContainer = super.inventorySlots;
      super.allowUserInput = true;
      super.ySize = 136;
      super.xSize = 195;
   }

   public void updateScreen() {
      if(!super.mc.playerController.isInCreativeMode()) {
         super.mc.displayGuiScreen(new GuiInventory(super.mc.thePlayer));
      }

   }

   protected void handleMouseClick(Slot var1, int var2, int var3, int var4) {
      this.field_147057_D = true;
      boolean var5 = var4 == 1;
      var4 = var2 == -999 && var4 == 0?4:var4;
      ItemStack var7;
      InventoryPlayer var11;
      if(var1 == null && selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && var4 != 5) {
         var11 = super.mc.thePlayer.inventory;
         if(var11.getItemStack() != null) {
            if(var3 == 0) {
               super.mc.thePlayer.dropPlayerItemWithRandomChoice(var11.getItemStack(), true);
               super.mc.playerController.sendPacketDropItem(var11.getItemStack());
               var11.setItemStack((ItemStack)null);
            }

            if(var3 == 1) {
               var7 = var11.getItemStack().splitStack(1);
               super.mc.thePlayer.dropPlayerItemWithRandomChoice(var7, true);
               super.mc.playerController.sendPacketDropItem(var7);
               if(var11.getItemStack().stackSize == 0) {
                  var11.setItemStack((ItemStack)null);
               }
            }
         }
      } else {
         int var10;
         if(var1 == this.field_147064_C && var5) {
            for(var10 = 0; var10 < super.mc.thePlayer.inventoryContainer.getInventory().size(); ++var10) {
               super.mc.playerController.sendSlotPacket((ItemStack)null, var10);
            }
         } else {
            ItemStack var6;
            if(selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()) {
               if(var1 == this.field_147064_C) {
                  super.mc.thePlayer.inventory.setItemStack((ItemStack)null);
               } else if(var4 == 4 && var1 != null && var1.getHasStack()) {
                  var6 = var1.decrStackSize(var3 == 0?1:var1.getStack().getMaxStackSize());
                  super.mc.thePlayer.dropPlayerItemWithRandomChoice(var6, true);
                  super.mc.playerController.sendPacketDropItem(var6);
               } else if(var4 == 4 && super.mc.thePlayer.inventory.getItemStack() != null) {
                  super.mc.thePlayer.dropPlayerItemWithRandomChoice(super.mc.thePlayer.inventory.getItemStack(), true);
                  super.mc.playerController.sendPacketDropItem(super.mc.thePlayer.inventory.getItemStack());
                  super.mc.thePlayer.inventory.setItemStack((ItemStack)null);
               } else {
                  super.mc.thePlayer.inventoryContainer.slotClick(var1 == null?var2:GuiContainerCreative$CreativeSlot.access$100((GuiContainerCreative$CreativeSlot)var1).slotNumber, var3, var4, super.mc.thePlayer);
                  super.mc.thePlayer.inventoryContainer.detectAndSendChanges();
               }
            } else if(var4 != 5 && var1.inventory == field_147060_v) {
               var11 = super.mc.thePlayer.inventory;
               var7 = var11.getItemStack();
               ItemStack var8 = var1.getStack();
               ItemStack var9;
               if(var4 == 2) {
                  if(var8 != null && var3 >= 0 && var3 < 9) {
                     var9 = var8.copy();
                     var9.stackSize = var9.getMaxStackSize();
                     super.mc.thePlayer.inventory.setInventorySlotContents(var3, var9);
                     super.mc.thePlayer.inventoryContainer.detectAndSendChanges();
                  }

                  return;
               }

               if(var4 == 3) {
                  if(var11.getItemStack() == null && var1.getHasStack()) {
                     var9 = var1.getStack().copy();
                     var9.stackSize = var9.getMaxStackSize();
                     var11.setItemStack(var9);
                  }

                  return;
               }

               if(var4 == 4) {
                  if(var8 != null) {
                     var9 = var8.copy();
                     var9.stackSize = var3 == 0?1:var9.getMaxStackSize();
                     super.mc.thePlayer.dropPlayerItemWithRandomChoice(var9, true);
                     super.mc.playerController.sendPacketDropItem(var9);
                  }

                  return;
               }

               if(var7 != null && var8 != null && var7.isItemEqual(var8)) {
                  if(var3 == 0) {
                     if(var5) {
                        var7.stackSize = var7.getMaxStackSize();
                     } else if(var7.stackSize < var7.getMaxStackSize()) {
                        ++var7.stackSize;
                     }
                  } else if(var7.stackSize <= 1) {
                     var11.setItemStack((ItemStack)null);
                  } else {
                     --var7.stackSize;
                  }
               } else if(var8 != null && var7 == null) {
                  var11.setItemStack(ItemStack.copyItemStack(var8));
                  var7 = var11.getItemStack();
                  if(var5) {
                     var7.stackSize = var7.getMaxStackSize();
                  }
               } else {
                  var11.setItemStack((ItemStack)null);
               }
            } else {
               super.inventorySlots.slotClick(var1 == null?var2:var1.slotNumber, var3, var4, super.mc.thePlayer);
               if(Container.func_94532_c(var3) == 2) {
                  for(var10 = 0; var10 < 9; ++var10) {
                     super.mc.playerController.sendSlotPacket(super.inventorySlots.getSlot(45 + var10).getStack(), 36 + var10);
                  }
               } else if(var1 != null) {
                  var6 = super.inventorySlots.getSlot(var1.slotNumber).getStack();
                  super.mc.playerController.sendSlotPacket(var6, var1.slotNumber - super.inventorySlots.inventorySlots.size() + 9 + 36);
               }
            }
         }
      }

   }

   public void initGui() {
      if(super.mc.playerController.isInCreativeMode()) {
         super.initGui();
         super.buttonList.clear();
         Keyboard.enableRepeatEvents(true);
         this.searchField = new GuiTextField(super.fontRendererObj, super.guiLeft + 82, super.guiTop + 6, 89, super.fontRendererObj.FONT_HEIGHT);
         this.searchField.setMaxStringLength(15);
         this.searchField.setEnableBackgroundDrawing(false);
         this.searchField.setVisible(false);
         this.searchField.setTextColor(16777215);
         int var1 = selectedTabIndex;
         selectedTabIndex = -1;
         this.setCurrentCreativeTab(CreativeTabs.creativeTabArray[var1]);
         this.field_147059_E = new CreativeCrafting(super.mc);
         super.mc.thePlayer.inventoryContainer.addCraftingToCrafters(this.field_147059_E);
      } else {
         super.mc.displayGuiScreen(new GuiInventory(super.mc.thePlayer));
      }

   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if(super.mc.thePlayer != null && super.mc.thePlayer.inventory != null) {
         super.mc.thePlayer.inventoryContainer.removeCraftingFromCrafters(this.field_147059_E);
      }

      Keyboard.enableRepeatEvents(false);
   }

   protected void keyTyped(char var1, int var2) {
      if(selectedTabIndex != CreativeTabs.tabAllSearch.getTabIndex()) {
         if(GameSettings.isKeyDown(super.mc.gameSettings.keyBindChat)) {
            this.setCurrentCreativeTab(CreativeTabs.tabAllSearch);
         } else {
            super.keyTyped(var1, var2);
         }

      } else {
         if(this.field_147057_D) {
            this.field_147057_D = false;
            this.searchField.setText("");
         }

         if(!this.checkHotbarKeys(var2)) {
            if(this.searchField.textboxKeyTyped(var1, var2)) {
               this.updateCreativeSearch();
            } else {
               super.keyTyped(var1, var2);
            }

         }
      }
   }

   private void updateCreativeSearch() {
      GuiContainerCreative$ContainerCreative var1 = (GuiContainerCreative$ContainerCreative)super.inventorySlots;
      var1.itemList.clear();
      Iterator var2 = Item.itemRegistry.iterator();

      while(var2.hasNext()) {
         Item var3 = (Item)var2.next();
         if(var3 != null && var3.getCreativeTab() != null) {
            var3.getSubItems(var3, (CreativeTabs)null, var1.itemList);
         }
      }

      Enchantment[] var8 = Enchantment.enchantmentsList;
      int var9 = var8.length;

      for(int var4 = 0; var4 < var9; ++var4) {
         Enchantment var5 = var8[var4];
         if(var5 != null && var5.type != null) {
            Items.enchanted_book.func_92113_a(var5, var1.itemList);
         }
      }

      var2 = var1.itemList.iterator();
      String var10 = this.searchField.getText().toLowerCase();

      while(var2.hasNext()) {
         ItemStack var11 = (ItemStack)var2.next();
         boolean var12 = false;
         Iterator var6 = var11.getTooltip(super.mc.thePlayer, super.mc.gameSettings.advancedItemTooltips).iterator();

         while(true) {
            if(var6.hasNext()) {
               String var7 = (String)var6.next();
               if(!var7.toLowerCase().contains(var10)) {
                  continue;
               }

               var12 = true;
            }

            if(!var12) {
               var2.remove();
            }
            break;
         }
      }

      this.currentScroll = 0.0F;
      var1.scrollTo(0.0F);
   }

   protected void drawGuiContainerForegroundLayer(int var1, int var2) {
      CreativeTabs var3 = CreativeTabs.creativeTabArray[selectedTabIndex];
      if(var3.drawInForegroundOfTab()) {
         GL11.glDisable(3042);
         super.fontRendererObj.drawString(I18n.format(var3.getTranslatedTabLabel(), new Object[0]), 8, 6, 4210752);
      }

   }

   protected void mouseClicked(int var1, int var2, int var3) {
      if(var3 == 0) {
         int var4 = var1 - super.guiLeft;
         int var5 = var2 - super.guiTop;
         CreativeTabs[] var6 = CreativeTabs.creativeTabArray;
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            CreativeTabs var9 = var6[var8];
            if(this.func_147049_a(var9, var4, var5)) {
               return;
            }
         }
      }

      super.mouseClicked(var1, var2, var3);
   }

   protected void mouseMovedOrUp(int var1, int var2, int var3) {
      if(var3 == 0) {
         int var4 = var1 - super.guiLeft;
         int var5 = var2 - super.guiTop;
         CreativeTabs[] var6 = CreativeTabs.creativeTabArray;
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            CreativeTabs var9 = var6[var8];
            if(this.func_147049_a(var9, var4, var5)) {
               this.setCurrentCreativeTab(var9);
               return;
            }
         }
      }

      super.mouseMovedOrUp(var1, var2, var3);
   }

   private boolean needsScrollBars() {
      return selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && CreativeTabs.creativeTabArray[selectedTabIndex].shouldHidePlayerInventory() && ((GuiContainerCreative$ContainerCreative)super.inventorySlots).func_148328_e();
   }

   private void setCurrentCreativeTab(CreativeTabs var1) {
      int var2 = selectedTabIndex;
      selectedTabIndex = var1.getTabIndex();
      GuiContainerCreative$ContainerCreative var3 = (GuiContainerCreative$ContainerCreative)super.inventorySlots;
      super.field_147008_s.clear();
      var3.itemList.clear();
      var1.displayAllReleventItems(var3.itemList);
      if(var1 == CreativeTabs.tabInventory) {
         Container var4 = super.mc.thePlayer.inventoryContainer;
         if(this.field_147063_B == null) {
            this.field_147063_B = var3.inventorySlots;
         }

         var3.inventorySlots = new ArrayList();

         for(int var5 = 0; var5 < var4.inventorySlots.size(); ++var5) {
            GuiContainerCreative$CreativeSlot var6 = new GuiContainerCreative$CreativeSlot(this, (Slot)var4.inventorySlots.get(var5), var5);
            var3.inventorySlots.add(var6);
            int var7;
            int var8;
            int var9;
            if(var5 >= 5 && var5 < 9) {
               var7 = var5 - 5;
               var8 = var7 / 2;
               var9 = var7 % 2;
               var6.xDisplayPosition = 9 + var8 * 54;
               var6.yDisplayPosition = 6 + var9 * 27;
            } else if(var5 >= 0 && var5 < 5) {
               var6.yDisplayPosition = -2000;
               var6.xDisplayPosition = -2000;
            } else if(var5 < var4.inventorySlots.size()) {
               var7 = var5 - 9;
               var8 = var7 % 9;
               var9 = var7 / 9;
               var6.xDisplayPosition = 9 + var8 * 18;
               if(var5 >= 36) {
                  var6.yDisplayPosition = 112;
               } else {
                  var6.yDisplayPosition = 54 + var9 * 18;
               }
            }
         }

         this.field_147064_C = new Slot(field_147060_v, 0, 173, 112);
         var3.inventorySlots.add(this.field_147064_C);
      } else if(var2 == CreativeTabs.tabInventory.getTabIndex()) {
         var3.inventorySlots = this.field_147063_B;
         this.field_147063_B = null;
      }

      if(this.searchField != null) {
         if(var1 == CreativeTabs.tabAllSearch) {
            this.searchField.setVisible(true);
            this.searchField.setCanLoseFocus(false);
            this.searchField.setFocused(true);
            this.searchField.setText("");
            this.updateCreativeSearch();
         } else {
            this.searchField.setVisible(false);
            this.searchField.setCanLoseFocus(true);
            this.searchField.setFocused(false);
         }
      }

      this.currentScroll = 0.0F;
      var3.scrollTo(0.0F);
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int var1 = Mouse.getEventDWheel();
      if(var1 != 0 && this.needsScrollBars()) {
         int var2 = ((GuiContainerCreative$ContainerCreative)super.inventorySlots).itemList.size() / 9 - 5 + 1;
         if(var1 > 0) {
            var1 = 1;
         }

         if(var1 < 0) {
            var1 = -1;
         }

         this.currentScroll = (float)((double)this.currentScroll - (double)var1 / (double)var2);
         if(this.currentScroll < 0.0F) {
            this.currentScroll = 0.0F;
         }

         if(this.currentScroll > 1.0F) {
            this.currentScroll = 1.0F;
         }

         ((GuiContainerCreative$ContainerCreative)super.inventorySlots).scrollTo(this.currentScroll);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      boolean var4 = Mouse.isButtonDown(0);
      int var5 = super.guiLeft;
      int var6 = super.guiTop;
      int var7 = var5 + 175;
      int var8 = var6 + 18;
      int var9 = var7 + 14;
      int var10 = var8 + 112;
      if(!this.wasClicking && var4 && var1 >= var7 && var2 >= var8 && var1 < var9 && var2 < var10) {
         this.isScrolling = this.needsScrollBars();
      }

      if(!var4) {
         this.isScrolling = false;
      }

      this.wasClicking = var4;
      if(this.isScrolling) {
         this.currentScroll = ((float)(var2 - var8) - 7.5F) / ((float)(var10 - var8) - 15.0F);
         if(this.currentScroll < 0.0F) {
            this.currentScroll = 0.0F;
         }

         if(this.currentScroll > 1.0F) {
            this.currentScroll = 1.0F;
         }

         ((GuiContainerCreative$ContainerCreative)super.inventorySlots).scrollTo(this.currentScroll);
      }

      super.drawScreen(var1, var2, var3);
      CreativeTabs[] var11 = CreativeTabs.creativeTabArray;
      int var12 = var11.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         CreativeTabs var14 = var11[var13];
         if(this.renderCreativeInventoryHoveringText(var14, var1, var2)) {
            break;
         }
      }

      if(this.field_147064_C != null && selectedTabIndex == CreativeTabs.tabInventory.getTabIndex() && this.func_146978_c(this.field_147064_C.xDisplayPosition, this.field_147064_C.yDisplayPosition, 16, 16, var1, var2)) {
         this.drawCreativeTabHoveringText(I18n.format("inventory.binSlot", new Object[0]), var1, var2);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2896);
   }

   protected void renderToolTip(ItemStack var1, int var2, int var3) {
      if(selectedTabIndex == CreativeTabs.tabAllSearch.getTabIndex()) {
         List var4 = var1.getTooltip(super.mc.thePlayer, super.mc.gameSettings.advancedItemTooltips);
         CreativeTabs var5 = var1.getItem().getCreativeTab();
         if(var5 == null && var1.getItem() == Items.enchanted_book) {
            Map var6 = EnchantmentHelper.getEnchantments(var1);
            if(var6.size() == 1) {
               Enchantment var7 = Enchantment.enchantmentsList[((Integer)var6.keySet().iterator().next()).intValue()];
               CreativeTabs[] var8 = CreativeTabs.creativeTabArray;
               int var9 = var8.length;

               for(int var10 = 0; var10 < var9; ++var10) {
                  CreativeTabs var11 = var8[var10];
                  if(var11.func_111226_a(var7.type)) {
                     var5 = var11;
                     break;
                  }
               }
            }
         }

         if(var5 != null) {
            var4.add(1, "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.format(var5.getTranslatedTabLabel(), new Object[0]));
         }

         for(int var12 = 0; var12 < var4.size(); ++var12) {
            if(var12 == 0) {
               var4.set(var12, var1.getRarity().rarityColor + (String)var4.get(var12));
            } else {
               var4.set(var12, EnumChatFormatting.GRAY + (String)var4.get(var12));
            }
         }

         this.func_146283_a(var4, var2, var3);
      } else {
         super.renderToolTip(var1, var2, var3);
      }

   }

   protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.enableGUIStandardItemLighting();
      CreativeTabs var4 = CreativeTabs.creativeTabArray[selectedTabIndex];
      CreativeTabs[] var5 = CreativeTabs.creativeTabArray;
      int var6 = var5.length;

      int var7;
      for(var7 = 0; var7 < var6; ++var7) {
         CreativeTabs var8 = var5[var7];
         super.mc.getTextureManager().bindTexture(field_147061_u);
         if(var8.getTabIndex() != selectedTabIndex) {
            this.func_147051_a(var8);
         }
      }

      super.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + var4.getBackgroundImageName()));
      this.drawTexturedModalRect(super.guiLeft, super.guiTop, 0, 0, super.xSize, super.ySize);
      this.searchField.drawTextBox();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int var9 = super.guiLeft + 175;
      var6 = super.guiTop + 18;
      var7 = var6 + 112;
      super.mc.getTextureManager().bindTexture(field_147061_u);
      if(var4.shouldHidePlayerInventory()) {
         this.drawTexturedModalRect(var9, var6 + (int)((float)(var7 - var6 - 17) * this.currentScroll), 232 + (this.needsScrollBars()?0:12), 0, 12, 15);
      }

      this.func_147051_a(var4);
      if(var4 == CreativeTabs.tabInventory) {
         GuiInventory.func_147046_a(super.guiLeft + 43, super.guiTop + 45, 20, (float)(super.guiLeft + 43 - var2), (float)(super.guiTop + 45 - 30 - var3), super.mc.thePlayer);
      }

   }

   protected boolean func_147049_a(CreativeTabs var1, int var2, int var3) {
      int var4 = var1.getTabColumn();
      int var5 = 28 * var4;
      byte var6 = 0;
      if(var4 == 5) {
         var5 = super.xSize - 28 + 2;
      } else if(var4 > 0) {
         var5 += var4;
      }

      int var7;
      if(var1.isTabInFirstRow()) {
         var7 = var6 - 32;
      } else {
         var7 = var6 + super.ySize;
      }

      return var2 >= var5 && var2 <= var5 + 28 && var3 >= var7 && var3 <= var7 + 32;
   }

   protected boolean renderCreativeInventoryHoveringText(CreativeTabs var1, int var2, int var3) {
      int var4 = var1.getTabColumn();
      int var5 = 28 * var4;
      byte var6 = 0;
      if(var4 == 5) {
         var5 = super.xSize - 28 + 2;
      } else if(var4 > 0) {
         var5 += var4;
      }

      int var7;
      if(var1.isTabInFirstRow()) {
         var7 = var6 - 32;
      } else {
         var7 = var6 + super.ySize;
      }

      if(this.func_146978_c(var5 + 3, var7 + 3, 23, 27, var2, var3)) {
         this.drawCreativeTabHoveringText(I18n.format(var1.getTranslatedTabLabel(), new Object[0]), var2, var3);
         return true;
      } else {
         return false;
      }
   }

   protected void func_147051_a(CreativeTabs var1) {
      boolean var2 = var1.getTabIndex() == selectedTabIndex;
      boolean var3 = var1.isTabInFirstRow();
      int var4 = var1.getTabColumn();
      int var5 = var4 * 28;
      int var6 = 0;
      int var7 = super.guiLeft + 28 * var4;
      int var8 = super.guiTop;
      byte var9 = 32;
      if(var2) {
         var6 += 32;
      }

      if(var4 == 5) {
         var7 = super.guiLeft + super.xSize - 28;
      } else if(var4 > 0) {
         var7 += var4;
      }

      if(var3) {
         var8 -= 28;
      } else {
         var6 += 64;
         var8 += super.ySize - 4;
      }

      GL11.glDisable(2896);
      this.drawTexturedModalRect(var7, var8, var5, var6, 28, var9);
      super.zLevel = 100.0F;
      GuiScreen.itemRender.zLevel = 100.0F;
      var7 += 6;
      var8 += 8 + (var3?1:-1);
      GL11.glEnable(2896);
      GL11.glEnable('\u803a');
      ItemStack var10 = var1.getIconItemStack();
      GuiScreen.itemRender.renderItemAndEffectIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var10, var7, var8);
      GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), var10, var7, var8);
      GL11.glDisable(2896);
      GuiScreen.itemRender.zLevel = 0.0F;
      super.zLevel = 0.0F;
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.id == 0) {
         super.mc.displayGuiScreen(new GuiAchievements(this, super.mc.thePlayer.getStatFileWriter()));
      }

      if(var1.id == 1) {
         super.mc.displayGuiScreen(new GuiStats(this, super.mc.thePlayer.getStatFileWriter()));
      }

   }

   public int func_147056_g() {
      return selectedTabIndex;
   }

   // $FF: synthetic method
   static InventoryBasic access$000() {
      return field_147060_v;
   }

}
