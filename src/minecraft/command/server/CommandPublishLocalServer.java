package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldSettings$GameType;

public class CommandPublishLocalServer extends CommandBase {

   public String getCommandName() {
      return "publish";
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.publish.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      String var3 = MinecraftServer.getServer().shareToLAN(WorldSettings$GameType.SURVIVAL, false);
      if(var3 != null) {
         func_152373_a(var1, this, "commands.publish.started", new Object[]{var3});
      } else {
         func_152373_a(var1, this, "commands.publish.failed", new Object[0]);
      }

   }
}
