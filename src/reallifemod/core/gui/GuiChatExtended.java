package de.ItsAMysterious.mods.reallifemod.core.gui;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.event.HoverEvent;
import net.minecraft.event.ClickEvent.Action;
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
import net.minecraftforge.client.ClientCommandHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tv.twitch.chat.ChatUserInfo;

@SideOnly(Side.CLIENT)
public class GuiChatExtended extends GuiScreen implements GuiYesNoCallback {

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
   private static final String __OBFID = "CL_00000682";


   public GuiChatExtended() {}

   public GuiChatExtended(String p_i1024_1_) {
      this.defaultInputFieldText = p_i1024_1_;
   }

   public void func_73866_w_() {
      Keyboard.enableRepeatEvents(true);
      this.sentHistoryCursor = this.field_146297_k.ingameGUI.getChatGUI().getSentMessages().size();
      this.inputField = new GuiTextField(this.field_146289_q, 4, this.field_146295_m - 12, this.field_146294_l - 4, 12);
      this.inputField.setMaxStringLength(100);
      this.inputField.setEnableBackgroundDrawing(false);
      this.inputField.setFocused(true);
      this.inputField.setText(this.defaultInputFieldText);
      this.inputField.setCanLoseFocus(false);
   }

   public void func_146281_b() {
      Keyboard.enableRepeatEvents(false);
      this.field_146297_k.ingameGUI.getChatGUI().resetScroll();
   }

