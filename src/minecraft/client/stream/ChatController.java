package net.minecraft.client.stream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.stream.ChatController$ChatListener;
import net.minecraft.client.stream.ChatController$ChatState;
import net.minecraft.client.stream.ChatController$SwitchChatState;
import net.minecraft.client.stream.TwitchStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.chat.Chat;
import tv.twitch.chat.ChatChannelInfo;
import tv.twitch.chat.ChatEvent;
import tv.twitch.chat.ChatMessageList;
import tv.twitch.chat.ChatTokenizedMessage;
import tv.twitch.chat.ChatUserList;
import tv.twitch.chat.IChatCallbacks;
import tv.twitch.chat.StandardChatAPI;

public class ChatController implements IChatCallbacks {

   private static final Logger field_153018_p = LogManager.getLogger();
   protected ChatController$ChatListener field_153003_a = null;
   protected String field_153004_b = "";
   protected String field_153005_c = "";
   protected String field_153006_d = "";
   protected String field_153007_e = "";
   protected Chat field_153008_f = null;
   protected boolean field_153009_g = false;
   protected boolean field_153010_h = false;
   protected ChatController$ChatState field_153011_i;
   protected AuthToken field_153012_j;
   protected List field_153013_k;
   protected LinkedList field_153014_l;
   protected int field_153015_m;
   protected boolean field_153016_n;
   protected boolean field_153017_o;


   public void chatStatusCallback(ErrorCode var1) {
      if(!ErrorCode.succeeded(var1)) {
         this.field_153011_i = ChatController$ChatState.Disconnected;
      }
   }

   public void chatChannelMembershipCallback(ChatEvent var1, ChatChannelInfo var2) {
      switch(ChatController$SwitchChatState.field_152982_a[var1.ordinal()]) {
      case 1:
         this.field_153011_i = ChatController$ChatState.Connected;
         this.func_152999_p();
         break;
      case 2:
         this.field_153011_i = ChatController$ChatState.Disconnected;
      }

   }

   public void chatChannelUserChangeCallback(ChatUserList var1, ChatUserList var2, ChatUserList var3) {
      int var4;
      int var5;
      for(var4 = 0; var4 < var2.userList.length; ++var4) {
         var5 = this.field_153013_k.indexOf(var2.userList[var4]);
         if(var5 >= 0) {
            this.field_153013_k.remove(var5);
         }
      }

      for(var4 = 0; var4 < var3.userList.length; ++var4) {
         var5 = this.field_153013_k.indexOf(var3.userList[var4]);
         if(var5 >= 0) {
            this.field_153013_k.remove(var5);
         }

         this.field_153013_k.add(var3.userList[var4]);
      }

      for(var4 = 0; var4 < var1.userList.length; ++var4) {
         this.field_153013_k.add(var1.userList[var4]);
      }

      try {
         if(this.field_153003_a != null) {
            this.field_153003_a.func_152904_a(var1.userList, var2.userList, var3.userList);
         }
      } catch (Exception var6) {
         this.func_152995_h(var6.toString());
      }

   }

   public void chatQueryChannelUsersCallback(ChatUserList var1) {}

   public void chatChannelMessageCallback(ChatMessageList var1) {
      for(int var2 = 0; var2 < var1.messageList.length; ++var2) {
         this.field_153014_l.addLast(var1.messageList[var2]);
      }

      try {
         if(this.field_153003_a != null) {
            this.field_153003_a.func_152903_a(var1.messageList);
         }
      } catch (Exception var3) {
         this.func_152995_h(var3.toString());
      }

      while(this.field_153014_l.size() > this.field_153015_m) {
         this.field_153014_l.removeFirst();
      }

   }

   public void chatClearCallback(String var1) {
      this.func_152987_o();
   }

   public void emoticonDataDownloadCallback(ErrorCode var1) {
      if(ErrorCode.succeeded(var1)) {
         this.func_152988_s();
      }

   }

   public void chatChannelTokenizedMessageCallback(ChatTokenizedMessage[] var1) {}

   public void func_152990_a(ChatController$ChatListener var1) {
      this.field_153003_a = var1;
   }

   public boolean func_152991_c() {
      return this.field_153011_i == ChatController$ChatState.Connected;
   }

   public void func_152994_a(AuthToken var1) {
      this.field_153012_j = var1;
   }

   public void func_152984_a(String var1) {
      this.field_153006_d = var1;
   }

   public void func_152998_c(String var1) {
      this.field_153004_b = var1;
   }

   public ChatController$ChatState func_153000_j() {
      return this.field_153011_i;
   }

   public ChatController() {
      this.field_153011_i = ChatController$ChatState.Uninitialized;
      this.field_153012_j = new AuthToken();
      this.field_153013_k = new ArrayList();
      this.field_153014_l = new LinkedList();
      this.field_153015_m = 128;
      this.field_153016_n = false;
      this.field_153017_o = false;
      this.field_153008_f = new Chat(new StandardChatAPI());
   }

