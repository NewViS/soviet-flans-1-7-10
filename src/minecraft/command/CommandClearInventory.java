package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;

public class CommandClearInventory extends CommandBase {

   public String getCommandName() {
      return "clear";
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.clear.usage";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      EntityPlayerMP var3 = var2.length == 0?getCommandSenderAsPlayer(var1):getPlayer(var1, var2[0]);
      Item var4 = var2.length >= 2?getItemByText(var1, var2[1]):null;
      int var5 = var2.length >= 3?parseIntWithMin(var1, var2[2], 0):-1;
      if(var2.length >= 2 && var4 == null) {
         throw new CommandException("commands.clear.failure", new Object[]{var3.getCommandSenderName()});
      } else {
         int var6 = var3.inventory.clearInventory(var4, var5);
         var3.inventoryContainer.detectAndSendChanges();
         if(!var3.capabilities.isCreativeMode) {
            var3.updateHeldItem();
         }

         if(var6 == 0) {
            throw new CommandException("commands.clear.failure", new Object[]{var3.getCommandSenderName()});
         } else {
            func_152373_a(var1, this, "commands.clear.success", new Object[]{var3.getCommandSenderName(), Integer.valueOf(var6)});
         }
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, this.func_147209_d()):(var2.length == 2?getListOfStringsFromIterableMatchingLastWord(var2, Item.itemRegistry.getKeys()):null);
   }

   protected String[] func_147209_d() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }
}
