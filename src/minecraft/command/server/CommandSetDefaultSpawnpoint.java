package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChunkCoordinates;

public class CommandSetDefaultSpawnpoint extends CommandBase {

   public String getCommandName() {
      return "setworldspawn";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.setworldspawn.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length == 3) {
         if(var1.getEntityWorld() == null) {
            throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
         }

         byte var3 = 0;
         int var7 = var3 + 1;
         int var4 = parseIntBounded(var1, var2[var3], -30000000, 30000000);
         int var5 = parseIntBounded(var1, var2[var7++], 0, 256);
         int var6 = parseIntBounded(var1, var2[var7++], -30000000, 30000000);
         var1.getEntityWorld().setSpawnLocation(var4, var5, var6);
         func_152373_a(var1, this, "commands.setworldspawn.success", new Object[]{Integer.valueOf(var4), Integer.valueOf(var5), Integer.valueOf(var6)});
      } else {
         if(var2.length != 0) {
            throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
         }

         ChunkCoordinates var8 = getCommandSenderAsPlayer(var1).getPlayerCoordinates();
         var1.getEntityWorld().setSpawnLocation(var8.posX, var8.posY, var8.posZ);
         func_152373_a(var1, this, "commands.setworldspawn.success", new Object[]{Integer.valueOf(var8.posX), Integer.valueOf(var8.posY), Integer.valueOf(var8.posZ)});
      }

   }
}
