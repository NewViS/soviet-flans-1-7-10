package net.minecraft.entity.player;


public enum EntityPlayer$EnumChatVisibility {

   FULL("FULL", 0, 0, "options.chat.visibility.full"),
   SYSTEM("SYSTEM", 1, 1, "options.chat.visibility.system"),
   HIDDEN("HIDDEN", 2, 2, "options.chat.visibility.hidden");
   private static final EntityPlayer$EnumChatVisibility[] field_151432_d = new EntityPlayer$EnumChatVisibility[values().length];
   private final int chatVisibility;
   private final String resourceKey;
   // $FF: synthetic field
   private static final EntityPlayer$EnumChatVisibility[] $VALUES = new EntityPlayer$EnumChatVisibility[]{FULL, SYSTEM, HIDDEN};


   private EntityPlayer$EnumChatVisibility(String var1, int var2, int var3, String var4) {
      this.chatVisibility = var3;
      this.resourceKey = var4;
   }

   public int getChatVisibility() {
      return this.chatVisibility;
   }

   public static EntityPlayer$EnumChatVisibility getEnumChatVisibility(int var0) {
      return field_151432_d[var0 % field_151432_d.length];
   }

   public String getResourceKey() {
      return this.resourceKey;
   }

   static {
      EntityPlayer$EnumChatVisibility[] var0 = values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         EntityPlayer$EnumChatVisibility var3 = var0[var2];
         field_151432_d[var3.chatVisibility] = var3;
      }

   }
}
