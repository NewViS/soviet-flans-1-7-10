package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandWhitelist extends CommandBase {

   public String getCommandName() {
      return "whitelist";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.whitelist.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length >= 1) {
         MinecraftServer var3 = MinecraftServer.getServer();
         if(var2[0].equals("on")) {
            var3.getConfigurationManager().setWhiteListEnabled(true);
            func_152373_a(var1, this, "commands.whitelist.enabled", new Object[0]);
            return;
         }

         if(var2[0].equals("off")) {
            var3.getConfigurationManager().setWhiteListEnabled(false);
            func_152373_a(var1, this, "commands.whitelist.disabled", new Object[0]);
            return;
         }

         if(var2[0].equals("list")) {
            var1.addChatMessage(new ChatComponentTranslation("commands.whitelist.list", new Object[]{Integer.valueOf(var3.getConfigurationManager().func_152598_l().length), Integer.valueOf(var3.getConfigurationManager().getAvailablePlayerDat().length)}));
            String[] var5 = var3.getConfigurationManager().func_152598_l();
            var1.addChatMessage(new ChatComponentText(joinNiceString(var5)));
            return;
         }

         GameProfile var4;
         if(var2[0].equals("add")) {
            if(var2.length < 2) {
               throw new WrongUsageException("commands.whitelist.add.usage", new Object[0]);
            }

            var4 = var3.func_152358_ax().func_152655_a(var2[1]);
            if(var4 == null) {
               throw new CommandException("commands.whitelist.add.failed", new Object[]{var2[1]});
            }

            var3.getConfigurationManager().func_152601_d(var4);
            func_152373_a(var1, this, "commands.whitelist.add.success", new Object[]{var2[1]});
            return;
         }

         if(var2[0].equals("remove")) {
            if(var2.length < 2) {
               throw new WrongUsageException("commands.whitelist.remove.usage", new Object[0]);
            }

            var4 = var3.getConfigurationManager().func_152599_k().func_152706_a(var2[1]);
            if(var4 == null) {
               throw new CommandException("commands.whitelist.remove.failed", new Object[]{var2[1]});
            }

            var3.getConfigurationManager().func_152597_c(var4);
            func_152373_a(var1, this, "commands.whitelist.remove.success", new Object[]{var2[1]});
            return;
         }

         if(var2[0].equals("reload")) {
            var3.getConfigurationManager().loadWhiteList();
            func_152373_a(var1, this, "commands.whitelist.reloaded", new Object[0]);
            return;
         }
      }

      throw new WrongUsageException("commands.whitelist.usage", new Object[0]);
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      if(var2.length == 1) {
         return getListOfStringsMatchingLastWord(var2, new String[]{"on", "off", "list", "add", "remove", "reload"});
      } else {
         if(var2.length == 2) {
            if(var2[0].equals("remove")) {
               return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getConfigurationManager().func_152598_l());
            }

            if(var2[0].equals("add")) {
               return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().func_152358_ax().func_152654_a());
            }
         }

         return null;
      }
   }
}
