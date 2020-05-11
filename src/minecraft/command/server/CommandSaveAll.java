package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandSaveAll extends CommandBase {

   public String getCommandName() {
      return "save-all";
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.save.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      MinecraftServer var3 = MinecraftServer.getServer();
      var1.addChatMessage(new ChatComponentTranslation("commands.save.start", new Object[0]));
      if(var3.getConfigurationManager() != null) {
         var3.getConfigurationManager().saveAllPlayerData();
      }

      try {
         int var4;
         WorldServer var5;
         boolean var6;
         for(var4 = 0; var4 < var3.worldServers.length; ++var4) {
            if(var3.worldServers[var4] != null) {
               var5 = var3.worldServers[var4];
               var6 = var5.levelSaving;
               var5.levelSaving = false;
               var5.saveAllChunks(true, (IProgressUpdate)null);
               var5.levelSaving = var6;
            }
         }

         if(var2.length > 0 && "flush".equals(var2[0])) {
            var1.addChatMessage(new ChatComponentTranslation("commands.save.flushStart", new Object[0]));

            for(var4 = 0; var4 < var3.worldServers.length; ++var4) {
               if(var3.worldServers[var4] != null) {
                  var5 = var3.worldServers[var4];
                  var6 = var5.levelSaving;
                  var5.levelSaving = false;
                  var5.saveChunkData();
                  var5.levelSaving = var6;
               }
            }

            var1.addChatMessage(new ChatComponentTranslation("commands.save.flushEnd", new Object[0]));
         }
      } catch (MinecraftException var7) {
         func_152373_a(var1, this, "commands.save.failed", new Object[]{var7.getMessage()});
         return;
      }

      func_152373_a(var1, this, "commands.save.success", new Object[0]);
   }
}
