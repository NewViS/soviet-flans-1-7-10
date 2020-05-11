package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;

public class CommandSetSpawnpoint extends CommandBase {

   public String getCommandName() {
      return "spawnpoint";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.spawnpoint.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      EntityPlayerMP var3 = var2.length == 0?getCommandSenderAsPlayer(var1):getPlayer(var1, var2[0]);
      if(var2.length == 4) {
         if(var3.worldObj != null) {
            byte var4 = 1;
            int var5 = 30000000;
            int var9 = var4 + 1;
            int var6 = parseIntBounded(var1, var2[var4], -var5, var5);
            int var7 = parseIntBounded(var1, var2[var9++], 0, 256);
            int var8 = parseIntBounded(var1, var2[var9++], -var5, var5);
            var3.setSpawnChunk(new ChunkCoordinates(var6, var7, var8), true);
            func_152373_a(var1, this, "commands.spawnpoint.success", new Object[]{var3.getCommandSenderName(), Integer.valueOf(var6), Integer.valueOf(var7), Integer.valueOf(var8)});
         }
      } else {
         if(var2.length > 1) {
            throw new WrongUsageException("commands.spawnpoint.usage", new Object[0]);
         }

         ChunkCoordinates var10 = var3.getPlayerCoordinates();
         var3.setSpawnChunk(var10, true);
         func_152373_a(var1, this, "commands.spawnpoint.success", new Object[]{var3.getCommandSenderName(), Integer.valueOf(var10.posX), Integer.valueOf(var10.posY), Integer.valueOf(var10.posZ)});
      }

   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length != 1 && var2.length != 2?null:getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }
}
