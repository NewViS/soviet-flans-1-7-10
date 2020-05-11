package net.minecraft.command.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class CommandAchievement extends CommandBase {

   public String getCommandName() {
      return "achievement";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.achievement.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length >= 2) {
         StatBase var3 = StatList.func_151177_a(var2[1]);
         if(var3 == null && !var2[1].equals("*")) {
            throw new CommandException("commands.achievement.unknownAchievement", new Object[]{var2[1]});
         }

         EntityPlayerMP var4;
         if(var2.length >= 3) {
            var4 = getPlayer(var1, var2[2]);
         } else {
            var4 = getCommandSenderAsPlayer(var1);
         }

         if(var2[0].equalsIgnoreCase("give")) {
            if(var3 == null) {
               Iterator var5 = AchievementList.achievementList.iterator();

               while(var5.hasNext()) {
                  Achievement var6 = (Achievement)var5.next();
                  var4.triggerAchievement(var6);
               }

               func_152373_a(var1, this, "commands.achievement.give.success.all", new Object[]{var4.getCommandSenderName()});
            } else {
               if(var3 instanceof Achievement) {
                  Achievement var9 = (Achievement)var3;

                  ArrayList var10;
                  for(var10 = Lists.newArrayList(); var9.parentAchievement != null && !var4.func_147099_x().hasAchievementUnlocked(var9.parentAchievement); var9 = var9.parentAchievement) {
                     var10.add(var9.parentAchievement);
                  }

                  Iterator var7 = Lists.reverse(var10).iterator();

                  while(var7.hasNext()) {
                     Achievement var8 = (Achievement)var7.next();
                     var4.triggerAchievement(var8);
                  }
               }

               var4.triggerAchievement(var3);
               func_152373_a(var1, this, "commands.achievement.give.success.one", new Object[]{var4.getCommandSenderName(), var3.func_150955_j()});
            }

            return;
         }
      }

      throw new WrongUsageException("commands.achievement.usage", new Object[0]);
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      if(var2.length == 1) {
         return getListOfStringsMatchingLastWord(var2, new String[]{"give"});
      } else if(var2.length != 2) {
         return var2.length == 3?getListOfStringsMatchingLastWord(var2, MinecraftServer.getServer().getAllUsernames()):null;
      } else {
         ArrayList var3 = Lists.newArrayList();
         Iterator var4 = StatList.allStats.iterator();

         while(var4.hasNext()) {
            StatBase var5 = (StatBase)var4.next();
            var3.add(var5.statId);
         }

         return getListOfStringsFromIterableMatchingLastWord(var2, var3);
      }
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return var2 == 2;
   }
}
