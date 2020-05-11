package net.minecraft.client.gui.achievement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.achievement.GuiStats$StatsBlock;
import net.minecraft.client.gui.achievement.GuiStats$StatsGeneral;
import net.minecraft.client.gui.achievement.GuiStats$StatsItem;
import net.minecraft.client.gui.achievement.GuiStats$StatsMobsList;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;
import net.minecraft.stats.StatFileWriter;
import org.lwjgl.opengl.GL11;

public class GuiStats extends GuiScreen implements IProgressMeter {

   private static RenderItem field_146544_g = new RenderItem();
   protected GuiScreen field_146549_a;
   protected String field_146542_f = "Select world";
   private GuiStats$StatsGeneral field_146550_h;
   private GuiStats$StatsItem field_146551_i;
   private GuiStats$StatsBlock field_146548_r;
   private GuiStats$StatsMobsList field_146547_s;
   private StatFileWriter field_146546_t;
   private GuiSlot field_146545_u;
   private boolean doesGuiPauseGame = true;


   public GuiStats(GuiScreen var1, StatFileWriter var2) {
      this.field_146549_a = var1;
      this.field_146546_t = var2;
   }

   public void initGui() {
      this.field_146542_f = I18n.format("gui.stats", new Object[0]);
      super.mc.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus$EnumState.REQUEST_STATS));
   }

   public void func_146541_h() {
      super.buttonList.add(new GuiButton(0, super.width / 2 + 4, super.height - 28, 150, 20, I18n.format("gui.done", new Object[0])));
      super.buttonList.add(new GuiButton(1, super.width / 2 - 160, super.height - 52, 80, 20, I18n.format("stat.generalButton", new Object[0])));
      GuiButton var1;
      super.buttonList.add(var1 = new GuiButton(2, super.width / 2 - 80, super.height - 52, 80, 20, I18n.format("stat.blocksButton", new Object[0])));
      GuiButton var2;
      super.buttonList.add(var2 = new GuiButton(3, super.width / 2, super.height - 52, 80, 20, I18n.format("stat.itemsButton", new Object[0])));
      GuiButton var3;
      super.buttonList.add(var3 = new GuiButton(4, super.width / 2 + 80, super.height - 52, 80, 20, I18n.format("stat.mobsButton", new Object[0])));
      if(this.field_146548_r.getSize() == 0) {
         var1.enabled = false;
      }

      if(this.field_146551_i.getSize() == 0) {
         var2.enabled = false;
      }

      if(this.field_146547_s.getSize() == 0) {
         var3.enabled = false;
      }

   }

   protected void actionPerformed(GuiButton var1) {
      if(var1.enabled) {
         if(var1.id == 0) {
            super.mc.displayGuiScreen(this.field_146549_a);
         } else if(var1.id == 1) {
            this.field_146545_u = this.field_146550_h;
         } else if(var1.id == 3) {
            this.field_146545_u = this.field_146551_i;
         } else if(var1.id == 2) {
            this.field_146545_u = this.field_146548_r;
         } else if(var1.id == 4) {
            this.field_146545_u = this.field_146547_s;
         } else {
            this.field_146545_u.actionPerformed(var1);
         }

      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      if(this.doesGuiPauseGame) {
         this.drawDefaultBackground();
         this.drawCenteredString(super.fontRendererObj, I18n.format("multiplayer.downloadingStats", new Object[0]), super.width / 2, super.height / 2, 16777215);
         this.drawCenteredString(super.fontRendererObj, IProgressMeter.field_146510_b_[(int)(Minecraft.getSystemTime() / 150L % (long)IProgressMeter.field_146510_b_.length)], super.width / 2, super.height / 2 + super.fontRendererObj.FONT_HEIGHT * 2, 16777215);
      } else {
         this.field_146545_u.drawScreen(var1, var2, var3);
         this.drawCenteredString(super.fontRendererObj, this.field_146542_f, super.width / 2, 20, 16777215);
         super.drawScreen(var1, var2, var3);
      }

   }

   public void func_146509_g() {
      if(this.doesGuiPauseGame) {
         this.field_146550_h = new GuiStats$StatsGeneral(this);
         this.field_146550_h.registerScrollButtons(1, 1);
         this.field_146551_i = new GuiStats$StatsItem(this);
         this.field_146551_i.registerScrollButtons(1, 1);
         this.field_146548_r = new GuiStats$StatsBlock(this);
         this.field_146548_r.registerScrollButtons(1, 1);
         this.field_146547_s = new GuiStats$StatsMobsList(this);
         this.field_146547_s.registerScrollButtons(1, 1);
         this.field_146545_u = this.field_146550_h;
         this.func_146541_h();
         this.doesGuiPauseGame = false;
      }

   }

   public boolean doesGuiPauseGame() {
      return !this.doesGuiPauseGame;
   }

   private void func_146521_a(int var1, int var2, Item var3) {
      this.drawButtonBackground(var1 + 1, var2 + 1);
      GL11.glEnable('\u803a');
      RenderHelper.enableGUIStandardItemLighting();
      field_146544_g.renderItemIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), new ItemStack(var3, 1, 0), var1 + 2, var2 + 2);
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable('\u803a');
   }

   private void drawButtonBackground(int var1, int var2) {
      this.drawSprite(var1, var2, 0, 0);
   }

   private void drawSprite(int var1, int var2, int var3, int var4) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.getTextureManager().bindTexture(Gui.statIcons);
      float var5 = 0.0078125F;
      float var6 = 0.0078125F;
      boolean var7 = true;
      boolean var8 = true;
      Tessellator var9 = Tessellator.instance;
      var9.startDrawingQuads();
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 18), (double)super.zLevel, (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 18), (double)(var2 + 18), (double)super.zLevel, (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 18) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 18), (double)(var2 + 0), (double)super.zLevel, (double)((float)(var3 + 18) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
      var9.addVertexWithUV((double)(var1 + 0), (double)(var2 + 0), (double)super.zLevel, (double)((float)(var3 + 0) * 0.0078125F), (double)((float)(var4 + 0) * 0.0078125F));
      var9.draw();
   }

   // $FF: synthetic method
   static Minecraft access$000(GuiStats var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static FontRenderer access$100(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static StatFileWriter access$200(GuiStats var0) {
      return var0.field_146546_t;
   }

   // $FF: synthetic method
   static FontRenderer access$300(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$400(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static Minecraft access$500(GuiStats var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static void access$600(GuiStats var0, int var1, int var2, int var3, int var4) {
      var0.drawSprite(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   static Minecraft access$700(GuiStats var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static FontRenderer access$800(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$900(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1000(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1100(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1200(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static void access$1300(GuiStats var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      var0.drawGradientRect(var1, var2, var3, var4, var5, var6);
   }

   // $FF: synthetic method
   static FontRenderer access$1400(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$1500(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static void access$1600(GuiStats var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      var0.drawGradientRect(var1, var2, var3, var4, var5, var6);
   }

   // $FF: synthetic method
   static FontRenderer access$1700(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static void access$1800(GuiStats var0, int var1, int var2, Item var3) {
      var0.func_146521_a(var1, var2, var3);
   }

   // $FF: synthetic method
   static Minecraft access$1900(GuiStats var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   static FontRenderer access$2000(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$2100(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$2200(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$2300(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$2400(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$2500(GuiStats var0) {
      return var0.fontRendererObj;
   }

   // $FF: synthetic method
   static FontRenderer access$2600(GuiStats var0) {
      return var0.fontRendererObj;
   }

}
