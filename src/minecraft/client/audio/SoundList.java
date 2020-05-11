package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.audio.SoundCategory;

public class SoundList {

   private final List field_148577_a = Lists.newArrayList();
   private boolean replaceExisting;
   private SoundCategory field_148576_c;


   public List getSoundList() {
      return this.field_148577_a;
   }

   public boolean canReplaceExisting() {
      return this.replaceExisting;
   }

   public void setReplaceExisting(boolean var1) {
      this.replaceExisting = var1;
   }

   public SoundCategory getSoundCategory() {
      return this.field_148576_c;
   }

   public void setSoundCategory(SoundCategory var1) {
      this.field_148576_c = var1;
   }
}
