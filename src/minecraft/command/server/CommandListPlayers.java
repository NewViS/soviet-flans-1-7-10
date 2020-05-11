package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandListPlayers extends CommandBase {

   public String getCommandName() {
      return "list";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.players.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      var1.addChatMessage(new ChatComponentTranslation("commands.players.list", new Object[]{Integer.valueOf(MinecraftServer.getServer().getCurrentPlayerCount()), Integer.valueOf(MinecraftServer.getServer().getMaxPlayers())}));
      var1.addChatMessage(new ChatComponentText(MinecraftServer.getServer().getConfigurationManager().func_152609_b(var2.length > 0 && "uuids".equalsIgnoreCase(var2[0]))));
   }
}
