package net.minecraft.command.server;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.IPBanEntry;
import net.minecraft.util.IChatComponent;

public class CommandBanIp extends CommandBase {

   public static final Pattern field_147211_a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");


   public String getCommandName() {
      return "ban-ip";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return MinecraftServer.getServer().getConfigurationManager().getBannedIPs().func_152689_b() && super.canCommandSenderUseCommand(var1);
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.banip.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length >= 1 && var2[0].length() > 1) {
         Matcher var3 = field_147211_a.matcher(var2[0]);
         IChatComponent var4 = null;
         if(var2.length >= 2) {
            var4 = func_147178_a(var1, var2, 1);
         }

         if(var3.matches()) {
            this.func_147210_a(var1, var2[0], var4 == null?null:var4.getUnformattedText());
         } else {
            EntityPlayerMP var5 = MinecraftServer.getServer().getConfigurationManager().func_152612_a(var2[0]);
            if(var5 == null) {
               throw new PlayerNotFoundException("commands.banip.invalid", new Object[0]);
            }

            this.func_147210_a(var1, var5.getPlayerIP(), var4 == null?null:var4.getUnformattedText());
         }

      } else {
         throw new WrongUsageException("commands.banip.usage", new Object[0]);
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()):null;
   }

   protected void func_147210_a(ICommandSender var1, String var2, String var3) {
      IPBanEntry var4 = new IPBanEntry(var2, (Date)null, var1.getCommandSenderName(), (Date)null, var3);
      MinecraftServer.getServer().getConfigurationManager().getBannedIPs().func_152687_a(var4);
      List var5 = MinecraftServer.getServer().getConfigurationManager().getPlayerList(var2);
      String[] var6 = new String[var5.size()];
      int var7 = 0;

      EntityPlayerMP var9;
      for(Iterator var8 = var5.iterator(); var8.hasNext(); var6[var7++] = var9.getCommandSenderName()) {
         var9 = (EntityPlayerMP)var8.next();
         var9.playerNetServerHandler.kickPlayerFromServer("You have been IP banned.");
      }

      if(var5.isEmpty()) {
         func_152373_a(var1, this, "commands.banip.success", new Object[]{var2});
      } else {
         func_152373_a(var1, this, "commands.banip.success.players", new Object[]{var2, joinNiceString(var6)});
      }

   }

}
