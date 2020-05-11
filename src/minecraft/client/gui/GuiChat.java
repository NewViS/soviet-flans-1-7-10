package net.minecraft.client.gui;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tv.twitch.chat.ChatUserInfo;

public class GuiChat extends GuiScreen implements GuiYesNoCallback {

   private static final Set field_152175_f = Sets.newHashSet(new String[]{"http", "https"});
   private static final Logger logger = LogManager.getLogger();
   private String field_146410_g = "";
   private int sentHistoryCursor = -1;
   private boolean field_146417_i;
   private boolean field_146414_r;
   private int field_146413_s;
   private List field_146412_t = new ArrayList();
   private URI clickedURI;
   protected GuiTextField inputField;
   private String defaultInputFieldText = "";


   public GuiChat() {}

   public GuiChat(String var1) {
      this.defaultInputFieldText = var1;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.sentHistoryCursor = super.mc.ingameGUI.getChatGUI().getSentMessages().size();
      this.inputField = new GuiTextField(super.fontRendererObj, 4, super.height - 12, super.width - 4, 12);
      this.inputField.setMaxStringLength(100);
      this.inputField.setEnableBackgroundDrawing(false);
      this.inputField.setFocused(true);
      this.inputField.setText(this.defaultInputFieldText);
      this.inputField.setCanLoseFocus(false);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      super.mc.ingameGUI.getChatGUI().resetScroll();
   }

   public void updateScreen() {
      this.inputField.updateCursorCounter();
   }

   protected void keyTyped(char var1, int var2) {
      this.field_146414_r = false;
      if(var2 == 15) {
         this.func_146404_p_();
      } else {
         this.field_146417_i = false;
      }

      if(var2 == 1) {
         super.mc.displayGuiScreen((GuiScreen)null);
      } else if(var2 != 28 && var2 != 156) {
         if(var2 == 200) {
            this.getSentHistory(-1);
         } else if(var2 == 208) {
            this.getSentHistory(1);
         } else if(var2 == 201) {
            super.mc.ingameGUI.getChatGUI().scroll(super.mc.ingameGUI.getChatGUI().func_146232_i() - 1);
         } else if(var2 == 209) {
            super.mc.ingameGUI.getChatGUI().scroll(-super.mc.ingameGUI.getChatGUI().func_146232_i() + 1);
         } else {
            this.inputField.textboxKeyTyped(var1, var2);
         }
      } else {
         String var3 = this.inputField.getText().trim();
         if(var3.length() > 0) {
            this.func_146403_a(var3);
         }

         super.mc.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_146403_a(String var1) {
      super.mc.ingameGUI.getChatGUI().addToSentMessages(var1);
      super.mc.thePlayer.sendChatMessage(var1);
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int var1 = Mouse.getEventDWheel();
      if(var1 != 0) {
         if(var1 > 1) {
            var1 = 1;
         }

         if(var1 < -1) {
            var1 = -1;
         }

         if(!isShiftKeyDown()) {
            var1 *= 7;
         }

         super.mc.ingameGUI.getChatGUI().scroll(var1);
      }

   }

   protected void mouseClicked(int var1, int var2, int var3) {
      if(var3 == 0 && super.mc.gameSettings.chatLinks) {
         IChatComponent var4 = super.mc.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY());
         if(var4 != null) {
            ClickEvent var5 = var4.getChatStyle().getChatClickEvent();
            if(var5 != null) {
               if(isShiftKeyDown()) {
                  this.inputField.writeText(var4.getUnformattedTextForChat());
               } else {
                  URI var6;
                  if(var5.getAction() == ClickEvent$Action.OPEN_URL) {
                     try {
                        var6 = new URI(var5.getValue());
                        if(!field_152175_f.contains(var6.getScheme().toLowerCase())) {
                           throw new URISyntaxException(var5.getValue(), "Unsupported protocol: " + var6.getScheme().toLowerCase());
                        }

                        if(super.mc.gameSettings.chatLinksPrompt) {
                           this.clickedURI = var6;
                           super.mc.displayGuiScreen(new GuiConfirmOpenLink(this, var5.getValue(), 0, false));
                        } else {
                           this.func_146407_a(var6);
                        }
                     } catch (URISyntaxException var7) {
                        logger.error("Can\'t open url for " + var5, var7);
                     }
                  } else if(var5.getAction() == ClickEvent$Action.OPEN_FILE) {
                     var6 = (new File(var5.getValue())).toURI();
                     this.func_146407_a(var6);
                  } else if(var5.getAction() == ClickEvent$Action.SUGGEST_COMMAND) {
                     this.inputField.setText(var5.getValue());
                  } else if(var5.getAction() == ClickEvent$Action.RUN_COMMAND) {
                     this.func_146403_a(var5.getValue());
                  } else if(var5.getAction() == ClickEvent$Action.TWITCH_USER_INFO) {
                     ChatUserInfo var8 = super.mc.func_152346_Z().func_152926_a(var5.getValue());
                     if(var8 != null) {
                        super.mc.displayGuiScreen(new GuiTwitchUserMode(super.mc.func_152346_Z(), var8));
                     } else {
                        logger.error("Tried to handle twitch user but couldn\'t find them!");
                     }
                  } else {
                     logger.error("Don\'t know how to handle " + var5);
                  }
               }

               return;
            }
         }
      }

      this.inputField.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }

   public void confirmClicked(boolean var1, int var2) {
      if(var2 == 0) {
         if(var1) {
            this.func_146407_a(this.clickedURI);
         }

         this.clickedURI = null;
         super.mc.displayGuiScreen(this);
      }

   }

   private void func_146407_a(URI var1) {
      try {
         Class var2 = Class.forName("java.awt.Desktop");
         Object var3 = var2.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
         var2.getMethod("browse", new Class[]{URI.class}).invoke(var3, new Object[]{var1});
      } catch (Throwable var4) {
         logger.error("Couldn\'t open link", var4);
      }

   }

   public void func_146404_p_() {
      String var3;
      if(this.field_146417_i) {
         this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
         if(this.field_146413_s >= this.field_146412_t.size()) {
            this.field_146413_s = 0;
         }
      } else {
         int var1 = this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false);
         this.field_146412_t.clear();
         this.field_146413_s = 0;
         String var2 = this.inputField.getText().substring(var1).toLowerCase();
         var3 = this.inputField.getText().substring(0, this.inputField.getCursorPosition());
         this.func_146405_a(var3, var2);
         if(this.field_146412_t.isEmpty()) {
            return;
         }

         this.field_146417_i = true;
         this.inputField.deleteFromCursor(var1 - this.inputField.getCursorPosition());
      }

      if(this.field_146412_t.size() > 1) {
         StringBuilder var4 = new StringBuilder();

         for(Iterator var5 = this.field_146412_t.iterator(); var5.hasNext(); var4.append(var3)) {
            var3 = (String)var5.next();
            if(var4.length() > 0) {
               var4.append(", ");
            }
         }

         super.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(var4.toString()), 1);
      }

