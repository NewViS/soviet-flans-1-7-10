package net.minecraft.realms;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.GL11;

public class RealmsEditBox {

   public static final int BACKWARDS = -1;
   public static final int FORWARDS = 1;
   private static final int CURSOR_INSERT_WIDTH = 1;
   private static final int CURSOR_INSERT_COLOR = -3092272;
   private static final String CURSOR_APPEND_CHARACTER = "_";
   private final FontRenderer font;
   private final int x;
   private final int y;
   private final int width;
   private final int height;
   private String value;
   private int maxLength;
   private int frame;
   private boolean bordered;
   private boolean canLoseFocus;
   private boolean inFocus;
   private boolean isEditable;
   private int displayPos;
   private int cursorPos;
   private int highlightPos;
   private int textColor;
   private int textColorUneditable;
   private boolean visible;


   public RealmsEditBox(int var1, int var2, int var3, int var4) {
      this(Minecraft.getMinecraft().fontRenderer, var1, var2, var3, var4);
   }

   public RealmsEditBox(FontRenderer var1, int var2, int var3, int var4, int var5) {
      this.value = "";
      this.maxLength = 32;
      this.bordered = true;
      this.canLoseFocus = true;
      this.isEditable = true;
      this.textColor = 14737632;
      this.textColorUneditable = 7368816;
      this.visible = true;
      this.font = var1;
      this.x = var2;
      this.y = var3;
      this.width = var4;
      this.height = var5;
   }

   public void tick() {
      ++this.frame;
   }

   public void setValue(String var1) {
      if(var1.length() > this.maxLength) {
         this.value = var1.substring(0, this.maxLength);
      } else {
         this.value = var1;
      }

      this.moveCursorToEnd();
   }

   public String getValue() {
      return this.value;
   }

   public String getHighlighted() {
      int var1 = this.cursorPos < this.highlightPos?this.cursorPos:this.highlightPos;
      int var2 = this.cursorPos < this.highlightPos?this.highlightPos:this.cursorPos;
      return this.value.substring(var1, var2);
   }

   public void insertText(String var1) {
      String var2 = "";
      String var3 = ChatAllowedCharacters.filerAllowedCharacters(var1);
      int var4 = this.cursorPos < this.highlightPos?this.cursorPos:this.highlightPos;
      int var5 = this.cursorPos < this.highlightPos?this.highlightPos:this.cursorPos;
      int var6 = this.maxLength - this.value.length() - (var4 - this.highlightPos);
      boolean var7 = false;
      if(this.value.length() > 0) {
         var2 = var2 + this.value.substring(0, var4);
      }

      int var8;
      if(var6 < var3.length()) {
         var2 = var2 + var3.substring(0, var6);
         var8 = var6;
      } else {
         var2 = var2 + var3;
         var8 = var3.length();
      }

      if(this.value.length() > 0 && var5 < this.value.length()) {
         var2 = var2 + this.value.substring(var5);
      }

      this.value = var2;
      this.moveCursor(var4 - this.highlightPos + var8);
   }

   public void deleteWords(int var1) {
      if(this.value.length() != 0) {
         if(this.highlightPos != this.cursorPos) {
            this.insertText("");
         } else {
            this.deleteChars(this.getWordPosition(var1) - this.cursorPos);
         }
      }
   }

   public void deleteChars(int var1) {
      if(this.value.length() != 0) {
         if(this.highlightPos != this.cursorPos) {
            this.insertText("");
         } else {
            boolean var2 = var1 < 0;
            int var3 = var2?this.cursorPos + var1:this.cursorPos;
            int var4 = var2?this.cursorPos:this.cursorPos + var1;
            String var5 = "";
            if(var3 >= 0) {
               var5 = this.value.substring(0, var3);
            }

            if(var4 < this.value.length()) {
               var5 = var5 + this.value.substring(var4);
            }

            this.value = var5;
            if(var2) {
               this.moveCursor(var1);
            }

         }
      }
   }

   public int getWordPosition(int var1) {
      return this.getWordPosition(var1, this.getCursorPosition());
   }

   public int getWordPosition(int var1, int var2) {
      return this.getWordPosition(var1, this.getCursorPosition(), true);
   }

   public int getWordPosition(int var1, int var2, boolean var3) {
      int var4 = var2;
      boolean var5 = var1 < 0;
      int var6 = Math.abs(var1);

      for(int var7 = 0; var7 < var6; ++var7) {
         if(var5) {
            while(var3 && var4 > 0 && this.value.charAt(var4 - 1) == 32) {
               --var4;
            }

            while(var4 > 0 && this.value.charAt(var4 - 1) != 32) {
               --var4;
            }
         } else {
            int var8 = this.value.length();
            var4 = this.value.indexOf(32, var4);
            if(var4 == -1) {
               var4 = var8;
            } else {
               while(var3 && var4 < var8 && this.value.charAt(var4) == 32) {
                  ++var4;
               }
            }
         }
      }

      return var4;
   }

