package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandServerKick extends CommandBase {

   public String getCommandName() {
      return "kick";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.kick.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length > 0 && var2[0].length() > 1) {
         EntityPlayerMP var3 = MinecraftServer.getServer().getConfigurationManager().func_152612_a(var2[0]);
         String var4 = "Kicked by an operator.";
         boolean var5 = false;
         if(var3 == null) {
            throw new PlayerNotFoundException();
         } else {
            if(var2.length >= 2) {
               var4 = func_147178_a(var1, var2, 1).getUnformattedText();
               var5 = true;
            }

            var3.playerNetServerHandler.kickPlayerFromServer(var4);
            if(var5) {
               func_152373_a(var1, this, "commands.kick.success.reason", new Object[]{var3.getCommandSenderName(), var4});
            } else {
               func_152373_a(var1, this, "commands.kick.success", new Object[]{var3.getCommandSenderName()});
            }

         }
      } else {
         throw new WrongUsageException("commands.kick.usage", new Object[0]);
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length >= 1?getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()):null;
   }
}
