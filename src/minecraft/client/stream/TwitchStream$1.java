package net.minecraft.client.stream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import net.minecraft.client.stream.IStream$AuthFailureReason;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.client.stream.TwitchStream$1$1;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.JsonUtils;
import tv.twitch.AuthToken;

class TwitchStream$1 extends Thread {

   // $FF: synthetic field
   final String field_153083_a;
   // $FF: synthetic field
   final TwitchStream field_153084_b;


   TwitchStream$1(TwitchStream var1, String var2, String var3) {
      super(var2);
      this.field_153084_b = var1;
      this.field_153083_a = var3;
   }

   public void run() {
      try {
         URL var1 = new URL("https://api.twitch.tv/kraken?oauth_token=" + URLEncoder.encode(this.field_153083_a, "UTF-8"));
         String var2 = HttpUtil.func_152755_a(var1);
         JsonObject var3 = JsonUtils.getJsonElementAsJsonObject((new JsonParser()).parse(var2), "Response");
         JsonObject var4 = JsonUtils.func_152754_s(var3, "token");
         if(JsonUtils.getJsonObjectBooleanFieldValue(var4, "valid")) {
            String var5 = JsonUtils.getJsonObjectStringFieldValue(var4, "user_name");
            TwitchStream.access$000().debug(TwitchStream.field_152949_a, "Authenticated with twitch; username is {}", new Object[]{var5});
            AuthToken var6 = new AuthToken();
            var6.data = this.field_153083_a;
            TwitchStream.access$100(this.field_153084_b).func_152818_a(var5, var6);
            TwitchStream.access$200(this.field_153084_b).func_152998_c(var5);
            TwitchStream.access$200(this.field_153084_b).func_152994_a(var6);
            Runtime.getRuntime().addShutdownHook(new TwitchStream$1$1(this, "Twitch shutdown hook"));
            TwitchStream.access$100(this.field_153084_b).func_152817_A();
         } else {
            TwitchStream.access$302(this.field_153084_b, IStream$AuthFailureReason.INVALID_TOKEN);
            TwitchStream.access$000().error(TwitchStream.field_152949_a, "Given twitch access token is invalid");
         }
      } catch (IOException var7) {
         TwitchStream.access$302(this.field_153084_b, IStream$AuthFailureReason.ERROR);
         TwitchStream.access$000().error(TwitchStream.field_152949_a, "Could not authenticate with twitch", var7);
      }

   }
}
