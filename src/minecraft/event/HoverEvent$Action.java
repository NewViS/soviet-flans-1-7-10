package net.minecraft.event;

import com.google.common.collect.Maps;
import java.util.Map;

public enum HoverEvent$Action {

   SHOW_TEXT("SHOW_TEXT", 0, "show_text", true),
   SHOW_ACHIEVEMENT("SHOW_ACHIEVEMENT", 1, "show_achievement", true),
   SHOW_ITEM("SHOW_ITEM", 2, "show_item", true);
   private static final Map nameMapping = Maps.newHashMap();
   private final boolean allowedInChat;
   private final String canonicalName;
   // $FF: synthetic field
   private static final HoverEvent$Action[] $VALUES = new HoverEvent$Action[]{SHOW_TEXT, SHOW_ACHIEVEMENT, SHOW_ITEM};


   private HoverEvent$Action(String var1, int var2, String var3, boolean var4) {
      this.canonicalName = var3;
      this.allowedInChat = var4;
   }

   public boolean shouldAllowInChat() {
      return this.allowedInChat;
   }

   public String getCanonicalName() {
      return this.canonicalName;
   }

   public static HoverEvent$Action getValueByCanonicalName(String var0) {
      return (HoverEvent$Action)nameMapping.get(var0);
   }

   static {
      HoverEvent$Action[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         HoverEvent$Action var3 = var0[var2];
         nameMapping.put(var3.getCanonicalName(), var3);
      }

   }
}
