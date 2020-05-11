package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;

public class CommandGameRule extends CommandBase {

   public String getCommandName() {
      return "gamerule";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.gamerule.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      String var6;
      if(var2.length == 2) {
         var6 = var2[0];
         String var7 = var2[1];
         GameRules var8 = this.getGameRules();
         if(var8.hasRule(var6)) {
            var8.setOrCreateGameRule(var6, var7);
            func_152373_a(var1, this, "commands.gamerule.success", new Object[0]);
         } else {
            func_152373_a(var1, this, "commands.gamerule.norule", new Object[]{var6});
         }

      } else if(var2.length == 1) {
         var6 = var2[0];
         GameRules var4 = this.getGameRules();
         if(var4.hasRule(var6)) {
            String var5 = var4.getGameRuleStringValue(var6);
            var1.addChatMessage((new ChatComponentText(var6)).appendText(" = ").appendText(var5));
         } else {
            func_152373_a(var1, this, "commands.gamerule.norule", new Object[]{var6});
         }

      } else if(var2.length == 0) {
         GameRules var3 = this.getGameRules();
         var1.addChatMessage(new ChatComponentText(joinNiceString(var3.getRules())));
      } else {
         throw new WrongUsageException("commands.gamerule.usage", new Object[0]);
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, this.getGameRules().getRules()):(var2.length == 2?getListOfStringsMatchingLastWord(var2, new String[]{"true", "false"}):null);
   }

   private GameRules getGameRules() {
      return MinecraftServer.getServer().worldServerForDimension(0).getGameRules();
   }
}
