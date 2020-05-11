package net.minecraft.command;

import java.util.Iterator;
import net.minecraft.command.CommandGameMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings$GameType;

public class CommandDefaultGameMode extends CommandGameMode {

   public String getCommandName() {
      return "defaultgamemode";
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.defaultgamemode.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length > 0) {
         WorldSettings$GameType var3 = this.getGameModeFromCommand(var1, var2[0]);
         this.setGameType(var3);
         func_152373_a(var1, this, "commands.defaultgamemode.success", new Object[]{new ChatComponentTranslation("gameMode." + var3.getName(), new Object[0])});
      } else {
         throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
      }
   }

   protected void setGameType(WorldSettings$GameType var1) {
      MinecraftServer var2 = MinecraftServer.getServer();
      var2.setGameType(var1);
      EntityPlayerMP var4;
      if(var2.getForceGamemode()) {
         for(Iterator var3 = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator(); var3.hasNext(); var4.fallDistance = 0.0F) {
            var4 = (EntityPlayerMP)var3.next();
            var4.setGameType(var1);
         }
      }

   }
}
