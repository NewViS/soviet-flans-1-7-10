package net.minecraft.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.HttpUtil$1;
import net.minecraft.util.HttpUtil$DownloadListener;
import net.minecraft.util.IProgressUpdate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpUtil {

   private static final AtomicInteger downloadThreadsStarted = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();


   public static String buildPostString(Map var0) {
      StringBuilder var1 = new StringBuilder();
      Iterator var2 = var0.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         if(var1.length() > 0) {
            var1.append('&');
         }

         try {
            var1.append(URLEncoder.encode((String)var3.getKey(), "UTF-8"));
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
         }

         if(var3.getValue() != null) {
            var1.append('=');

            try {
               var1.append(URLEncoder.encode(var3.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException var5) {
               var5.printStackTrace();
            }
         }
      }

      return var1.toString();
   }

   public static String func_151226_a(URL var0, Map var1, boolean var2) {
      return func_151225_a(var0, buildPostString(var1), var2);
   }

   private static String func_151225_a(URL var0, String var1, boolean var2) {
      try {
         Proxy var3 = MinecraftServer.getServer() == null?null:MinecraftServer.getServer().getServerProxy();
         if(var3 == null) {
            var3 = Proxy.NO_PROXY;
         }

         HttpURLConnection var4 = (HttpURLConnection)var0.openConnection(var3);
         var4.setRequestMethod("POST");
         var4.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         var4.setRequestProperty("Content-Length", "" + var1.getBytes().length);
         var4.setRequestProperty("Content-Language", "en-US");
         var4.setUseCaches(false);
         var4.setDoInput(true);
         var4.setDoOutput(true);
         DataOutputStream var5 = new DataOutputStream(var4.getOutputStream());
         var5.writeBytes(var1);
         var5.flush();
         var5.close();
         BufferedReader var6 = new BufferedReader(new InputStreamReader(var4.getInputStream()));
         StringBuffer var8 = new StringBuffer();

         String var7;
         while((var7 = var6.readLine()) != null) {
            var8.append(var7);
            var8.append('\r');
         }

         var6.close();
         return var8.toString();
      } catch (Exception var9) {
         if(!var2) {
            logger.error("Could not post to " + var0, var9);
         }

         return "";
      }
   }

   public static void func_151223_a(File var0, String var1, HttpUtil$DownloadListener var2, Map var3, int var4, IProgressUpdate var5, Proxy var6) {
      Thread var7 = new Thread(new HttpUtil$1(var5, var1, var6, var3, var0, var2, var4), "File Downloader #" + downloadThreadsStarted.incrementAndGet());
      var7.setDaemon(true);
      var7.start();
   }

   public static int func_76181_a() {
      ServerSocket var0 = null;
      boolean var1 = true;

      int var10;
      try {
         var0 = new ServerSocket(0);
         var10 = var0.getLocalPort();
      } finally {
         try {
            if(var0 != null) {
               var0.close();
            }
         } catch (IOException var8) {
            ;
         }

      }

      return var10;
   }

   public static String func_152755_a(URL var0) {
      HttpURLConnection var1 = (HttpURLConnection)var0.openConnection();
      var1.setRequestMethod("GET");
      BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
      StringBuilder var4 = new StringBuilder();

      String var3;
      while((var3 = var2.readLine()) != null) {
         var4.append(var3);
         var4.append('\r');
      }

      var2.close();
      return var4.toString();
   }

   // $FF: synthetic method
   static Logger access$000() {
      return logger;
   }

}
