package net.minecraft.client.multiplayer;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public enum ServerData$ServerResourceMode {

   ENABLED("ENABLED", 0, "enabled"),
   DISABLED("DISABLED", 1, "disabled"),
   PROMPT("PROMPT", 2, "prompt");
   private final IChatComponent field_152594_d;
   // $FF: synthetic field
   private static final ServerData$ServerResourceMode[] $VALUES = new ServerData$ServerResourceMode[]{ENABLED, DISABLED, PROMPT};


   private ServerData$ServerResourceMode(String var1, int var2, String var3) {
      this.field_152594_d = new ChatComponentTranslation("addServer.resourcePack." + var3, new Object[0]);
   }

   public IChatComponent func_152589_a() {
      return this.field_152594_d;
   }

}
