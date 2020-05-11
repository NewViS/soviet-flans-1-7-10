package net.minecraft.client.gui.achievement;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiAchievements extends GuiScreen implements IProgressMeter {

   private static final int field_146572_y = AchievementList.minDisplayColumn * 24 - 112;
   private static final int field_146571_z = AchievementList.minDisplayRow * 24 - 112;
   private static final int field_146559_A = AchievementList.maxDisplayColumn * 24 - 77;
   private static final int field_146560_B = AchievementList.maxDisplayRow * 24 - 77;
   private static final ResourceLocation field_146561_C = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   protected GuiScreen field_146562_a;
   protected int field_146555_f = 256;
   protected int field_146557_g = 202;
   protected int field_146563_h;
   protected int field_146564_i;
   protected float field_146570_r = 1.0F;
   protected double field_146569_s;
   protected double field_146568_t;
   protected double field_146567_u;
   protected double field_146566_v;
   protected double field_146565_w;
   protected double field_146573_x;
   private int field_146554_D;
   private StatFileWriter field_146556_E;
   private boolean field_146558_F = true;


   public GuiAchievements(GuiScreen var1, StatFileWriter var2) {
      this.field_146562_a = var1;
      this.field_146556_E = var2;
      short var3 = 141;
      short var4 = 141;
      this.field_146569_s = this.field_146567_u = this.field_146565_w = (double)(AchievementList.openInventory.displayColumn * 24 - var3 / 2 - 12);
      this.field_146568_t = this.field_146566_v = this.field_146573_x = (double)(AchievementList.openInventory.displayRow * 24 - var4 / 2);
   }

   public void initGui() {
      super.mc.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus$EnumState.REQUEST_STATS));
      super.buttonList.clear();
      super.buttonList.add(new GuiOptionButton(1, super.width / 2 + 24, super.height / 2 + 74, 80, 20, I18n.format("gui.done", new Object[0])));
   }

   protected void actionPerformed(GuiButton var1) {
      if(!this.field_146558_F) {
         if(var1.id == 1) {
            super.mc.displayGuiScreen(this.field_146562_a);
         }

      }
   }

   protected void keyTyped(char var1, int var2) {
      if(var2 == super.mc.gameSettings.keyBindInventory.getKeyCode()) {
         super.mc.displayGuiScreen((GuiScreen)null);
         super.mc.setIngameFocus();
      } else {
         super.keyTyped(var1, var2);
      }

   }

   public void drawScreen(int var1, int var2, float var3) {
      if(this.field_146558_F) {
         this.drawDefaultBackground();
         this.drawCenteredString(super.fontRendererObj, I18n.format("multiplayer.downloadingStats", new Object[0]), super.width / 2, super.height / 2, 16777215);
         this.drawCenteredString(super.fontRendererObj, IProgressMeter.field_146510_b_[(int)(Minecraft.getSystemTime() / 150L % (long)IProgressMeter.field_146510_b_.length)], super.width / 2, super.height / 2 + super.fontRendererObj.FONT_HEIGHT * 2, 16777215);
      } else {
         int var4;
         if(Mouse.isButtonDown(0)) {
            var4 = (super.width - this.field_146555_f) / 2;
            int var5 = (super.height - this.field_146557_g) / 2;
            int var6 = var4 + 8;
            int var7 = var5 + 17;
            if((this.field_146554_D == 0 || this.field_146554_D == 1) && var1 >= var6 && var1 < var6 + 224 && var2 >= var7 && var2 < var7 + 155) {
               if(this.field_146554_D == 0) {
                  this.field_146554_D = 1;
               } else {
                  this.field_146567_u -= (double)((float)(var1 - this.field_146563_h) * this.field_146570_r);
                  this.field_146566_v -= (double)((float)(var2 - this.field_146564_i) * this.field_146570_r);
                  this.field_146565_w = this.field_146569_s = this.field_146567_u;
                  this.field_146573_x = this.field_146568_t = this.field_146566_v;
               }

               this.field_146563_h = var1;
               this.field_146564_i = var2;
            }
         } else {
            this.field_146554_D = 0;
         }

         var4 = Mouse.getDWheel();
         float var11 = this.field_146570_r;
         if(var4 < 0) {
            this.field_146570_r += 0.25F;
         } else if(var4 > 0) {
            this.field_146570_r -= 0.25F;
         }

         this.field_146570_r = MathHelper.clamp_float(this.field_146570_r, 1.0F, 2.0F);
         if(this.field_146570_r != var11) {
            float var10000 = var11 - this.field_146570_r;
            float var12 = var11 * (float)this.field_146555_f;
            float var8 = var11 * (float)this.field_146557_g;
            float var9 = this.field_146570_r * (float)this.field_146555_f;
            float var10 = this.field_146570_r * (float)this.field_146557_g;
            this.field_146567_u -= (double)((var9 - var12) * 0.5F);
            this.field_146566_v -= (double)((var10 - var8) * 0.5F);
            this.field_146565_w = this.field_146569_s = this.field_146567_u;
            this.field_146573_x = this.field_146568_t = this.field_146566_v;
         }

         if(this.field_146565_w < (double)field_146572_y) {
            this.field_146565_w = (double)field_146572_y;
         }

         if(this.field_146573_x < (double)field_146571_z) {
            this.field_146573_x = (double)field_146571_z;
         }

         if(this.field_146565_w >= (double)field_146559_A) {
            this.field_146565_w = (double)(field_146559_A - 1);
         }

         if(this.field_146573_x >= (double)field_146560_B) {
            this.field_146573_x = (double)(field_146560_B - 1);
         }

         this.drawDefaultBackground();
         this.func_146552_b(var1, var2, var3);
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         this.func_146553_h();
         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }

   }

   public void func_146509_g() {
      if(this.field_146558_F) {
         this.field_146558_F = false;
      }

   }

   public void updateScreen() {
      if(!this.field_146558_F) {
         this.field_146569_s = this.field_146567_u;
         this.field_146568_t = this.field_146566_v;
         double var1 = this.field_146565_w - this.field_146567_u;
         double var3 = this.field_146573_x - this.field_146566_v;
         if(var1 * var1 + var3 * var3 < 4.0D) {
            this.field_146567_u += var1;
            this.field_146566_v += var3;
         } else {
            this.field_146567_u += var1 * 0.85D;
            this.field_146566_v += var3 * 0.85D;
         }

      }
   }

   protected void func_146553_h() {
      int var1 = (super.width - this.field_146555_f) / 2;
      int var2 = (super.height - this.field_146557_g) / 2;
      super.fontRendererObj.drawString(I18n.format("gui.achievements", new Object[0]), var1 + 15, var2 + 5, 4210752);
   }

   protected void func_146552_b(int var1, int var2, float var3) {
      int var4 = MathHelper.floor_double(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * (double)var3);
      int var5 = MathHelper.floor_double(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * (double)var3);
      if(var4 < field_146572_y) {
         var4 = field_146572_y;
      }

      if(var5 < field_146571_z) {
         var5 = field_146571_z;
      }

      if(var4 >= field_146559_A) {
         var4 = field_146559_A - 1;
      }

      if(var5 >= field_146560_B) {
         var5 = field_146560_B - 1;
      }

      int var6 = (super.width - this.field_146555_f) / 2;
      int var7 = (super.height - this.field_146557_g) / 2;
      int var8 = var6 + 16;
      int var9 = var7 + 17;
      super.zLevel = 0.0F;
      GL11.glDepthFunc(518);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)var8, (float)var9, -200.0F);
      GL11.glScalef(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 0.0F);
      GL11.glEnable(3553);
      GL11.glDisable(2896);
      GL11.glEnable('\u803a');
      GL11.glEnable(2903);
      int var10 = var4 + 288 >> 4;
      int var11 = var5 + 288 >> 4;
      int var12 = (var4 + 288) % 16;
      int var13 = (var5 + 288) % 16;
      boolean var14 = true;
      boolean var15 = true;
      boolean var16 = true;
      boolean var17 = true;
      boolean var18 = true;
      Random var19 = new Random();
      float var20 = 16.0F / this.field_146570_r;
      float var21 = 16.0F / this.field_146570_r;

      int var22;
      int var24;
      int var25;
      for(var22 = 0; (float)var22 * var20 - (float)var13 < 155.0F; ++var22) {
         float var23 = 0.6F - (float)(var11 + var22) / 25.0F * 0.3F;
         GL11.glColor4f(var23, var23, var23, 1.0F);

         for(var24 = 0; (float)var24 * var21 - (float)var12 < 224.0F; ++var24) {
            var19.setSeed((long)(super.mc.getSession().getPlayerID().hashCode() + var10 + var24 + (var11 + var22) * 16));
            var25 = var19.nextInt(1 + var11 + var22) + (var11 + var22) / 2;
            IIcon var26 = Blocks.sand.getIcon(0, 0);
            if(var25 <= 37 && var11 + var22 != 35) {
               if(var25 == 22) {
                  if(var19.nextInt(2) == 0) {
                     var26 = Blocks.diamond_ore.getIcon(0, 0);
                  } else {
                     var26 = Blocks.redstone_ore.getIcon(0, 0);
                  }
               } else if(var25 == 10) {
                  var26 = Blocks.iron_ore.getIcon(0, 0);
               } else if(var25 == 8) {
                  var26 = Blocks.coal_ore.getIcon(0, 0);
               } else if(var25 > 4) {
                  var26 = Blocks.stone.getIcon(0, 0);
               } else if(var25 > 0) {
                  var26 = Blocks.dirt.getIcon(0, 0);
               }
            } else {
               var26 = Blocks.bedrock.getIcon(0, 0);
            }

            super.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            this.drawTexturedModelRectFromIcon(var24 * 16 - var12, var22 * 16 - var13, var26, 16, 16);
         }
      }

      GL11.glEnable(2929);
      GL11.glDepthFunc(515);
      super.mc.getTextureManager().bindTexture(field_146561_C);

      int var30;
      int var31;
      int var42;
      for(var22 = 0; var22 < AchievementList.achievementList.size(); ++var22) {
         Achievement var35 = (Achievement)AchievementList.achievementList.get(var22);
         if(var35.parentAchievement != null) {
            var24 = var35.displayColumn * 24 - var4 + 11;
            var25 = var35.displayRow * 24 - var5 + 11;
            var42 = var35.parentAchievement.displayColumn * 24 - var4 + 11;
            int var27 = var35.parentAchievement.displayRow * 24 - var5 + 11;
            boolean var28 = this.field_146556_E.hasAchievementUnlocked(var35);
            boolean var29 = this.field_146556_E.canUnlockAchievement(var35);
            var30 = this.field_146556_E.func_150874_c(var35);
            if(var30 <= 4) {
               var31 = -16777216;
               if(var28) {
                  var31 = -6250336;
               } else if(var29) {
                  var31 = -16711936;
               }

               this.drawHorizontalLine(var24, var42, var25, var31);
               this.drawVerticalLine(var42, var25, var27, var31);
               if(var24 > var42) {
                  this.drawTexturedModalRect(var24 - 11 - 7, var25 - 5, 114, 234, 7, 11);
               } else if(var24 < var42) {
                  this.drawTexturedModalRect(var24 + 11, var25 - 5, 107, 234, 7, 11);
               } else if(var25 > var27) {
                  this.drawTexturedModalRect(var24 - 5, var25 - 11 - 7, 96, 234, 11, 7);
               } else if(var25 < var27) {
                  this.drawTexturedModalRect(var24 - 5, var25 + 11, 96, 241, 11, 7);
               }
            }
         }
      }

      Achievement var34 = null;
      RenderItem var36 = new RenderItem();
      float var37 = (float)(var1 - var8) * this.field_146570_r;
      float var38 = (float)(var2 - var9) * this.field_146570_r;
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glEnable('\u803a');
      GL11.glEnable(2903);

      int var41;
      int var43;
      for(var42 = 0; var42 < AchievementList.achievementList.size(); ++var42) {
         Achievement var39 = (Achievement)AchievementList.achievementList.get(var42);
         var41 = var39.displayColumn * 24 - var4;
         var43 = var39.displayRow * 24 - var5;
         if(var41 >= -24 && var43 >= -24 && (float)var41 <= 224.0F * this.field_146570_r && (float)var43 <= 155.0F * this.field_146570_r) {
            var30 = this.field_146556_E.func_150874_c(var39);
            float var45;
            if(this.field_146556_E.hasAchievementUnlocked(var39)) {
               var45 = 0.75F;
               GL11.glColor4f(var45, var45, var45, 1.0F);
            } else if(this.field_146556_E.canUnlockAchievement(var39)) {
               var45 = 1.0F;
               GL11.glColor4f(var45, var45, var45, 1.0F);
            } else if(var30 < 3) {
               var45 = 0.3F;
               GL11.glColor4f(var45, var45, var45, 1.0F);
            } else if(var30 == 3) {
               var45 = 0.2F;
               GL11.glColor4f(var45, var45, var45, 1.0F);
            } else {
               if(var30 != 4) {
                  continue;
               }

               var45 = 0.1F;
               GL11.glColor4f(var45, var45, var45, 1.0F);
            }

            super.mc.getTextureManager().bindTexture(field_146561_C);
            if(var39.getSpecial()) {
               this.drawTexturedModalRect(var41 - 2, var43 - 2, 26, 202, 26, 26);
            } else {
               this.drawTexturedModalRect(var41 - 2, var43 - 2, 0, 202, 26, 26);
            }

            if(!this.field_146556_E.canUnlockAchievement(var39)) {
               var45 = 0.1F;
               GL11.glColor4f(var45, var45, var45, 1.0F);
               var36.renderWithColor = false;
            }

            GL11.glEnable(2896);
            GL11.glEnable(2884);
            var36.renderItemAndEffectIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), var39.theItemStack, var41 + 3, var43 + 3);
            GL11.glBlendFunc(770, 771);
            GL11.glDisable(2896);
            if(!this.field_146556_E.canUnlockAchievement(var39)) {
               var36.renderWithColor = true;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            if(var37 >= (float)var41 && var37 <= (float)(var41 + 22) && var38 >= (float)var43 && var38 <= (float)(var43 + 22)) {
               var34 = var39;
            }
         }
      }

      GL11.glDisable(2929);
      GL11.glEnable(3042);
      GL11.glPopMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(field_146561_C);
      this.drawTexturedModalRect(var6, var7, 0, 0, this.field_146555_f, this.field_146557_g);
      super.zLevel = 0.0F;
      GL11.glDepthFunc(515);
      GL11.glDisable(2929);
      GL11.glEnable(3553);
      super.drawScreen(var1, var2, var3);
      if(var34 != null) {
         String var44 = var34.func_150951_e().getUnformattedText();
         String var40 = var34.getDescription();
         var41 = var1 + 12;
         var43 = var2 - 4;
         var30 = this.field_146556_E.func_150874_c(var34);
         if(!this.field_146556_E.canUnlockAchievement(var34)) {
            String var32;
            int var33;
            if(var30 == 3) {
               var44 = I18n.format("achievement.unknown", new Object[0]);
               var31 = Math.max(super.fontRendererObj.getStringWidth(var44), 120);
               var32 = (new ChatComponentTranslation("achievement.requires", new Object[]{var34.parentAchievement.func_150951_e()})).getUnformattedText();
               var33 = super.fontRendererObj.splitStringWidth(var32, var31);
               this.drawGradientRect(var41 - 3, var43 - 3, var41 + var31 + 3, var43 + var33 + 12 + 3, -1073741824, -1073741824);
               super.fontRendererObj.drawSplitString(var32, var41, var43 + 12, var31, -9416624);
            } else if(var30 < 3) {
               var31 = Math.max(super.fontRendererObj.getStringWidth(var44), 120);
               var32 = (new ChatComponentTranslation("achievement.requires", new Object[]{var34.parentAchievement.func_150951_e()})).getUnformattedText();
               var33 = super.fontRendererObj.splitStringWidth(var32, var31);
               this.drawGradientRect(var41 - 3, var43 - 3, var41 + var31 + 3, var43 + var33 + 12 + 3, -1073741824, -1073741824);
               super.fontRendererObj.drawSplitString(var32, var41, var43 + 12, var31, -9416624);
            } else {
               var44 = null;
            }
         } else {
            var31 = Math.max(super.fontRendererObj.getStringWidth(var44), 120);
            int var46 = super.fontRendererObj.splitStringWidth(var40, var31);
            if(this.field_146556_E.hasAchievementUnlocked(var34)) {
               var46 += 12;
            }

            this.drawGradientRect(var41 - 3, var43 - 3, var41 + var31 + 3, var43 + var46 + 3 + 12, -1073741824, -1073741824);
            super.fontRendererObj.drawSplitString(var40, var41, var43 + 12, var31, -6250336);
            if(this.field_146556_E.hasAchievementUnlocked(var34)) {
               super.fontRendererObj.drawStringWithShadow(I18n.format("achievement.taken", new Object[0]), var41, var43 + var46 + 4, -7302913);
            }
         }

         if(var44 != null) {
            super.fontRendererObj.drawStringWithShadow(var44, var41, var43, this.field_146556_E.canUnlockAchievement(var34)?(var34.getSpecial()?-128:-1):(var34.getSpecial()?-8355776:-8355712));
         }
      }

      GL11.glEnable(2929);
      GL11.glEnable(2896);
      RenderHelper.disableStandardItemLighting();
   }

   public boolean doesGuiPauseGame() {
      return !this.field_146558_F;
   }

}
