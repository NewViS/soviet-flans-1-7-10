package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandStop extends CommandBase {

   public String getCommandName() {
      return "stop";
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.stop.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(MinecraftServer.getServer().worldServers != null) {
         func_152373_a(var1, this, "commands.stop.start", new Object[0]);
      }

      MinecraftServer.getServer().initiateShutdown();
   }
}
