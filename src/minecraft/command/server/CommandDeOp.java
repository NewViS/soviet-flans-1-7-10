package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

public class CommandDeOp extends CommandBase {

   public String getCommandName() {
      return "deop";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.deop.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length == 1 && var2[0].length() > 0) {
         MinecraftServer var3 = MinecraftServer.getServer();
         GameProfile var4 = var3.getConfigurationManager().func_152603_m().func_152700_a(var2[0]);
         if(var4 == null) {
            throw new CommandException("commands.deop.failed", new Object[]{var2[0]});
         } else {
            var3.getConfigurationManager().func_152610_b(var4);
            func_152373_a(var1, this, "commands.deop.success", new Object[]{var2[0]});
         }
      } else {
         throw new WrongUsageException("commands.deop.usage", new Object[0]);
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getConfigurationManager().func_152606_n()):null;
   }
}
