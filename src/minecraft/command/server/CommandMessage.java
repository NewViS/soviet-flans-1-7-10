package net.minecraft.command.server;

import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class CommandMessage extends CommandBase {

   public List getCommandAliases() {
      return Arrays.asList(new String[]{"w", "msg"});
   }

   public String getCommandName() {
      return "tell";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.message.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length < 2) {
         throw new WrongUsageException("commands.message.usage", new Object[0]);
      } else {
         EntityPlayerMP var3 = getPlayer(var1, var2[0]);
         if(var3 == null) {
            throw new PlayerNotFoundException();
         } else if(var3 == var1) {
            throw new PlayerNotFoundException("commands.message.sameTarget", new Object[0]);
         } else {
            IChatComponent var4 = func_147176_a(var1, var2, 1, !(var1 instanceof EntityPlayer));
            ChatComponentTranslation var5 = new ChatComponentTranslation("commands.message.display.incoming", new Object[]{var1.func_145748_c_(), var4.createCopy()});
            ChatComponentTranslation var6 = new ChatComponentTranslation("commands.message.display.outgoing", new Object[]{var3.func_145748_c_(), var4.createCopy()});
            var5.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(Boolean.valueOf(true));
            var6.getChatStyle().setColor(EnumChatFormatting.GRAY).setItalic(Boolean.valueOf(true));
            var3.addChatMessage(var5);
            var1.addChatMessage(var6);
         }
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames());
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }
}
