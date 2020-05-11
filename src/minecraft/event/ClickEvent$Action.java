package net.minecraft.event;

import com.google.common.collect.Maps;
import java.util.Map;

public enum ClickEvent$Action {

   OPEN_URL("OPEN_URL", 0, "open_url", true),
   OPEN_FILE("OPEN_FILE", 1, "open_file", false),
   RUN_COMMAND("RUN_COMMAND", 2, "run_command", true),
   TWITCH_USER_INFO("TWITCH_USER_INFO", 3, "twitch_user_info", false),
   SUGGEST_COMMAND("SUGGEST_COMMAND", 4, "suggest_command", true);
   private static final Map nameMapping = Maps.newHashMap();
   private final boolean allowedInChat;
   private final String canonicalName;
   // $FF: synthetic field
   private static final ClickEvent$Action[] $VALUES = new ClickEvent$Action[]{OPEN_URL, OPEN_FILE, RUN_COMMAND, TWITCH_USER_INFO, SUGGEST_COMMAND};


   private ClickEvent$Action(String var1, int var2, String var3, boolean var4) {
      this.canonicalName = var3;
      this.allowedInChat = var4;
   }

   public boolean shouldAllowInChat() {
      return this.allowedInChat;
   }

   public String getCanonicalName() {
      return this.canonicalName;
   }

   public static ClickEvent$Action getValueByCanonicalName(String var0) {
      return (ClickEvent$Action)nameMapping.get(var0);
   }

   static {
      ClickEvent$Action[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         ClickEvent$Action var3 = var0[var2];
         nameMapping.put(var3.getCanonicalName(), var3);
      }

   }
}
