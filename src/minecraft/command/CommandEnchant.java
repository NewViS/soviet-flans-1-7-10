package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;

public class CommandEnchant extends CommandBase {

   public String getCommandName() {
      return "enchant";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.enchant.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length < 2) {
         throw new WrongUsageException("commands.enchant.usage", new Object[0]);
      } else {
         EntityPlayerMP var3 = getPlayer(var1, var2[0]);
         int var4 = parseIntBounded(var1, var2[1], 0, Enchantment.enchantmentsList.length - 1);
         int var5 = 1;
         ItemStack var6 = var3.getCurrentEquippedItem();
         if(var6 == null) {
            throw new CommandException("commands.enchant.noItem", new Object[0]);
         } else {
            Enchantment var7 = Enchantment.enchantmentsList[var4];
            if(var7 == null) {
               throw new NumberInvalidException("commands.enchant.notFound", new Object[]{Integer.valueOf(var4)});
            } else if(!var7.canApply(var6)) {
               throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
            } else {
               if(var2.length >= 3) {
                  var5 = parseIntBounded(var1, var2[2], var7.getMinLevel(), var7.getMaxLevel());
               }

               if(var6.hasTagCompound()) {
                  NBTTagList var8 = var6.getEnchantmentTagList();
                  if(var8 != null) {
                     for(int var9 = 0; var9 < var8.tagCount(); ++var9) {
                        short var10 = var8.getCompoundTagAt(var9).getShort("id");
                        if(Enchantment.enchantmentsList[var10] != null) {
                           Enchantment var11 = Enchantment.enchantmentsList[var10];
                           if(!var11.canApplyTogether(var7)) {
                              throw new CommandException("commands.enchant.cantCombine", new Object[]{var7.getTranslatedName(var5), var11.getTranslatedName(var8.getCompoundTagAt(var9).getShort("lvl"))});
                           }
                        }
                     }
                  }
               }

               var6.addEnchantment(var7, var5);
               func_152373_a(var1, this, "commands.enchant.success", new Object[0]);
            }
         }
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, this.getListOfPlayers()):null;
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 0;
   }
}
