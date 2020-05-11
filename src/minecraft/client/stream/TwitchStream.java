package net.minecraft.client.stream;

import com.google.common.collect.Maps;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.stream.BroadcastController;
import net.minecraft.client.stream.BroadcastController$BroadcastListener;
import net.minecraft.client.stream.BroadcastController$BroadcastState;
import net.minecraft.client.stream.ChatController;
import net.minecraft.client.stream.ChatController$ChatListener;
import net.minecraft.client.stream.ChatController$ChatState;
import net.minecraft.client.stream.IStream;
import net.minecraft.client.stream.IStream$AuthFailureReason;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.client.stream.IngestServerTester$IngestTestListener;
import net.minecraft.client.stream.IngestServerTester$IngestTestState;
import net.minecraft.client.stream.Metadata;
import net.minecraft.client.stream.TwitchStream$1;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Util;
import net.minecraft.util.Util$EnumOS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.core.helpers.Strings;
import org.lwjgl.opengl.GL11;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.VideoParams;
import tv.twitch.chat.ChatMessage;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class TwitchStream implements BroadcastController$BroadcastListener, ChatController$ChatListener, IngestServerTester$IngestTestListener, IStream {

   private static final Logger field_152950_b = LogManager.getLogger();
   public static final Marker field_152949_a = MarkerManager.getMarker("STREAM");
   private final BroadcastController field_152951_c;
   private final ChatController field_152952_d;
   private final Minecraft field_152953_e;
   private final IChatComponent field_152954_f = new ChatComponentText("Twitch");
   private final Map field_152955_g = Maps.newHashMap();
   private Framebuffer field_152956_h;
   private boolean field_152957_i;
   private int field_152958_j = 30;
   private long field_152959_k = 0L;
   private boolean field_152960_l = false;
   private boolean field_152961_m;
   private boolean field_152962_n;
   private boolean field_152963_o;
   private IStream$AuthFailureReason field_152964_p;
   private static boolean field_152965_q;


   public TwitchStream(Minecraft var1, String var2) {
      this.field_152964_p = IStream$AuthFailureReason.ERROR;
      this.field_152953_e = var1;
      this.field_152951_c = new BroadcastController();
      this.field_152952_d = new ChatController();
      this.field_152951_c.func_152841_a(this);
      this.field_152952_d.func_152990_a(this);
      this.field_152951_c.func_152842_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.field_152952_d.func_152984_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.field_152954_f.getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE);
      if(Strings.isNotEmpty(var2) && OpenGlHelper.framebufferSupported) {
         TwitchStream$1 var3 = new TwitchStream$1(this, "Twitch authenticator", var2);
         var3.setDaemon(true);
         var3.start();
      }

   }

   public void func_152923_i() {
      field_152950_b.debug(field_152949_a, "Shutdown streaming");
      this.field_152951_c.func_152851_B();
      this.field_152952_d.func_152993_m();
   }

   public void func_152935_j() {
      int var1 = this.field_152953_e.gameSettings.field_152408_R;
      ChatController$ChatState var2 = this.field_152952_d.func_153000_j();
      if(var1 == 2) {
         if(var2 == ChatController$ChatState.Connected) {
            field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat per user options");
            this.field_152952_d.func_153002_l();
         }
      } else if(var1 == 1) {
         if((var2 == ChatController$ChatState.Disconnected || var2 == ChatController$ChatState.Uninitialized) && this.field_152951_c.func_152849_q()) {
            field_152950_b.debug(field_152949_a, "Connecting to twitch chat per user options");
            this.func_152942_I();
         }
      } else if(var1 == 0) {
         if((var2 == ChatController$ChatState.Disconnected || var2 == ChatController$ChatState.Uninitialized) && this.func_152934_n()) {
            field_152950_b.debug(field_152949_a, "Connecting to twitch chat as user is streaming");
            this.func_152942_I();
         } else if(var2 == ChatController$ChatState.Connected && !this.func_152934_n()) {
            field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat as user is no longer streaming");
            this.field_152952_d.func_153002_l();
         }
      }

      this.field_152951_c.func_152821_H();
      this.field_152952_d.func_152997_n();
   }

   protected void func_152942_I() {
      ChatController$ChatState var1 = this.field_152952_d.func_153000_j();
      String var2 = this.field_152951_c.func_152843_l().name;
      if(var1 == ChatController$ChatState.Uninitialized) {
         this.field_152952_d.func_152985_f(var2);
         this.field_152952_d.field_153005_c = var2;
      } else if(var1 == ChatController$ChatState.Disconnected) {
         this.field_152952_d.func_152986_d(var2);
      } else {
         field_152950_b.warn("Invalid twitch chat state {}", new Object[]{var1});
      }

   }

   public void func_152922_k() {
      if(this.field_152951_c.func_152850_m() && !this.field_152951_c.func_152839_p()) {
         long var1 = System.nanoTime();
         long var3 = (long)(1000000000 / this.field_152958_j);
         long var5 = var1 - this.field_152959_k;
         boolean var7 = var5 >= var3;
         if(var7) {
            FrameBuffer var8 = this.field_152951_c.func_152822_N();
            Framebuffer var9 = this.field_152953_e.getFramebuffer();
            this.field_152956_h.bindFramebuffer(true);
            GL11.glMatrixMode(5889);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0D, (double)this.field_152956_h.framebufferWidth, (double)this.field_152956_h.framebufferHeight, 0.0D, 1000.0D, 3000.0D);
            GL11.glMatrixMode(5888);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glViewport(0, 0, this.field_152956_h.framebufferWidth, this.field_152956_h.framebufferHeight);
            GL11.glEnable(3553);
            GL11.glDisable(3008);
            GL11.glDisable(3042);
            float var10 = (float)this.field_152956_h.framebufferWidth;
            float var11 = (float)this.field_152956_h.framebufferHeight;
            float var12 = (float)var9.framebufferWidth / (float)var9.framebufferTextureWidth;
            float var13 = (float)var9.framebufferHeight / (float)var9.framebufferTextureHeight;
            var9.bindFramebufferTexture();
            GL11.glTexParameterf(3553, 10241, 9729.0F);
            GL11.glTexParameterf(3553, 10240, 9729.0F);
            Tessellator var14 = Tessellator.instance;
            var14.startDrawingQuads();
            var14.addVertexWithUV(0.0D, (double)var11, 0.0D, 0.0D, (double)var13);
            var14.addVertexWithUV((double)var10, (double)var11, 0.0D, (double)var12, (double)var13);
            var14.addVertexWithUV((double)var10, 0.0D, 0.0D, (double)var12, 0.0D);
            var14.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
            var14.draw();
            var9.unbindFramebufferTexture();
            GL11.glPopMatrix();
            GL11.glMatrixMode(5889);
            GL11.glPopMatrix();
            GL11.glMatrixMode(5888);
            this.field_152951_c.func_152846_a(var8);
            this.field_152956_h.unbindFramebuffer();
            this.field_152951_c.func_152859_b(var8);
            this.field_152959_k = var1;
         }

      }
   }

   public boolean func_152936_l() {
      return this.field_152951_c.func_152849_q();
   }

   public boolean func_152924_m() {
      return this.field_152951_c.func_152857_n();
   }

   public boolean func_152934_n() {
      return this.field_152951_c.func_152850_m();
   }

   public void func_152911_a(Metadata var1, long var2) {
      if(this.func_152934_n() && this.field_152957_i) {
         long var4 = this.field_152951_c.func_152844_x();
         if(!this.field_152951_c.func_152840_a(var1.func_152810_c(), var4 + var2, var1.func_152809_a(), var1.func_152806_b())) {
            field_152950_b.warn(field_152949_a, "Couldn\'t send stream metadata action at {}: {}", new Object[]{Long.valueOf(var4 + var2), var1});
         } else {
            field_152950_b.debug(field_152949_a, "Sent stream metadata action at {}: {}", new Object[]{Long.valueOf(var4 + var2), var1});
         }

      }
   }

   public boolean func_152919_o() {
      return this.field_152951_c.func_152839_p();
   }

   public void func_152931_p() {
      if(this.field_152951_c.func_152830_D()) {
         field_152950_b.debug(field_152949_a, "Requested commercial from Twitch");
      } else {
         field_152950_b.warn(field_152949_a, "Could not request commercial from Twitch");
      }

   }

   public void func_152916_q() {
      this.field_152951_c.func_152847_F();
      this.field_152962_n = true;
      this.func_152915_s();
   }

   public void func_152933_r() {
      this.field_152951_c.func_152854_G();
      this.field_152962_n = false;
      this.func_152915_s();
   }

   public void func_152915_s() {
      if(this.func_152934_n()) {
         float var1 = this.field_152953_e.gameSettings.field_152402_L;
         boolean var2 = this.field_152962_n || var1 <= 0.0F;
         this.field_152951_c.func_152837_b(var2?0.0F:var1);
         this.field_152951_c.func_152829_a(this.func_152929_G()?0.0F:this.field_152953_e.gameSettings.field_152401_K);
      }

   }

   public void func_152930_t() {
      GameSettings var1 = this.field_152953_e.gameSettings;
      VideoParams var2 = this.field_152951_c.func_152834_a(func_152946_b(var1.field_152403_M), func_152948_a(var1.field_152404_N), func_152947_c(var1.field_152400_J), (float)this.field_152953_e.displayWidth / (float)this.field_152953_e.displayHeight);
      switch(var1.field_152405_O) {
      case 0:
         var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_LOW;
         break;
      case 1:
         var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_MEDIUM;
         break;
      case 2:
         var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
      }

      if(this.field_152956_h == null) {
         this.field_152956_h = new Framebuffer(var2.outputWidth, var2.outputHeight, false);
      } else {
         this.field_152956_h.createBindFramebuffer(var2.outputWidth, var2.outputHeight);
      }

      if(var1.field_152407_Q != null && var1.field_152407_Q.length() > 0) {
         IngestServer[] var3 = this.func_152925_v();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            IngestServer var6 = var3[var5];
            if(var6.serverUrl.equals(var1.field_152407_Q)) {
               this.field_152951_c.func_152824_a(var6);
               break;
            }
         }
      }

      this.field_152958_j = var2.targetFps;
      this.field_152957_i = var1.field_152406_P;
      this.field_152951_c.func_152836_a(var2);
      field_152950_b.info(field_152949_a, "Streaming at {}/{} at {} kbps to {}", new Object[]{Integer.valueOf(var2.outputWidth), Integer.valueOf(var2.outputHeight), Integer.valueOf(var2.maxKbps), this.field_152951_c.func_152833_s().serverUrl});
      this.field_152951_c.func_152828_a((String)null, "Minecraft", (String)null);
   }

   public void func_152914_u() {
      if(this.field_152951_c.func_152819_E()) {
         field_152950_b.info(field_152949_a, "Stopped streaming to Twitch");
      } else {
         field_152950_b.warn(field_152949_a, "Could not stop streaming to Twitch");
      }

   }

   public void func_152900_a(ErrorCode var1, AuthToken var2) {}

   public void func_152897_a(ErrorCode var1) {
      if(ErrorCode.succeeded(var1)) {
         field_152950_b.debug(field_152949_a, "Login attempt successful");
         this.field_152961_m = true;
      } else {
         field_152950_b.warn(field_152949_a, "Login attempt unsuccessful: {} (error code {})", new Object[]{ErrorCode.getString(var1), Integer.valueOf(var1.getValue())});
         this.field_152961_m = false;
      }

   }

   public void func_152898_a(ErrorCode var1, GameInfo[] var2) {}

   public void func_152891_a(BroadcastController$BroadcastState var1) {
      field_152950_b.debug(field_152949_a, "Broadcast state changed to {}", new Object[]{var1});
      if(var1 == BroadcastController$BroadcastState.Initialized) {
         this.field_152951_c.func_152827_a(BroadcastController$BroadcastState.Authenticated);
      }

   }

   public void func_152895_a() {
      field_152950_b.info(field_152949_a, "Logged out of twitch");
   }

   public void func_152894_a(StreamInfo var1) {
      field_152950_b.debug(field_152949_a, "Stream info updated; {} viewers on stream ID {}", new Object[]{Integer.valueOf(var1.viewers), Long.valueOf(var1.streamId)});
   }

   public void func_152896_a(IngestList var1) {}

   public void func_152893_b(ErrorCode var1) {
      field_152950_b.warn(field_152949_a, "Issue submitting frame: {} (Error code {})", new Object[]{ErrorCode.getString(var1), Integer.valueOf(var1.getValue())});
      this.field_152953_e.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText("Issue streaming frame: " + var1 + " (" + ErrorCode.getString(var1) + ")"), 2);
   }

   public void func_152899_b() {
      this.func_152915_s();
      field_152950_b.info(field_152949_a, "Broadcast to Twitch has started");
   }

   public void func_152901_c() {
      field_152950_b.info(field_152949_a, "Broadcast to Twitch has stopped");
   }

   public void func_152892_c(ErrorCode var1) {
      ChatComponentTranslation var2;
      if(var1 == ErrorCode.TTV_EC_SOUNDFLOWER_NOT_INSTALLED) {
         var2 = new ChatComponentTranslation("stream.unavailable.soundflower.chat.link", new Object[0]);
         var2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.OPEN_URL, "https://help.mojang.com/customer/portal/articles/1374877-configuring-soundflower-for-streaming-on-apple-computers"));
         var2.getChatStyle().setUnderlined(Boolean.valueOf(true));
         ChatComponentTranslation var3 = new ChatComponentTranslation("stream.unavailable.soundflower.chat", new Object[]{var2});
         var3.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
         this.field_152953_e.ingameGUI.getChatGUI().printChatMessage(var3);
      } else {
         var2 = new ChatComponentTranslation("stream.unavailable.unknown.chat", new Object[]{ErrorCode.getString(var1)});
         var2.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
         this.field_152953_e.ingameGUI.getChatGUI().printChatMessage(var2);
      }

   }

   public void func_152907_a(IngestServerTester var1, IngestServerTester$IngestTestState var2) {
      field_152950_b.debug(field_152949_a, "Ingest test state changed to {}", new Object[]{var2});
      if(var2 == IngestServerTester$IngestTestState.Finished) {
         this.field_152960_l = true;
      }

   }

   public static int func_152948_a(float var0) {
      return MathHelper.floor_float(10.0F + var0 * 50.0F);
   }

   public static int func_152946_b(float var0) {
      return MathHelper.floor_float(230.0F + var0 * 3270.0F);
   }

   public static float func_152947_c(float var0) {
      return 0.1F + var0 * 0.1F;
   }

   public IngestServer[] func_152925_v() {
      return this.field_152951_c.func_152855_t().getServers();
   }

   public void func_152909_x() {
      IngestServerTester var1 = this.field_152951_c.func_152838_J();
      if(var1 != null) {
         var1.func_153042_a(this);
      }

   }

   public IngestServerTester func_152932_y() {
      return this.field_152951_c.func_152856_w();
   }

   public boolean func_152908_z() {
      return this.field_152951_c.func_152825_o();
   }

   public int func_152920_A() {
      return this.func_152934_n()?this.field_152951_c.func_152816_j().viewers:0;
   }

   public void func_152903_a(ChatMessage[] var1) {
      ChatMessage[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ChatMessage var5 = var2[var4];
         this.func_152939_a(var5.userName, var5);
         if(this.func_152940_a(var5.modes, var5.subscriptions, this.field_152953_e.gameSettings.field_152409_S)) {
            ChatComponentText var6 = new ChatComponentText(var5.userName);
            ChatComponentTranslation var7 = new ChatComponentTranslation("chat.stream." + (var5.action?"emote":"text"), new Object[]{this.field_152954_f, var6, EnumChatFormatting.getTextWithoutFormattingCodes(var5.message)});
            if(var5.action) {
               var7.getChatStyle().setItalic(Boolean.valueOf(true));
            }

            ChatComponentText var8 = new ChatComponentText("");
            var8.appendSibling(new ChatComponentTranslation("stream.userinfo.chatTooltip", new Object[0]));
            Iterator var9 = GuiTwitchUserMode.func_152328_a(var5.modes, var5.subscriptions, (IStream)null).iterator();

            while(var9.hasNext()) {
               IChatComponent var10 = (IChatComponent)var9.next();
               var8.appendText("\n");
               var8.appendSibling(var10);
            }

            var6.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent$Action.SHOW_TEXT, var8));
            var6.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.TWITCH_USER_INFO, var5.userName));
            this.field_152953_e.ingameGUI.getChatGUI().printChatMessage(var7);
         }
      }

   }

   private void func_152939_a(String var1, ChatMessage var2) {
      ChatUserInfo var3 = (ChatUserInfo)this.field_152955_g.get(var1);
      if(var3 == null) {
         var3 = new ChatUserInfo();
         var3.displayName = var1;
         this.field_152955_g.put(var1, var3);
      }

      var3.subscriptions = var2.subscriptions;
      var3.modes = var2.modes;
      var3.emoticonSet = var2.emoticonSet;
      var3.nameColorARGB = var2.nameColorARGB;
   }

   private boolean func_152940_a(HashSet var1, HashSet var2, int var3) {
      return var1.contains(ChatUserMode.TTV_CHAT_USERMODE_BANNED)?false:(var1.contains(ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR)?true:(var1.contains(ChatUserMode.TTV_CHAT_USERMODE_MODERATOR)?true:(var1.contains(ChatUserMode.TTV_CHAT_USERMODE_STAFF)?true:(var3 == 0?true:(var3 == 1?var2.contains(ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER):false)))));
   }

   public void func_152904_a(ChatUserInfo[] var1, ChatUserInfo[] var2, ChatUserInfo[] var3) {
      ChatUserInfo[] var4 = var2;
      int var5 = var2.length;

      int var6;
      ChatUserInfo var7;
      for(var6 = 0; var6 < var5; ++var6) {
         var7 = var4[var6];
         this.field_152955_g.remove(var7.displayName);
      }

      var4 = var3;
      var5 = var3.length;

      for(var6 = 0; var6 < var5; ++var6) {
         var7 = var4[var6];
         this.field_152955_g.put(var7.displayName, var7);
      }

      var4 = var1;
      var5 = var1.length;

      for(var6 = 0; var6 < var5; ++var6) {
         var7 = var4[var6];
         this.field_152955_g.put(var7.displayName, var7);
      }

   }

   public void func_152906_d() {
      field_152950_b.debug(field_152949_a, "Chat connected");
   }

   public void func_152905_e() {
      field_152950_b.debug(field_152949_a, "Chat disconnected");
      this.field_152955_g.clear();
   }

   public void func_152902_f() {}

   public boolean func_152927_B() {
      return this.field_152952_d.func_152991_c() && this.field_152952_d.field_153005_c.equals(this.field_152951_c.func_152843_l().name);
   }

   public String func_152921_C() {
      return this.field_152952_d.field_153005_c;
   }

   public ChatUserInfo func_152926_a(String var1) {
      return (ChatUserInfo)this.field_152955_g.get(var1);
   }

   public void func_152917_b(String var1) {
      this.field_152952_d.func_152992_g(var1);
   }

   public boolean func_152928_D() {
      return field_152965_q && this.field_152951_c.func_152858_b();
   }

   public ErrorCode func_152912_E() {
      return !field_152965_q?ErrorCode.TTV_EC_OS_TOO_OLD:this.field_152951_c.func_152852_P();
   }

   public boolean func_152913_F() {
      return this.field_152961_m;
   }

   public void func_152910_a(boolean var1) {
      this.field_152963_o = var1;
      this.func_152915_s();
   }

   public boolean func_152929_G() {
      boolean var1 = this.field_152953_e.gameSettings.field_152410_T == 1;
      return this.field_152962_n || this.field_152953_e.gameSettings.field_152401_K <= 0.0F || var1 != this.field_152963_o;
   }

   public IStream$AuthFailureReason func_152918_H() {
      return this.field_152964_p;
   }

   // $FF: synthetic method
   static Logger access$000() {
      return field_152950_b;
   }

   // $FF: synthetic method
   static BroadcastController access$100(TwitchStream var0) {
      return var0.field_152951_c;
   }

   // $FF: synthetic method
   static ChatController access$200(TwitchStream var0) {
      return var0.field_152952_d;
   }

   // $FF: synthetic method
   static IStream$AuthFailureReason access$302(TwitchStream var0, IStream$AuthFailureReason var1) {
      return var0.field_152964_p = var1;
   }

   static {
      try {
         if(Util.getOSType() == Util$EnumOS.WINDOWS) {
            System.loadLibrary("avutil-ttv-51");
            System.loadLibrary("swresample-ttv-0");
            System.loadLibrary("libmp3lame-ttv");
            if(System.getProperty("os.arch").contains("64")) {
               System.loadLibrary("libmfxsw64");
            } else {
               System.loadLibrary("libmfxsw32");
            }
         }

         field_152965_q = true;
      } catch (Throwable var1) {
         field_152965_q = false;
      }

   }
}
