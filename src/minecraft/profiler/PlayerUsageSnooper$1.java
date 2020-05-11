package net.minecraft.profiler;

import java.util.HashMap;
import java.util.TimerTask;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.util.HttpUtil;

class PlayerUsageSnooper$1 extends TimerTask {

   // $FF: synthetic field
   final PlayerUsageSnooper snooper;


   PlayerUsageSnooper$1(PlayerUsageSnooper var1) {
      this.snooper = var1;
   }

   public void run() {
      if(PlayerUsageSnooper.access$000(this.snooper).isSnooperEnabled()) {
         HashMap var1;
         synchronized(PlayerUsageSnooper.access$100(this.snooper)) {
            var1 = new HashMap(PlayerUsageSnooper.access$200(this.snooper));
            if(PlayerUsageSnooper.access$300(this.snooper) == 0) {
               var1.putAll(PlayerUsageSnooper.access$400(this.snooper));
            }

            var1.put("snooper_count", Integer.valueOf(PlayerUsageSnooper.access$308(this.snooper)));
            var1.put("snooper_token", PlayerUsageSnooper.access$500(this.snooper));
         }

         HttpUtil.func_151226_a(PlayerUsageSnooper.access$600(this.snooper), var1, true);
      }
   }
}