   public void func_73876_c() {
      this.inputField.updateCursorCounter();
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) {
      this.field_146414_r = false;
      if(p_73869_2_ == 15) {
         this.func_146404_p_();
      } else {
         this.field_146417_i = false;
      }

      if(p_73869_2_ == 1) {
         this.field_146297_k.displayGuiScreen((GuiScreen)null);
      } else if(p_73869_2_ != 28 && p_73869_2_ != 156) {
         if(p_73869_2_ == 200) {
            this.getSentHistory(-1);
         } else if(p_73869_2_ == 208) {
            this.getSentHistory(1);
         } else if(p_73869_2_ == 201) {
            this.field_146297_k.ingameGUI.getChatGUI().scroll(this.field_146297_k.ingameGUI.getChatGUI().func_146232_i() - 1);
         } else if(p_73869_2_ == 209) {
            this.field_146297_k.ingameGUI.getChatGUI().scroll(-this.field_146297_k.ingameGUI.getChatGUI().func_146232_i() + 1);
         } else {
            this.inputField.textboxKeyTyped(p_73869_1_, p_73869_2_);
         }
      } else {
         String s = this.inputField.getText().trim();
         if(s.length() > 0) {
            this.func_146403_a(s);
         }

         this.field_146297_k.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_146403_a(String p_146403_1_) {
      this.field_146297_k.ingameGUI.getChatGUI().addToSentMessages(p_146403_1_);
      if(ClientCommandHandler.instance.func_71556_a(this.field_146297_k.thePlayer, p_146403_1_) == 0) {
         this.field_146297_k.thePlayer.sendChatMessage(p_146403_1_);
      }
   }

   public void func_146274_d() {
      super.handleMouseInput();
      int i = Mouse.getEventDWheel();
      if(i != 0) {
         if(i > 1) {
            i = 1;
         }

         if(i < -1) {
            i = -1;
         }

         if(!func_146272_n()) {
            i *= 7;
         }

         this.field_146297_k.ingameGUI.getChatGUI().scroll(i);
      }

   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
      if(p_73864_3_ == 0 && this.field_146297_k.gameSettings.chatLinks) {
         IChatComponent ichatcomponent = this.field_146297_k.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY());
         if(ichatcomponent != null) {
            ClickEvent clickevent = ichatcomponent.getChatStyle().getChatClickEvent();
            if(clickevent != null) {
               if(func_146272_n()) {
                  this.inputField.writeText(ichatcomponent.getUnformattedTextForChat());
               } else {
                  URI uri;
                  if(clickevent.getAction() == Action.OPEN_URL) {
                     try {
                        uri = new URI(clickevent.getValue());
                        if(!field_152175_f.contains(uri.getScheme().toLowerCase())) {
                           throw new URISyntaxException(clickevent.getValue(), "Unsupported protocol: " + uri.getScheme().toLowerCase());
                        }

                        if(this.field_146297_k.gameSettings.chatLinksPrompt) {
                           this.clickedURI = uri;
                           this.field_146297_k.displayGuiScreen(new GuiConfirmOpenLink(this, clickevent.getValue(), 0, false));
                        } else {
                           this.func_146407_a(uri);
                        }
                     } catch (URISyntaxException var8) {
                        logger.error("Can\'t open url for " + clickevent, var8);
                     }
                  } else if(clickevent.getAction() == Action.OPEN_FILE) {
                     uri = (new File(clickevent.getValue())).toURI();
                     this.func_146407_a(uri);
                  } else if(clickevent.getAction() == Action.SUGGEST_COMMAND) {
                     this.inputField.setText(clickevent.getValue());
                  } else if(clickevent.getAction() == Action.RUN_COMMAND) {
                     this.func_146403_a(clickevent.getValue());
                  } else if(clickevent.getAction() == Action.TWITCH_USER_INFO) {
                     ChatUserInfo chatuserinfo = this.field_146297_k.func_152346_Z().func_152926_a(clickevent.getValue());
                     if(chatuserinfo != null) {
                        this.field_146297_k.displayGuiScreen(new GuiTwitchUserMode(this.field_146297_k.func_152346_Z(), chatuserinfo));
                     } else {
                        logger.error("Tried to handle twitch user but couldn\'t find them!");
                     }
                  } else {
                     logger.error("Don\'t know how to handle " + clickevent);
                  }
               }

               return;
            }
         }
      }

      this.inputField.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
      super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
      if(p_73878_2_ == 0) {
         if(p_73878_1_) {
            this.func_146407_a(this.clickedURI);
         }

         this.clickedURI = null;
         this.field_146297_k.displayGuiScreen(this);
      }

   }

   private void func_146407_a(URI p_146407_1_) {
      try {
         Class throwable = Class.forName("java.awt.Desktop");
         Object object = throwable.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
         throwable.getMethod("browse", new Class[]{URI.class}).invoke(object, new Object[]{p_146407_1_});
      } catch (Throwable var4) {
         logger.error("Couldn\'t open link", var4);
      }

   }

   public void func_146404_p_() {
      String s1;
      if(this.field_146417_i) {
         this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
         if(this.field_146413_s >= this.field_146412_t.size()) {
            this.field_146413_s = 0;
         }
      } else {
         int stringbuilder = this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false);
         this.field_146412_t.clear();
         this.field_146413_s = 0;
         String iterator = this.inputField.getText().substring(stringbuilder).toLowerCase();
         s1 = this.inputField.getText().substring(0, this.inputField.getCursorPosition());
         this.func_146405_a(s1, iterator);
         if(this.field_146412_t.isEmpty()) {
            return;
         }

         this.field_146417_i = true;
         this.inputField.deleteFromCursor(stringbuilder - this.inputField.getCursorPosition());
      }

      if(this.field_146412_t.size() > 1) {
         StringBuilder stringbuilder1 = new StringBuilder();

         for(Iterator iterator1 = this.field_146412_t.iterator(); iterator1.hasNext(); stringbuilder1.append(s1)) {
            s1 = (String)iterator1.next();
            if(stringbuilder1.length() > 0) {
               stringbuilder1.append(", ");
            }
         }

         this.field_146297_k.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(stringbuilder1.toString()), 1);
      }

