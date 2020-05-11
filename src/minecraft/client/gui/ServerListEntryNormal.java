package net.minecraft.client.gui;

import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ServerListEntryNormal$1;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class ServerListEntryNormal implements GuiListExtended$IGuiListEntry {

   private static final Logger logger = LogManager.getLogger();
   private static final ThreadPoolExecutor field_148302_b = new ScheduledThreadPoolExecutor(5, (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build());
   private final GuiMultiplayer field_148303_c;
   private final Minecraft field_148300_d;
   private final ServerData field_148301_e;
   private long field_148298_f;
   private String field_148299_g;
   private DynamicTexture field_148305_h;
   private ResourceLocation field_148306_i;


   protected ServerListEntryNormal(GuiMultiplayer var1, ServerData var2) {
      this.field_148303_c = var1;
      this.field_148301_e = var2;
      this.field_148300_d = Minecraft.getMinecraft();
      this.field_148306_i = new ResourceLocation("servers/" + var2.serverIP + "/icon");
      this.field_148305_h = (DynamicTexture)this.field_148300_d.getTextureManager().getTexture(this.field_148306_i);
   }

   public void drawEntry(int var1, int var2, int var3, int var4, int var5, Tessellator var6, int var7, int var8, boolean var9) {
      if(!this.field_148301_e.field_78841_f) {
         this.field_148301_e.field_78841_f = true;
         this.field_148301_e.pingToServer = -2L;
         this.field_148301_e.serverMOTD = "";
         this.field_148301_e.populationInfo = "";
         field_148302_b.submit(new ServerListEntryNormal$1(this));
      }

      boolean var10 = this.field_148301_e.field_82821_f > 5;
      boolean var11 = this.field_148301_e.field_82821_f < 5;
      boolean var12 = var10 || var11;
      this.field_148300_d.fontRenderer.drawString(this.field_148301_e.serverName, var2 + 32 + 3, var3 + 1, 16777215);
      List var13 = this.field_148300_d.fontRenderer.listFormattedStringToWidth(this.field_148301_e.serverMOTD, var4 - 32 - 2);

      for(int var14 = 0; var14 < Math.min(var13.size(), 2); ++var14) {
         this.field_148300_d.fontRenderer.drawString((String)var13.get(var14), var2 + 32 + 3, var3 + 12 + this.field_148300_d.fontRenderer.FONT_HEIGHT * var14, 8421504);
      }

      String var22 = var12?EnumChatFormatting.DARK_RED + this.field_148301_e.gameVersion:this.field_148301_e.populationInfo;
      int var15 = this.field_148300_d.fontRenderer.getStringWidth(var22);
      this.field_148300_d.fontRenderer.drawString(var22, var2 + var4 - var15 - 15 - 2, var3 + 1, 8421504);
      byte var16 = 0;
      String var18 = null;
      int var17;
      String var19;
      if(var12) {
         var17 = 5;
         var19 = var10?"Client out of date!":"Server out of date!";
         var18 = this.field_148301_e.field_147412_i;
      } else if(this.field_148301_e.field_78841_f && this.field_148301_e.pingToServer != -2L) {
         if(this.field_148301_e.pingToServer < 0L) {
            var17 = 5;
         } else if(this.field_148301_e.pingToServer < 150L) {
            var17 = 0;
         } else if(this.field_148301_e.pingToServer < 300L) {
            var17 = 1;
         } else if(this.field_148301_e.pingToServer < 600L) {
            var17 = 2;
         } else if(this.field_148301_e.pingToServer < 1000L) {
            var17 = 3;
         } else {
            var17 = 4;
         }

         if(this.field_148301_e.pingToServer < 0L) {
            var19 = "(no connection)";
         } else {
            var19 = this.field_148301_e.pingToServer + "ms";
            var18 = this.field_148301_e.field_147412_i;
         }
      } else {
         var16 = 1;
         var17 = (int)(Minecraft.getSystemTime() / 100L + (long)(var1 * 2) & 7L);
         if(var17 > 4) {
            var17 = 8 - var17;
         }

         var19 = "Pinging...";
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_148300_d.getTextureManager().bindTexture(Gui.icons);
      Gui.func_146110_a(var2 + var4 - 15, var3, (float)(var16 * 10), (float)(176 + var17 * 8), 10, 8, 256.0F, 256.0F);
      if(this.field_148301_e.getBase64EncodedIconData() != null && !this.field_148301_e.getBase64EncodedIconData().equals(this.field_148299_g)) {
         this.field_148299_g = this.field_148301_e.getBase64EncodedIconData();
         this.func_148297_b();
         this.field_148303_c.func_146795_p().saveServerList();
      }

      if(this.field_148305_h != null) {
         this.field_148300_d.getTextureManager().bindTexture(this.field_148306_i);
         Gui.func_146110_a(var2, var3, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      }

      int var20 = var7 - var2;
      int var21 = var8 - var3;
      if(var20 >= var4 - 15 && var20 <= var4 - 5 && var21 >= 0 && var21 <= 8) {
         this.field_148303_c.func_146793_a(var19);
      } else if(var20 >= var4 - var15 - 15 - 2 && var20 <= var4 - 15 - 2 && var21 >= 0 && var21 <= 8) {
         this.field_148303_c.func_146793_a(var18);
      }

   }

   private void func_148297_b() {
      if(this.field_148301_e.getBase64EncodedIconData() == null) {
         this.field_148300_d.getTextureManager().deleteTexture(this.field_148306_i);
         this.field_148305_h = null;
      } else {
         ByteBuf var2 = Unpooled.copiedBuffer(this.field_148301_e.getBase64EncodedIconData(), Charsets.UTF_8);
         ByteBuf var3 = Base64.decode(var2);

         BufferedImage var1;
         label74: {
            try {
               var1 = ImageIO.read(new ByteBufInputStream(var3));
               Validate.validState(var1.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
               Validate.validState(var1.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
               break label74;
            } catch (Exception var8) {
               logger.error("Invalid icon for server " + this.field_148301_e.serverName + " (" + this.field_148301_e.serverIP + ")", var8);
               this.field_148301_e.func_147407_a((String)null);
            } finally {
               var2.release();
               var3.release();
            }

            return;
         }

         if(this.field_148305_h == null) {
            this.field_148305_h = new DynamicTexture(var1.getWidth(), var1.getHeight());
            this.field_148300_d.getTextureManager().loadTexture(this.field_148306_i, this.field_148305_h);
         }

         var1.getRGB(0, 0, var1.getWidth(), var1.getHeight(), this.field_148305_h.getTextureData(), 0, var1.getWidth());
         this.field_148305_h.updateDynamicTexture();
      }

   }

   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_148303_c.func_146790_a(var1);
      if(Minecraft.getSystemTime() - this.field_148298_f < 250L) {
         this.field_148303_c.func_146796_h();
      }

      this.field_148298_f = Minecraft.getSystemTime();
      return false;
   }

   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {}

   public ServerData func_148296_a() {
      return this.field_148301_e;
   }

   // $FF: synthetic method
   static ServerData access$000(ServerListEntryNormal var0) {
      return var0.field_148301_e;
   }

   // $FF: synthetic method
   static GuiMultiplayer access$100(ServerListEntryNormal var0) {
      return var0.field_148303_c;
   }

}
