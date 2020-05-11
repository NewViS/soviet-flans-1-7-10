package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTeleport extends CommandBase {

   public String getCommandName() {
      return "tp";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.tp.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length < 1) {
         throw new WrongUsageException("commands.tp.usage", new Object[0]);
      } else {
         EntityPlayerMP var3;
         if(var2.length != 2 && var2.length != 4) {
            var3 = getCommandSenderAsPlayer(var1);
         } else {
            var3 = getPlayer(var1, var2[0]);
            if(var3 == null) {
               throw new PlayerNotFoundException();
            }
         }

         if(var2.length != 3 && var2.length != 4) {
            if(var2.length == 1 || var2.length == 2) {
               EntityPlayerMP var11 = getPlayer(var1, var2[var2.length - 1]);
               if(var11 == null) {
                  throw new PlayerNotFoundException();
               }

               if(var11.worldObj != var3.worldObj) {
                  func_152373_a(var1, this, "commands.tp.notSameDimension", new Object[0]);
                  return;
               }

               var3.mountEntity((Entity)null);
               var3.playerNetServerHandler.setPlayerLocation(var11.posX, var11.posY, var11.posZ, var11.rotationYaw, var11.rotationPitch);
               func_152373_a(var1, this, "commands.tp.success", new Object[]{var3.getCommandSenderName(), var11.getCommandSenderName()});
            }
         } else if(var3.worldObj != null) {
            int var4 = var2.length - 3;
            double var5 = func_110666_a(var1, var3.posX, var2[var4++]);
            double var7 = func_110665_a(var1, var3.posY, var2[var4++], 0, 0);
            double var9 = func_110666_a(var1, var3.posZ, var2[var4++]);
            var3.mountEntity((Entity)null);
            var3.setPositionAndUpdate(var5, var7, var9);
            func_152373_a(var1, this, "commands.tp.success.coordinates", new Object[]{var3.getCommandSenderName(), Double.valueOf(var5), Double.valueOf(var7), Double.valueOf(var9)});
         }

      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length != 1 && var2.length != 2?null:getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }
}