      this.inputField.writeText((String)this.field_146412_t.get(this.field_146413_s++));
   }

   private void func_146405_a(String var1, String var2) {
      if(var1.length() >= 1) {
         super.mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(var1));
         this.field_146414_r = true;
      }
   }

   public void getSentHistory(int var1) {
      int var2 = this.sentHistoryCursor + var1;
      int var3 = super.mc.ingameGUI.getChatGUI().getSentMessages().size();
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 > var3) {
         var2 = var3;
      }

      if(var2 != this.sentHistoryCursor) {
         if(var2 == var3) {
            this.sentHistoryCursor = var3;
            this.inputField.setText(this.field_146410_g);
         } else {
            if(this.sentHistoryCursor == var3) {
               this.field_146410_g = this.inputField.getText();
            }

            this.inputField.setText((String)super.mc.ingameGUI.getChatGUI().getSentMessages().get(var2));
            this.sentHistoryCursor = var2;
         }
      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      drawRect(2, super.height - 14, super.width - 2, super.height - 2, Integer.MIN_VALUE);
      this.inputField.drawTextBox();
      IChatComponent var4 = super.mc.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY());
      if(var4 != null && var4.getChatStyle().getChatHoverEvent() != null) {
         HoverEvent var5 = var4.getChatStyle().getChatHoverEvent();
         if(var5.getAction() == HoverEvent$Action.SHOW_ITEM) {
            ItemStack var6 = null;

            try {
               NBTBase var7 = JsonToNBT.func_150315_a(var5.getValue().getUnformattedText());
               if(var7 != null && var7 instanceof NBTTagCompound) {
                  var6 = ItemStack.loadItemStackFromNBT((NBTTagCompound)var7);
               }
            } catch (NBTException var11) {
               ;
            }

            if(var6 != null) {
               this.renderToolTip(var6, var1, var2);
            } else {
               this.drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid Item!", var1, var2);
            }
         } else if(var5.getAction() == HoverEvent$Action.SHOW_TEXT) {
            this.func_146283_a(Splitter.on("\n").splitToList(var5.getValue().getFormattedText()), var1, var2);
         } else if(var5.getAction() == HoverEvent$Action.SHOW_ACHIEVEMENT) {
            StatBase var12 = StatList.func_151177_a(var5.getValue().getUnformattedText());
            if(var12 != null) {
               IChatComponent var13 = var12.func_150951_e();
               ChatComponentTranslation var8 = new ChatComponentTranslation("stats.tooltip.type." + (var12.isAchievement()?"achievement":"statistic"), new Object[0]);
               var8.getChatStyle().setItalic(Boolean.valueOf(true));
               String var9 = var12 instanceof Achievement?((Achievement)var12).getDescription():null;
               ArrayList var10 = Lists.newArrayList(new String[]{var13.getFormattedText(), var8.getFormattedText()});
               if(var9 != null) {
                  var10.addAll(super.fontRendererObj.listFormattedStringToWidth(var9, 150));
               }

               this.func_146283_a(var10, var1, var2);
            } else {
               this.drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid statistic/achievement!", var1, var2);
            }
         }

         GL11.glDisable(2896);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void func_146406_a(String[] var1) {
      if(this.field_146414_r) {
         this.field_146417_i = false;
         this.field_146412_t.clear();
         String[] var2 = var1;
         int var3 = var1.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            if(var5.length() > 0) {
               this.field_146412_t.add(var5);
            }
         }

         String var6 = this.inputField.getText().substring(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false));
         String var7 = StringUtils.getCommonPrefix(var1);
         if(var7.length() > 0 && !var6.equalsIgnoreCase(var7)) {
            this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
            this.inputField.writeText(var7);
         } else if(this.field_146412_t.size() > 0) {
            this.field_146417_i = true;
            this.func_146404_p_();
         }
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