   public boolean func_152986_d(String var1) {
      this.func_153002_l();
      this.field_153010_h = false;
      this.field_153005_c = var1;
      return this.func_152985_f(var1);
   }

   public boolean func_153002_l() {
      if(this.field_153011_i != ChatController$ChatState.Connected && this.field_153011_i != ChatController$ChatState.Connecting) {
         if(this.field_153011_i == ChatController$ChatState.Disconnected) {
            this.func_152989_q();
         }
      } else {
         ErrorCode var1 = this.field_153008_f.disconnect();
         if(ErrorCode.failed(var1)) {
            String var2 = ErrorCode.getString(var1);
            this.func_152995_h(String.format("Error disconnecting: %s", new Object[]{var2}));
            return false;
         }

         this.func_152989_q();
      }

      return this.func_152993_m();
   }

   protected boolean func_152985_f(String var1) {
      if(this.field_153009_g) {
         return false;
      } else {
         ErrorCode var2 = this.field_153008_f.initialize(var1, false);
         if(ErrorCode.failed(var2)) {
            String var3 = ErrorCode.getString(var2);
            this.func_152995_h(String.format("Error initializing chat: %s", new Object[]{var3}));
            this.func_152989_q();
            return false;
         } else {
            this.field_153009_g = true;
            this.field_153008_f.setChatCallbacks(this);
            this.field_153011_i = ChatController$ChatState.Initialized;
            return true;
         }
      }
   }

   protected boolean func_152993_m() {
      if(this.field_153009_g) {
         ErrorCode var1 = this.field_153008_f.shutdown();
         if(ErrorCode.failed(var1)) {
            String var2 = ErrorCode.getString(var1);
            this.func_152995_h(String.format("Error shutting down chat: %s", new Object[]{var2}));
            return false;
         }
      }

      this.field_153011_i = ChatController$ChatState.Uninitialized;
      this.field_153009_g = false;
      this.func_152996_t();
      this.field_153008_f.setChatCallbacks((IChatCallbacks)null);
      return true;
   }

   public void func_152997_n() {
      if(this.field_153009_g) {
         ErrorCode var1 = this.field_153008_f.flushEvents();
         String var2;
         if(ErrorCode.failed(var1)) {
            var2 = ErrorCode.getString(var1);
            this.func_152995_h(String.format("Error flushing chat events: %s", new Object[]{var2}));
         }

         switch(ChatController$SwitchChatState.field_152983_b[this.field_153011_i.ordinal()]) {
         case 1:
         case 3:
         case 4:
         default:
            break;
         case 2:
            if(this.field_153010_h) {
               var1 = this.field_153008_f.connectAnonymous();
            } else {
               var1 = this.field_153008_f.connect(this.field_153005_c, this.field_153012_j.data);
            }

            if(ErrorCode.failed(var1)) {
               var2 = ErrorCode.getString(var1);
               this.func_152995_h(String.format("Error connecting: %s", new Object[]{var2}));
               this.func_152993_m();
               this.func_152989_q();
            } else {
               this.field_153011_i = ChatController$ChatState.Connecting;
               this.func_153001_r();
            }
            break;
         case 5:
            this.func_153002_l();
         }

      }
   }

   public boolean func_152992_g(String var1) {
      if(this.field_153011_i != ChatController$ChatState.Connected) {
         return false;
      } else {
         ErrorCode var2 = this.field_153008_f.sendMessage(var1);
         if(ErrorCode.failed(var2)) {
            String var3 = ErrorCode.getString(var2);
            this.func_152995_h(String.format("Error sending chat message: %s", new Object[]{var3}));
            return false;
         } else {
            return true;
         }
      }
   }

   public void func_152987_o() {
      this.field_153014_l.clear();

      try {
         if(this.field_153003_a != null) {
            this.field_153003_a.func_152902_f();
         }
      } catch (Exception var2) {
         this.func_152995_h(var2.toString());
      }

   }

   protected void func_152999_p() {
      try {
         if(this.field_153003_a != null) {
            this.field_153003_a.func_152906_d();
         }
      } catch (Exception var2) {
         this.func_152995_h(var2.toString());
      }

   }

   protected void func_152989_q() {
      try {
         if(this.field_153003_a != null) {
            this.field_153003_a.func_152905_e();
         }
      } catch (Exception var2) {
         this.func_152995_h(var2.toString());
      }

   }

   protected void func_153001_r() {}

   protected void func_152988_s() {}

   protected void func_152996_t() {}

   protected void func_152995_h(String var1) {
      field_153018_p.error(TwitchStream.field_152949_a, "[Chat controller] {}", new Object[]{var1});
   }

}
