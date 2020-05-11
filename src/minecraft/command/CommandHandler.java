package net.minecraft.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandHandler implements ICommandManager {

   private static final Logger logger = LogManager.getLogger();
   private final Map commandMap = new HashMap();
   private final Set commandSet = new HashSet();


   public int executeCommand(ICommandSender var1, String var2) {
      var2 = var2.trim();
      if(var2.startsWith("/")) {
         var2 = var2.substring(1);
      }

      String[] var3 = var2.split(" ");
      String var4 = var3[0];
      var3 = dropFirstString(var3);
      ICommand var5 = (ICommand)this.commandMap.get(var4);
      int var6 = this.getUsernameIndex(var5, var3);
      int var7 = 0;

      ChatComponentTranslation var9;
      try {
         if(var5 == null) {
            throw new CommandNotFoundException();
         }

         if(var5.canCommandSenderUseCommand(var1)) {
            if(var6 > -1) {
               EntityPlayerMP[] var8 = PlayerSelector.matchPlayers(var1, var3[var6]);
               String var22 = var3[var6];
               EntityPlayerMP[] var10 = var8;
               int var11 = var8.length;

               for(int var12 = 0; var12 < var11; ++var12) {
                  EntityPlayerMP var13 = var10[var12];
                  var3[var6] = var13.getCommandSenderName();

                  try {
                     var5.processCommand(var1, var3);
                     ++var7;
                  } catch (CommandException var17) {
                     ChatComponentTranslation var15 = new ChatComponentTranslation(var17.getMessage(), var17.getErrorOjbects());
                     var15.getChatStyle().setColor(EnumChatFormatting.RED);
                     var1.addChatMessage(var15);
                  }
               }

               var3[var6] = var22;
            } else {
               try {
                  var5.processCommand(var1, var3);
                  ++var7;
               } catch (CommandException var16) {
                  var9 = new ChatComponentTranslation(var16.getMessage(), var16.getErrorOjbects());
                  var9.getChatStyle().setColor(EnumChatFormatting.RED);
                  var1.addChatMessage(var9);
               }
            }
         } else {
            ChatComponentTranslation var21 = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
            var21.getChatStyle().setColor(EnumChatFormatting.RED);
            var1.addChatMessage(var21);
         }
      } catch (WrongUsageException var18) {
         var9 = new ChatComponentTranslation("commands.generic.usage", new Object[]{new ChatComponentTranslation(var18.getMessage(), var18.getErrorOjbects())});
         var9.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var9);
      } catch (CommandException var19) {
         var9 = new ChatComponentTranslation(var19.getMessage(), var19.getErrorOjbects());
         var9.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var9);
      } catch (Throwable var20) {
         var9 = new ChatComponentTranslation("commands.generic.exception", new Object[0]);
         var9.getChatStyle().setColor(EnumChatFormatting.RED);
         var1.addChatMessage(var9);
         logger.error("Couldn\'t process command: \'" + var2 + "\'", var20);
      }

      return var7;
   }

   public ICommand registerCommand(ICommand var1) {
      List var2 = var1.getCommandAliases();
      this.commandMap.put(var1.getCommandName(), var1);
      this.commandSet.add(var1);
      if(var2 != null) {
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            String var4 = (String)var3.next();
            ICommand var5 = (ICommand)this.commandMap.get(var4);
            if(var5 == null || !var5.getCommandName().equals(var4)) {
               this.commandMap.put(var4, var1);
            }
         }
      }

      return var1;
   }

   private static String[] dropFirstString(String[] var0) {
      String[] var1 = new String[var0.length - 1];

      for(int var2 = 1; var2 < var0.length; ++var2) {
         var1[var2 - 1] = var0[var2];
      }

      return var1;
   }

   public List getPossibleCommands(ICommandSender var1, String var2) {
      String[] var3 = var2.split(" ", -1);
      String var4 = var3[0];
      if(var3.length == 1) {
         ArrayList var8 = new ArrayList();
         Iterator var6 = this.commandMap.entrySet().iterator();

         while(var6.hasNext()) {
            Entry var7 = (Entry)var6.next();
            if(CommandBase.doesStringStartWith(var4, (String)var7.getKey()) && ((ICommand)var7.getValue()).canCommandSenderUseCommand(var1)) {
               var8.add(var7.getKey());
            }
         }

         return var8;
      } else {
         if(var3.length > 1) {
            ICommand var5 = (ICommand)this.commandMap.get(var4);
            if(var5 != null) {
               return var5.addTabCompletionOptions(var1, dropFirstString(var3));
            }
         }

         return null;
      }
   }

   public List getPossibleCommands(ICommandSender var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.commandSet.iterator();

      while(var3.hasNext()) {
         ICommand var4 = (ICommand)var3.next();
         if(var4.canCommandSenderUseCommand(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public Map getCommands() {
      return this.commandMap;
   }

   private int getUsernameIndex(ICommand var1, String[] var2) {
      if(var1 == null) {
         return -1;
      } else {
         for(int var3 = 0; var3 < var2.length; ++var3) {
            if(var1.isUsernameIndex(var2, var3) && PlayerSelector.matchesMultiplePlayers(var2[var3])) {
               return var3;
            }
         }

         return -1;
      }
   }

}
