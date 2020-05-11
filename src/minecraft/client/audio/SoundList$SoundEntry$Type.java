package net.minecraft.client.audio;


public enum SoundList$SoundEntry$Type {

   FILE("FILE", 0, "file"),
   SOUND_EVENT("SOUND_EVENT", 1, "event");
   private final String field_148583_c;
   // $FF: synthetic field
   private static final SoundList$SoundEntry$Type[] $VALUES = new SoundList$SoundEntry$Type[]{FILE, SOUND_EVENT};


   private SoundList$SoundEntry$Type(String var1, int var2, String var3) {
      this.field_148583_c = var3;
   }

   public static SoundList$SoundEntry$Type getType(String var0) {
      SoundList$SoundEntry$Type[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SoundList$SoundEntry$Type var4 = var1[var3];
         if(var4.field_148583_c.equals(var0)) {
            return var4;
         }
      }

      return null;
   }

}
