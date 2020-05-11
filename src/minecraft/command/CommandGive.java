package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class CommandGive extends CommandBase {

   public String getCommandName() {
      return "give";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.give.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length < 2) {
         throw new WrongUsageException("commands.give.usage", new Object[0]);
      } else {
         EntityPlayerMP var3 = getPlayer(var1, var2[0]);
         Item var4 = getItemByText(var1, var2[1]);
         int var5 = 1;
         int var6 = 0;
         if(var2.length >= 3) {
            var5 = parseIntBounded(var1, var2[2], 1, 64);
         }

         if(var2.length >= 4) {
            var6 = parseInt(var1, var2[3]);
         }

         ItemStack var7 = new ItemStack(var4, var5, var6);
         if(var2.length >= 5) {
            String var8 = func_147178_a(var1, var2, 4).getUnformattedText();

            try {
               NBTBase var9 = JsonToNBT.func_150315_a(var8);
               if(!(var9 instanceof NBTTagCompound)) {
                  func_152373_a(var1, this, "commands.give.tagError", new Object[]{"Not a valid tag"});
                  return;
               }

               var7.setTagCompound((NBTTagCompound)var9);
            } catch (NBTException var10) {
               func_152373_a(var1, this, "commands.give.tagError", new Object[]{var10.getMessage()});
               return;
            }
         }

         EntityItem var11 = var3.dropPlayerItemWithRandomChoice(var7, false);
         var11.delayBeforeCanPickup = 0;
         var11.func_145797_a(var3.getCommandSenderName());
         func_152373_a(var1, this, "commands.give.success", new Object[]{var7.func_151000_E(), Integer.valueOf(var5), var3.getCommandSenderName()});
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, this.getPlayers()):(var2.length == 2?getListOfStringsFromIterableMatchingLastWord(var2, Item.itemRegistry.getKeys()):null);
   }

   protected String[] getPlayers() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }
}
