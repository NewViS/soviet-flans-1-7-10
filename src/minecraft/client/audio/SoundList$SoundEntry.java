package net.minecraft.client.audio;

import net.minecraft.client.audio.SoundList$SoundEntry$Type;

public class SoundList$SoundEntry {

   private String field_148569_a;
   private float field_148567_b = 1.0F;
   private float field_148568_c = 1.0F;
   private int field_148565_d = 1;
   private SoundList$SoundEntry$Type field_148566_e;
   private boolean field_148564_f;


   public SoundList$SoundEntry() {
      this.field_148566_e = SoundList$SoundEntry$Type.FILE;
      this.field_148564_f = false;
   }

   public String getSoundEntryName() {
      return this.field_148569_a;
   }

   public void setSoundEntryName(String var1) {
      this.field_148569_a = var1;
   }

   public float getSoundEntryVolume() {
      return this.field_148567_b;
   }

   public void setSoundEntryVolume(float var1) {
      this.field_148567_b = var1;
   }

   public float getSoundEntryPitch() {
      return this.field_148568_c;
   }

   public void setSoundEntryPitch(float var1) {
      this.field_148568_c = var1;
   }

   public int getSoundEntryWeight() {
      return this.field_148565_d;
   }

   public void setSoundEntryWeight(int var1) {
      this.field_148565_d = var1;
   }

   public SoundList$SoundEntry$Type getSoundEntryType() {
      return this.field_148566_e;
   }

   public void setSoundEntryType(SoundList$SoundEntry$Type var1) {
      this.field_148566_e = var1;
   }

   public boolean isStreaming() {
      return this.field_148564_f;
   }

   public void setStreaming(boolean var1) {
      this.field_148564_f = var1;
   }
}