   public void moveCursor(int var1) {
      this.moveCursorTo(this.highlightPos + var1);
   }

   public void moveCursorTo(int var1) {
      this.cursorPos = var1;
      int var2 = this.value.length();
      if(this.cursorPos < 0) {
         this.cursorPos = 0;
      }

      if(this.cursorPos > var2) {
         this.cursorPos = var2;
      }

      this.setHighlightPos(this.cursorPos);
   }

   public void moveCursorToStart() {
      this.moveCursorTo(0);
   }

   public void moveCursorToEnd() {
      this.moveCursorTo(this.value.length());
   }

   public boolean keyPressed(char var1, int var2) {
      if(!this.inFocus) {
         return false;
      } else {
         switch(var1) {
         case 1:
            this.moveCursorToEnd();
            this.setHighlightPos(0);
            return true;
         case 3:
            GuiScreen.setClipboardString(this.getHighlighted());
            return true;
         case 22:
            if(this.isEditable) {
               this.insertText(GuiScreen.getClipboardString());
            }

            return true;
         case 24:
            GuiScreen.setClipboardString(this.getHighlighted());
            if(this.isEditable) {
               this.insertText("");
            }

            return true;
         default:
            switch(var2) {
            case 14:
               if(GuiScreen.isCtrlKeyDown()) {
                  if(this.isEditable) {
                     this.deleteWords(-1);
                  }
               } else if(this.isEditable) {
                  this.deleteChars(-1);
               }

               return true;
            case 199:
               if(GuiScreen.isShiftKeyDown()) {
                  this.setHighlightPos(0);
               } else {
                  this.moveCursorToStart();
               }

               return true;
            case 203:
               if(GuiScreen.isShiftKeyDown()) {
                  if(GuiScreen.isCtrlKeyDown()) {
                     this.setHighlightPos(this.getWordPosition(-1, this.getHighlightPos()));
                  } else {
                     this.setHighlightPos(this.getHighlightPos() - 1);
                  }
               } else if(GuiScreen.isCtrlKeyDown()) {
                  this.moveCursorTo(this.getWordPosition(-1));
               } else {
                  this.moveCursor(-1);
               }

               return true;
            case 205:
               if(GuiScreen.isShiftKeyDown()) {
                  if(GuiScreen.isCtrlKeyDown()) {
                     this.setHighlightPos(this.getWordPosition(1, this.getHighlightPos()));
                  } else {
                     this.setHighlightPos(this.getHighlightPos() + 1);
                  }
               } else if(GuiScreen.isCtrlKeyDown()) {
                  this.moveCursorTo(this.getWordPosition(1));
               } else {
                  this.moveCursor(1);
               }

               return true;
            case 207:
               if(GuiScreen.isShiftKeyDown()) {
                  this.setHighlightPos(this.value.length());
               } else {
                  this.moveCursorToEnd();
               }

               return true;
            case 211:
               if(GuiScreen.isCtrlKeyDown()) {
                  if(this.isEditable) {
                     this.deleteWords(1);
                  }
               } else if(this.isEditable) {
                  this.deleteChars(1);
               }

               return true;
            default:
               if(ChatAllowedCharacters.isAllowedCharacter(var1)) {
                  if(this.isEditable) {
                     this.insertText(Character.toString(var1));
                  }

                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   public void mouseClicked(int var1, int var2, int var3) {
      boolean var4 = var1 >= this.x && var1 < this.x + this.width && var2 >= this.y && var2 < this.y + this.height;
      if(this.canLoseFocus) {
         this.setFocus(var4);
      }

      if(this.inFocus && var3 == 0) {
         int var5 = var1 - this.x;
         if(this.bordered) {
            var5 -= 4;
         }

         String var6 = this.font.trimStringToWidth(this.value.substring(this.displayPos), this.getInnerWidth());
         this.moveCursorTo(this.font.trimStringToWidth(var6, var5).length() + this.displayPos);
      }

   }

   public void render() {
      if(this.isVisible()) {
         if(this.isBordered()) {
            Gui.drawRect(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
            Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
         }

         int var1 = this.isEditable?this.textColor:this.textColorUneditable;
         int var2 = this.cursorPos - this.displayPos;
         int var3 = this.highlightPos - this.displayPos;
         String var4 = this.font.trimStringToWidth(this.value.substring(this.displayPos), this.getInnerWidth());
         boolean var5 = var2 >= 0 && var2 <= var4.length();
         boolean var6 = this.inFocus && this.frame / 6 % 2 == 0 && var5;
         int var7 = this.bordered?this.x + 4:this.x;
         int var8 = this.bordered?this.y + (this.height - 8) / 2:this.y;
         int var9 = var7;
         if(var3 > var4.length()) {
            var3 = var4.length();
         }

         if(var4.length() > 0) {
            String var10 = var5?var4.substring(0, var2):var4;
            var9 = this.font.drawStringWithShadow(var10, var7, var8, var1);
         }

         boolean var13 = this.cursorPos < this.value.length() || this.value.length() >= this.getMaxLength();
         int var11 = var9;
         if(!var5) {
            var11 = var2 > 0?var7 + this.width:var7;
         } else if(var13) {
            var11 = var9 - 1;
            --var9;
         }

         if(var4.length() > 0 && var5 && var2 < var4.length()) {
            this.font.drawStringWithShadow(var4.substring(var2), var9, var8, var1);
         }

         if(var6) {
            if(var13) {
               Gui.drawRect(var11, var8 - 1, var11 + 1, var8 + 1 + this.font.FONT_HEIGHT, -3092272);
            } else {
               this.font.drawStringWithShadow("_", var11, var8, var1);
            }
         }

         if(var3 != var2) {
            int var12 = var7 + this.font.getStringWidth(var4.substring(0, var3));
            this.renderHighlight(var11, var8 - 1, var12 - 1, var8 + 1 + this.font.FONT_HEIGHT);
         }

      }
   }

   private void renderHighlight(int var1, int var2, int var3, int var4) {
      int var5;
      if(var1 < var3) {
         var5 = var1;
         var1 = var3;
         var3 = var5;
      }

      if(var2 < var4) {
         var5 = var2;
         var2 = var4;
         var4 = var5;
      }

      if(var3 > this.x + this.width) {
         var3 = this.x + this.width;
      }

      if(var1 > this.x + this.width) {
         var1 = this.x + this.width;
      }

      Tessellator var6 = Tessellator.instance;
      GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
      GL11.glDisable(3553);
      GL11.glEnable(3058);
      GL11.glLogicOp(5387);
      var6.startDrawingQuads();
      var6.addVertex((double)var1, (double)var4, 0.0D);
      var6.addVertex((double)var3, (double)var4, 0.0D);
      var6.addVertex((double)var3, (double)var2, 0.0D);
      var6.addVertex((double)var1, (double)var2, 0.0D);
      var6.draw();
      GL11.glDisable(3058);
      GL11.glEnable(3553);
   }

   public void setMaxLength(int var1) {
      this.maxLength = var1;
      if(this.value.length() > var1) {
         this.value = this.value.substring(0, var1);
      }

   }

   public int getMaxLength() {
      return this.maxLength;
   }

   public int getCursorPosition() {
      return this.cursorPos;
   }

   public boolean isBordered() {
      return this.bordered;
   }

   public void setBordered(boolean var1) {
      this.bordered = var1;
   }

   public int getTextColor() {
      return this.textColor;
   }

   public void setTextColor(int var1) {
      this.textColor = var1;
   }

   public int getTextColorUneditable() {
      return this.textColorUneditable;
   }

   public void setTextColorUneditable(int var1) {
      this.textColorUneditable = var1;
   }

   public void setFocus(boolean var1) {
      if(var1 && !this.inFocus) {
         this.frame = 0;
      }

      this.inFocus = var1;
   }

   public boolean isFocused() {
      return this.inFocus;
   }

   public boolean isIsEditable() {
      return this.isEditable;
   }

   public void setIsEditable(boolean var1) {
      this.isEditable = var1;
   }

   public int getHighlightPos() {
      return this.highlightPos;
   }

   public int getInnerWidth() {
      return this.isBordered()?this.width - 8:this.width;
   }

   public void setHighlightPos(int var1) {
      int var2 = this.value.length();
      if(var1 > var2) {
         var1 = var2;
      }

      if(var1 < 0) {
         var1 = 0;
      }

      this.highlightPos = var1;
      if(this.font != null) {
         if(this.displayPos > var2) {
            this.displayPos = var2;
         }

         int var3 = this.getInnerWidth();
         String var4 = this.font.trimStringToWidth(this.value.substring(this.displayPos), var3);
         int var5 = var4.length() + this.displayPos;
         if(var1 == this.displayPos) {
            this.displayPos -= this.font.trimStringToWidth(this.value, var3, true).length();
         }

         if(var1 > var5) {
            this.displayPos += var1 - var5;
         } else if(var1 <= this.displayPos) {
            this.displayPos -= this.displayPos - var1;
         }

         if(this.displayPos < 0) {
            this.displayPos = 0;
         }

         if(this.displayPos > var2) {
            this.displayPos = var2;
         }
      }

   }

   public boolean isCanLoseFocus() {
      return this.canLoseFocus;
   }

   public void setCanLoseFocus(boolean var1) {
      this.canLoseFocus = var1;
   }

   public boolean isVisible() {
      return this.visible;
   }

   public void setVisible(boolean var1) {
      this.visible = var1;
   }
}
