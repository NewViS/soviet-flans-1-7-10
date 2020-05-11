package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandXP extends CommandBase {

   public String getCommandName() {
      return "xp";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.xp.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length <= 0) {
         throw new WrongUsageException("commands.xp.usage", new Object[0]);
      } else {
         String var4 = var2[0];
         boolean var5 = var4.endsWith("l") || var4.endsWith("L");
         if(var5 && var4.length() > 1) {
            var4 = var4.substring(0, var4.length() - 1);
         }

         int var6 = parseInt(var1, var4);
         boolean var7 = var6 < 0;
         if(var7) {
            var6 *= -1;
         }

         EntityPlayerMP var3;
         if(var2.length > 1) {
            var3 = getPlayer(var1, var2[1]);
         } else {
            var3 = getCommandSenderAsPlayer(var1);
         }

         if(var5) {
            if(var7) {
               var3.addExperienceLevel(-var6);
               func_152373_a(var1, this, "commands.xp.success.negative.levels", new Object[]{Integer.valueOf(var6), var3.getCommandSenderName()});
            } else {
               var3.addExperienceLevel(var6);
               func_152373_a(var1, this, "commands.xp.success.levels", new Object[]{Integer.valueOf(var6), var3.getCommandSenderName()});
            }
         } else {
            if(var7) {
               throw new WrongUsageException("commands.xp.failure.widthdrawXp", new Object[0]);
            }

            var3.addExperience(var6);
            func_152373_a(var1, this, "commands.xp.success", new Object[]{Integer.valueOf(var6), var3.getCommandSenderName()});
         }

      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 2?getListOfStringsMatchingLastWord(var2, this.getAllUsernames()):null;
   }

   protected String[] getAllUsernames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 1;
   }
}
