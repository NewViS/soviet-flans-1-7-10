package net.minecraft.client.gui;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook$NextPageButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiScreenBook extends GuiScreen {

   private static final Logger logger = LogManager.getLogger();
   private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
   private final EntityPlayer editingPlayer;
   private final ItemStack bookObj;
   private final boolean bookIsUnsigned;
   private boolean field_146481_r;
   private boolean field_146480_s;
   private int updateCount;
   private int bookImageWidth = 192;
   private int bookImageHeight = 192;
   private int bookTotalPages = 1;
   private int currPage;
   private NBTTagList bookPages;
   private String bookTitle = "";
   private GuiScreenBook$NextPageButton buttonNextPage;
   private GuiScreenBook$NextPageButton buttonPreviousPage;
   private GuiButton buttonDone;
   private GuiButton buttonSign;
   private GuiButton buttonFinalize;
   private GuiButton buttonCancel;


   public GuiScreenBook(EntityPlayer var1, ItemStack var2, boolean var3) {
      this.editingPlayer = var1;
      this.bookObj = var2;
      this.bookIsUnsigned = var3;
      if(var2.hasTagCompound()) {
         NBTTagCompound var4 = var2.getTagCompound();
         this.bookPages = var4.getTagList("pages", 8);
         if(this.bookPages != null) {
            this.bookPages = (NBTTagList)this.bookPages.copy();
            this.bookTotalPages = this.bookPages.tagCount();
            if(this.bookTotalPages < 1) {
               this.bookTotalPages = 1;
            }
         }
      }

      if(this.bookPages == null && var3) {
         this.bookPages = new NBTTagList();
         this.bookPages.appendTag(new NBTTagString(""));
         this.bookTotalPages = 1;
      }

   }

   public void updateScreen() {
      super.updateScreen();
      ++this.updateCount;
   }

   public void initGui() {
      super.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      if(this.bookIsUnsigned) {
         super.buttonList.add(this.buttonSign = new GuiButton(3, super.width / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.signButton", new Object[0])));
         super.buttonList.add(this.buttonDone = new GuiButton(0, super.width / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.done", new Object[0])));
         super.buttonList.add(this.buttonFinalize = new GuiButton(5, super.width / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.finalizeButton", new Object[0])));
         super.buttonList.add(this.buttonCancel = new GuiButton(4, super.width / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.cancel", new Object[0])));
      } else {
         super.buttonList.add(this.buttonDone = new GuiButton(0, super.width / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.format("gui.done", new Object[0])));
      }

      int var1 = (super.width - this.bookImageWidth) / 2;
      byte var2 = 2;
      super.buttonList.add(this.buttonNextPage = new GuiScreenBook$NextPageButton(1, var1 + 120, var2 + 154, true));
      super.buttonList.add(this.buttonPreviousPage = new GuiScreenBook$NextPageButton(2, var1 + 38, var2 + 154, false));
      this.updateButtons();
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   private void updateButtons() {
      this.buttonNextPage.visible = !this.field_146480_s && (this.currPage < this.bookTotalPages - 1 || this.bookIsUnsigned);
      this.buttonPreviousPage.visible = !this.field_146480_s && this.currPage > 0;
      this.buttonDone.visible = !this.bookIsUnsigned || !this.field_146480_s;
      if(this.bookIsUnsigned) {
         this.buttonSign.visible = !this.field_146480_s;
         this.buttonCancel.visible = this.field_146480_s;
         this.buttonFinalize.visible = this.field_146480_s;
         this.buttonFinalize.enabled = this.bookTitle.trim().length() > 0;
      }

   }

   private void sendBookToServer(boolean var1) {
      if(this.bookIsUnsigned && this.field_146481_r) {
         if(this.bookPages != null) {
            String var2;
            while(this.bookPages.tagCount() > 1) {
               var2 = this.bookPages.getStringTagAt(this.bookPages.tagCount() - 1);
               if(var2.length() != 0) {
                  break;
               }

               this.bookPages.removeTag(this.bookPages.tagCount() - 1);
            }

            if(this.bookObj.hasTagCompound()) {
               NBTTagCompound var10 = this.bookObj.getTagCompound();
               var10.setTag("pages", this.bookPages);
            } else {
               this.bookObj.setTagInfo("pages", this.bookPages);
            }

            var2 = "MC|BEdit";
            if(var1) {
               var2 = "MC|BSign";
               this.bookObj.setTagInfo("author", new NBTTagString(this.editingPlayer.getCommandSenderName()));
               this.bookObj.setTagInfo("title", new NBTTagString(this.bookTitle.trim()));
               this.bookObj.func_150996_a(Items.written_book);
            }

            ByteBuf var3 = Unpooled.buffer();

            try {
               (new PacketBuffer(var3)).writeItemStackToBuffer(this.bookObj);
               super.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload(var2, var3));
            } catch (Exception var8) {
               logger.error("Couldn\'t send book info", var8);
            } finally {
               var3.release();
            }
         }

      }
   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 0) {
            super.mc.displayGuiScreen((GuiScreen)null);
            this.sendBookToServer(false);
         } else if(var1.id == 3 && this.bookIsUnsigned) {
            this.field_146480_s = true;
         } else if(var1.id == 1) {
            if(this.currPage < this.bookTotalPages - 1) {
               ++this.currPage;
            } else if(this.bookIsUnsigned) {
               this.addNewPage();
               if(this.currPage < this.bookTotalPages - 1) {
                  ++this.currPage;
               }
            }
         } else if(var1.id == 2) {
            if(this.currPage > 0) {
               --this.currPage;
            }
         } else if(var1.id == 5 && this.field_146480_s) {
            this.sendBookToServer(true);
            super.mc.displayGuiScreen((GuiScreen)null);
         } else if(var1.id == 4 && this.field_146480_s) {
            this.field_146480_s = false;
         }

         this.updateButtons();
      }
   }

   private void addNewPage() {
      if(this.bookPages != null && this.bookPages.tagCount() < 50) {
         this.bookPages.appendTag(new NBTTagString(""));
         ++this.bookTotalPages;
         this.field_146481_r = true;
      }
   }

   protected void keyTyped(char var1, int var2) {
      super.keyTyped(var1, var2);
      if(this.bookIsUnsigned) {
         if(this.field_146480_s) {
            this.func_146460_c(var1, var2);
         } else {
            this.keyTypedInBook(var1, var2);
         }

      }
   }

   private void keyTypedInBook(char var1, int var2) {
      switch(var1) {
      case 22:
         this.func_146459_b(GuiScreen.getClipboardString());
         return;
      default:
         switch(var2) {
         case 14:
            String var3 = this.func_146456_p();
            if(var3.length() > 0) {
               this.func_146457_a(var3.substring(0, var3.length() - 1));
            }

            return;
         case 28:
         case 156:
            this.func_146459_b("\n");
            return;
         default:
            if(ChatAllowedCharacters.isAllowedCharacter(var1)) {
               this.func_146459_b(Character.toString(var1));
            }
         }
      }
   }

   private void func_146460_c(char var1, int var2) {
      switch(var2) {
      case 14:
         if(!this.bookTitle.isEmpty()) {
            this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
            this.updateButtons();
         }

         return;
      case 28:
      case 156:
         if(!this.bookTitle.isEmpty()) {
            this.sendBookToServer(true);
            super.mc.displayGuiScreen((GuiScreen)null);
         }

         return;
      default:
         if(this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(var1)) {
            this.bookTitle = this.bookTitle + Character.toString(var1);
            this.updateButtons();
            this.field_146481_r = true;
         }

      }
   }

   private String func_146456_p() {
      return this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()?this.bookPages.getStringTagAt(this.currPage):"";
   }

   private void func_146457_a(String var1) {
      if(this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
         this.bookPages.func_150304_a(this.currPage, new NBTTagString(var1));
         this.field_146481_r = true;
      }

   }

   private void func_146459_b(String var1) {
      String var2 = this.func_146456_p();
      String var3 = var2 + var1;
      int var4 = super.fontRendererObj.splitStringWidth(var3 + "" + EnumChatFormatting.BLACK + "_", 118);
      if(var4 <= 118 && var3.length() < 256) {
         this.func_146457_a(var3);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(bookGuiTextures);
      int var4 = (super.width - this.bookImageWidth) / 2;
      byte var5 = 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.bookImageWidth, this.bookImageHeight);
      String var6;
      String var7;
      int var8;
      if(this.field_146480_s) {
         var6 = this.bookTitle;
         if(this.bookIsUnsigned) {
            if(this.updateCount / 6 % 2 == 0) {
               var6 = var6 + "" + EnumChatFormatting.BLACK + "_";
            } else {
               var6 = var6 + "" + EnumChatFormatting.GRAY + "_";
            }
         }

         var7 = I18n.format("book.editTitle", new Object[0]);
         var8 = super.fontRendererObj.getStringWidth(var7);
         super.fontRendererObj.drawString(var7, var4 + 36 + (116 - var8) / 2, var5 + 16 + 16, 0);
         int var9 = super.fontRendererObj.getStringWidth(var6);
         super.fontRendererObj.drawString(var6, var4 + 36 + (116 - var9) / 2, var5 + 48, 0);
         String var10 = I18n.format("book.byAuthor", new Object[]{this.editingPlayer.getCommandSenderName()});
         int var11 = super.fontRendererObj.getStringWidth(var10);
         super.fontRendererObj.drawString(EnumChatFormatting.DARK_GRAY + var10, var4 + 36 + (116 - var11) / 2, var5 + 48 + 10, 0);
         String var12 = I18n.format("book.finalizeWarning", new Object[0]);
         super.fontRendererObj.drawSplitString(var12, var4 + 36, var5 + 80, 116, 0);
      } else {
         var6 = I18n.format("book.pageIndicator", new Object[]{Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages)});
         var7 = "";
         if(this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
            var7 = this.bookPages.getStringTagAt(this.currPage);
         }

         if(this.bookIsUnsigned) {
            if(super.fontRendererObj.getBidiFlag()) {
               var7 = var7 + "_";
            } else if(this.updateCount / 6 % 2 == 0) {
               var7 = var7 + "" + EnumChatFormatting.BLACK + "_";
            } else {
               var7 = var7 + "" + EnumChatFormatting.GRAY + "_";
            }
         }

         var8 = super.fontRendererObj.getStringWidth(var6);
         super.fontRendererObj.drawString(var6, var4 - var8 + this.bookImageWidth - 44, var5 + 16, 0);
         super.fontRendererObj.drawSplitString(var7, var4 + 36, var5 + 16 + 16, 116, 0);
      }

      super.drawScreen(var1, var2, var3);
   }

   // $FF: synthetic method
   static ResourceLocation access$000() {
      return bookGuiTextures;
   }

}