      this.inputField.writeText(EnumChatFormatting.getTextWithoutFormattingCodes((String)this.field_146412_t.get(this.field_146413_s++)));
   }

   private void func_146405_a(String p_146405_1_, String p_146405_2_) {
      if(p_146405_1_.length() >= 1) {
         ClientCommandHandler.instance.autoComplete(p_146405_1_, p_146405_2_);
         this.field_146297_k.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(p_146405_1_));
         this.field_146414_r = true;
      }

   }

   public void getSentHistory(int p_146402_1_) {
      int j = this.sentHistoryCursor + p_146402_1_;
      int k = this.field_146297_k.ingameGUI.getChatGUI().getSentMessages().size();
      if(j < 0) {
         j = 0;
      }

      if(j > k) {
         j = k;
      }

      if(j != this.sentHistoryCursor) {
         if(j == k) {
            this.sentHistoryCursor = k;
            this.inputField.setText(this.field_146410_g);
         } else {
            if(this.sentHistoryCursor == k) {
               this.field_146410_g = this.inputField.getText();
            }

            this.inputField.setText((String)this.field_146297_k.ingameGUI.getChatGUI().getSentMessages().get(j));
            this.sentHistoryCursor = j;
         }
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      func_73734_a(2, this.field_146295_m - 14, this.field_146294_l - 2, this.field_146295_m - 2, Integer.MIN_VALUE);
      this.inputField.drawTextBox();
      IChatComponent ichatcomponent = this.field_146297_k.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY());
      if(ichatcomponent != null && ichatcomponent.getChatStyle().getChatHoverEvent() != null) {
         HoverEvent hoverevent = ichatcomponent.getChatStyle().getChatHoverEvent();
         if(hoverevent.getAction() == net.minecraft.event.HoverEvent.Action.SHOW_ITEM) {
            ItemStack statbase = null;

            try {
               NBTBase ichatcomponent1 = JsonToNBT.func_150315_a(hoverevent.getValue().getUnformattedText());
               if(ichatcomponent1 != null && ichatcomponent1 instanceof NBTTagCompound) {
                  statbase = ItemStack.loadItemStackFromNBT((NBTTagCompound)ichatcomponent1);
               }
            } catch (NBTException var11) {
               ;
            }

            if(statbase != null) {
               this.func_146285_a(statbase, p_73863_1_, p_73863_2_);
            } else {
               this.func_146279_a(EnumChatFormatting.RED + "Invalid Item!", p_73863_1_, p_73863_2_);
            }
         } else if(hoverevent.getAction() == net.minecraft.event.HoverEvent.Action.SHOW_TEXT) {
            this.func_146283_a(Splitter.on("\n").splitToList(hoverevent.getValue().getFormattedText()), p_73863_1_, p_73863_2_);
         } else if(hoverevent.getAction() == net.minecraft.event.HoverEvent.Action.SHOW_ACHIEVEMENT) {
            StatBase statbase1 = StatList.func_151177_a(hoverevent.getValue().getUnformattedText());
            if(statbase1 != null) {
               IChatComponent ichatcomponent11 = statbase1.func_150951_e();
               ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("stats.tooltip.type." + (statbase1.isAchievement()?"achievement":"statistic"), new Object[0]);
               chatcomponenttranslation.func_150256_b().setItalic(Boolean.valueOf(true));
               String s = statbase1 instanceof Achievement?((Achievement)statbase1).getDescription():null;
               ArrayList arraylist = Lists.newArrayList(new String[]{ichatcomponent11.getFormattedText(), chatcomponenttranslation.func_150254_d()});
               if(s != null) {
                  arraylist.addAll(this.field_146289_q.listFormattedStringToWidth(s, 150));
               }

               this.func_146283_a(arraylist, p_73863_1_, p_73863_2_);
            } else {
               this.func_146279_a(EnumChatFormatting.RED + "Invalid statistic/achievement!", p_73863_1_, p_73863_2_);
            }
         }

         GL11.glDisable(2896);
      }

      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   public void func_146406_a(String[] p_146406_1_) {
      if(this.field_146414_r) {
         this.field_146417_i = false;
         this.field_146412_t.clear();
         String[] astring1 = p_146406_1_;
         int i = p_146406_1_.length;
         String[] complete = ClientCommandHandler.instance.latestAutoComplete;
         if(complete != null) {
            astring1 = (String[])ObjectArrays.concat(complete, p_146406_1_, String.class);
            i = astring1.length;
         }

         String s2;
         for(int s1 = 0; s1 < i; ++s1) {
            s2 = astring1[s1];
            if(s2.length() > 0) {
               this.field_146412_t.add(s2);
            }
         }

         String var7 = this.inputField.getText().substring(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false));
         s2 = StringUtils.getCommonPrefix(p_146406_1_);
         if(s2.length() > 0 && !var7.equalsIgnoreCase(s2)) {
            this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
            this.inputField.writeText(s2);
         } else if(this.field_146412_t.size() > 0) {
            this.field_146417_i = true;
            this.func_146404_p_();
         }
      }

   }

   public boolean func_73868_f() {
      return false;
   }

}
