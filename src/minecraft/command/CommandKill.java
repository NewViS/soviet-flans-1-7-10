package net.minecraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;

public class CommandKill extends CommandBase {

   public String getCommandName() {
      return "kill";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.kill.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      EntityPlayerMP var3 = getCommandSenderAsPlayer(var1);
      var3.attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
      var1.addChatMessage(new ChatComponentTranslation("commands.kill.success", new Object[0]));
   }
}
