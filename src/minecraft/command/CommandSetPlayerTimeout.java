package net.minecraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

public class CommandSetPlayerTimeout extends CommandBase {

   public String getCommandName() {
      return "setidletimeout";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.setidletimeout.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length == 1) {
         int var3 = parseIntWithMin(var1, var2[0], 0);
         MinecraftServer.getServer().func_143006_e(var3);
         func_152373_a(var1, this, "commands.setidletimeout.success", new Object[]{Integer.valueOf(var3)});
      } else {
         throw new WrongUsageException("commands.setidletimeout.usage", new Object[0]);
      }
   }
}
