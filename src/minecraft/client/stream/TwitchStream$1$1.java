package net.minecraft.client.stream;

import net.minecraft.client.stream.TwitchStream$1;

class TwitchStream$1$1 extends Thread {

   // $FF: synthetic field
   final TwitchStream$1 field_153082_a;


   TwitchStream$1$1(TwitchStream$1 var1, String var2) {
      super(var2);
      this.field_153082_a = var1;
   }

   public void run() {
      this.field_153082_a.field_153084_b.func_152923_i();
   }
}
