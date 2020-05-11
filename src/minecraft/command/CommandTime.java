package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandTime extends CommandBase {

   public String getCommandName() {
      return "time";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.time.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length > 1) {
         int var3;
         if(var2[0].equals("set")) {
            if(var2[1].equals("day")) {
               var3 = 1000;
            } else if(var2[1].equals("night")) {
               var3 = 13000;
            } else {
               var3 = parseIntWithMin(var1, var2[1], 0);
            }

            this.setTime(var1, var3);
            func_152373_a(var1, this, "commands.time.set", new Object[]{Integer.valueOf(var3)});
            return;
         }

         if(var2[0].equals("add")) {
            var3 = parseIntWithMin(var1, var2[1], 0);
            this.addTime(var1, var3);
            func_152373_a(var1, this, "commands.time.added", new Object[]{Integer.valueOf(var3)});
            return;
         }
      }

      throw new WrongUsageException("commands.time.usage", new Object[0]);
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, new String[]{"set", "add"}):(var2.length == 2 && var2[0].equals("set")?getListOfStringsMatchingLastWord(var2, new String[]{"day", "night"}):null);
   }

   protected void setTime(ICommandSender var1, int var2) {
      for(int var3 = 0; var3 < MinecraftServer.getServer().worldServers.length; ++var3) {
         MinecraftServer.getServer().worldServers[var3].setWorldTime((long)var2);
      }

   }

   protected void addTime(ICommandSender var1, int var2) {
      for(int var3 = 0; var3 < MinecraftServer.getServer().worldServers.length; ++var3) {
         WorldServer var4 = MinecraftServer.getServer().worldServers[var3];
         var4.setWorldTime(var4.getWorldTime() + (long)var2);
      }

   }
}
