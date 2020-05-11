package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class CommandDifficulty extends CommandBase {

   public String getCommandName() {
      return "difficulty";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.difficulty.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length > 0) {
         EnumDifficulty var3 = this.func_147201_h(var1, var2[0]);
         MinecraftServer.getServer().func_147139_a(var3);
         func_152373_a(var1, this, "commands.difficulty.success", new Object[]{new ChatComponentTranslation(var3.getDifficultyResourceKey(), new Object[0])});
      } else {
         throw new WrongUsageException("commands.difficulty.usage", new Object[0]);
      }
   }

   protected EnumDifficulty func_147201_h(ICommandSender var1, String var2) {
      return !var2.equalsIgnoreCase("peaceful") && !var2.equalsIgnoreCase("p")?(!var2.equalsIgnoreCase("easy") && !var2.equalsIgnoreCase("e")?(!var2.equalsIgnoreCase("normal") && !var2.equalsIgnoreCase("n")?(!var2.equalsIgnoreCase("hard") && !var2.equalsIgnoreCase("h")?EnumDifficulty.getDifficultyEnum(parseIntBounded(var1, var2, 0, 3)):EnumDifficulty.HARD):EnumDifficulty.NORMAL):EnumDifficulty.EASY):EnumDifficulty.PEACEFUL;
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, new String[]{"peaceful", "easy", "normal", "hard"}):null;
   }
}
