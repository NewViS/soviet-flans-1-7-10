package net.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.ServerData$ServerResourceMode;
import net.minecraft.nbt.NBTTagCompound;

public class ServerData {

   public String serverName;
   public String serverIP;
   public String populationInfo;
   public String serverMOTD;
   public long pingToServer;
   public int field_82821_f;
   public String gameVersion;
   public boolean field_78841_f;
   public String field_147412_i;
   private ServerData$ServerResourceMode field_152587_j;
   private String field_147411_m;
   private boolean field_152588_l;


   public ServerData(String var1, String var2) {
      this.field_82821_f = 5;
      this.gameVersion = "1.7.10";
      this.field_152587_j = ServerData$ServerResourceMode.PROMPT;
      this.serverName = var1;
      this.serverIP = var2;
   }

   public ServerData(String var1, String var2, boolean var3) {
      this(var1, var2);
      this.field_152588_l = var3;
   }

   public NBTTagCompound getNBTCompound() {
      NBTTagCompound var1 = new NBTTagCompound();
      var1.setString("name", this.serverName);
      var1.setString("ip", this.serverIP);
      if(this.field_147411_m != null) {
         var1.setString("icon", this.field_147411_m);
      }

      if(this.field_152587_j == ServerData$ServerResourceMode.ENABLED) {
         var1.setBoolean("acceptTextures", true);
      } else if(this.field_152587_j == ServerData$ServerResourceMode.DISABLED) {
         var1.setBoolean("acceptTextures", false);
      }

      return var1;
   }

   public ServerData$ServerResourceMode func_152586_b() {
      return this.field_152587_j;
   }

   public void func_152584_a(ServerData$ServerResourceMode var1) {
      this.field_152587_j = var1;
   }

   public static ServerData getServerDataFromNBTCompound(NBTTagCompound var0) {
      ServerData var1 = new ServerData(var0.getString("name"), var0.getString("ip"));
      if(var0.hasKey("icon", 8)) {
         var1.func_147407_a(var0.getString("icon"));
      }

      if(var0.hasKey("acceptTextures", 1)) {
         if(var0.getBoolean("acceptTextures")) {
            var1.func_152584_a(ServerData$ServerResourceMode.ENABLED);
         } else {
            var1.func_152584_a(ServerData$ServerResourceMode.DISABLED);
         }
      } else {
         var1.func_152584_a(ServerData$ServerResourceMode.PROMPT);
      }

      return var1;
   }

   public String getBase64EncodedIconData() {
      return this.field_147411_m;
   }

   public void func_147407_a(String var1) {
      this.field_147411_m = var1;
   }

   public void func_152583_a(ServerData var1) {
      this.serverIP = var1.serverIP;
      this.serverName = var1.serverName;
      this.func_152584_a(var1.func_152586_b());
      this.field_147411_m = var1.field_147411_m;
   }

   public boolean func_152585_d() {
      return this.field_152588_l;
   }
}
