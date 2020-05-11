package net.minecraft.client.network;

import net.minecraft.client.Minecraft;

public class LanServerDetector$LanServer {

   private String lanServerMotd;
   private String lanServerIpPort;
   private long timeLastSeen;


   public LanServerDetector$LanServer(String var1, String var2) {
      this.lanServerMotd = var1;
      this.lanServerIpPort = var2;
      this.timeLastSeen = Minecraft.getSystemTime();
   }

   public String getServerMotd() {
      return this.lanServerMotd;
   }

   public String getServerIpPort() {
      return this.lanServerIpPort;
   }

   public void updateLastSeen() {
      this.timeLastSeen = Minecraft.getSystemTime();
   }
}
