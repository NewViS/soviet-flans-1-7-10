package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.NetworkStatistics$PacketStat;
import net.minecraft.util.ChatComponentText;

public class CommandNetstat extends CommandBase {

   public String getCommandName() {
      return "netstat";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.players.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var1 instanceof EntityPlayer) {
         var1.addChatMessage(new ChatComponentText("Command is not available for players"));
      } else {
         if(var2.length > 0 && var2[0].length() > 1) {
            if("hottest-read".equals(var2[0])) {
               var1.addChatMessage(new ChatComponentText(NetworkManager.field_152462_h.func_152477_e().toString()));
            } else if("hottest-write".equals(var2[0])) {
               var1.addChatMessage(new ChatComponentText(NetworkManager.field_152462_h.func_152475_g().toString()));
            } else if("most-read".equals(var2[0])) {
               var1.addChatMessage(new ChatComponentText(NetworkManager.field_152462_h.func_152467_f().toString()));
            } else if("most-write".equals(var2[0])) {
               var1.addChatMessage(new ChatComponentText(NetworkManager.field_152462_h.func_152470_h().toString()));
            } else {
               NetworkStatistics$PacketStat var4;
               int var7;
               if("packet-read".equals(var2[0])) {
                  if(var2.length > 1 && var2[1].length() > 0) {
                     try {
                        var7 = Integer.parseInt(var2[1].trim());
                        var4 = NetworkManager.field_152462_h.func_152466_a(var7);
                        this.func_152375_a(var1, var7, var4);
                     } catch (Exception var6) {
                        var1.addChatMessage(new ChatComponentText("Packet " + var2[1] + " not found!"));
                     }
                  } else {
                     var1.addChatMessage(new ChatComponentText("Packet id is missing"));
                  }
               } else if("packet-write".equals(var2[0])) {
                  if(var2.length > 1 && var2[1].length() > 0) {
                     try {
                        var7 = Integer.parseInt(var2[1].trim());
                        var4 = NetworkManager.field_152462_h.func_152468_b(var7);
                        this.func_152375_a(var1, var7, var4);
                     } catch (Exception var5) {
                        var1.addChatMessage(new ChatComponentText("Packet " + var2[1] + " not found!"));
                     }
                  } else {
                     var1.addChatMessage(new ChatComponentText("Packet id is missing"));
                  }
               } else if("read-count".equals(var2[0])) {
                  var1.addChatMessage(new ChatComponentText("total-read-count" + String.valueOf(NetworkManager.field_152462_h.func_152472_c())));
               } else if("write-count".equals(var2[0])) {
                  var1.addChatMessage(new ChatComponentText("total-write-count" + String.valueOf(NetworkManager.field_152462_h.func_152473_d())));
               } else {
                  var1.addChatMessage(new ChatComponentText("Unrecognized: " + var2[0]));
               }
            }
         } else {
            String var3 = "reads: " + NetworkManager.field_152462_h.func_152465_a();
            var3 = var3 + ", writes: " + NetworkManager.field_152462_h.func_152471_b();
            var1.addChatMessage(new ChatComponentText(var3));
         }

      }
   }

   private void func_152375_a(ICommandSender var1, int var2, NetworkStatistics$PacketStat var3) {
      if(var3 != null) {
         var1.addChatMessage(new ChatComponentText(var3.toString()));
      } else {
         var1.addChatMessage(new ChatComponentText("Packet " + var2 + " not found!"));
      }

   }
}
