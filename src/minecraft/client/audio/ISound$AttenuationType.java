package net.minecraft.client.audio;


public enum ISound$AttenuationType {

   NONE("NONE", 0, 0),
   LINEAR("LINEAR", 1, 2);
   private final int field_148589_c;
   // $FF: synthetic field
   private static final ISound$AttenuationType[] $VALUES = new ISound$AttenuationType[]{NONE, LINEAR};


   private ISound$AttenuationType(String var1, int var2, int var3) {
      this.field_148589_c = var3;
   }

   public int getTypeInt() {
      return this.field_148589_c;
   }

}
