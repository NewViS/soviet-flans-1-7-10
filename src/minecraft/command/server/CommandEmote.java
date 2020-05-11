package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandEmote extends CommandBase {

   public String getCommandName() {
      return "me";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.me.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length > 0) {
         IChatComponent var3 = func_147176_a(var1, var2, 0, var1.canCommandSenderUseCommand(1, "me"));
         MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation("chat.type.emote", new Object[]{var1.func_145748_c_(), var3}));
      } else {
         throw new WrongUsageException("commands.me.usage", new Object[0]);
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
   }
}
