package net.minecraft.command;

import com.google.common.primitives.Doubles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.IAdminCommand;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public abstract class CommandBase implements ICommand {

   private static IAdminCommand theAdmin;


   public int getRequiredPermissionLevel() {
      return 4;
   }

   public List getCommandAliases() {
      return null;
   }

   public boolean canCommandSenderUseCommand(ICommandSender var1) {
      return var1.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return null;
   }

   public static int parseInt(ICommandSender var0, String var1) {
      try {
         return Integer.parseInt(var1);
      } catch (NumberFormatException var3) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{var1});
      }
   }

   public static int parseIntWithMin(ICommandSender var0, String var1, int var2) {
      return parseIntBounded(var0, var1, var2, Integer.MAX_VALUE);
   }

   public static int parseIntBounded(ICommandSender var0, String var1, int var2, int var3) {
      int var4 = parseInt(var0, var1);
      if(var4 < var2) {
         throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[]{Integer.valueOf(var4), Integer.valueOf(var2)});
      } else if(var4 > var3) {
         throw new NumberInvalidException("commands.generic.num.tooBig", new Object[]{Integer.valueOf(var4), Integer.valueOf(var3)});
      } else {
         return var4;
      }
   }

   public static double parseDouble(ICommandSender var0, String var1) {
      try {
         double var2 = Double.parseDouble(var1);
         if(!Doubles.isFinite(var2)) {
            throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{var1});
         } else {
            return var2;
         }
      } catch (NumberFormatException var4) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{var1});
      }
   }

   public static double parseDoubleWithMin(ICommandSender var0, String var1, double var2) {
      return parseDoubleBounded(var0, var1, var2, Double.MAX_VALUE);
   }

   public static double parseDoubleBounded(ICommandSender var0, String var1, double var2, double var4) {
      double var6 = parseDouble(var0, var1);
      if(var6 < var2) {
         throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{Double.valueOf(var6), Double.valueOf(var2)});
      } else if(var6 > var4) {
         throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{Double.valueOf(var6), Double.valueOf(var4)});
      } else {
         return var6;
      }
   }

   public static boolean parseBoolean(ICommandSender var0, String var1) {
      if(!var1.equals("true") && !var1.equals("1")) {
         if(!var1.equals("false") && !var1.equals("0")) {
            throw new CommandException("commands.generic.boolean.invalid", new Object[]{var1});
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public static EntityPlayerMP getCommandSenderAsPlayer(ICommandSender var0) {
      if(var0 instanceof EntityPlayerMP) {
         return (EntityPlayerMP)var0;
      } else {
         throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
      }
   }

   public static EntityPlayerMP getPlayer(ICommandSender var0, String var1) {
      EntityPlayerMP var2 = PlayerSelector.matchOnePlayer(var0, var1);
      if(var2 != null) {
         return var2;
      } else {
         var2 = MinecraftServer.getServer().getConfigurationManager().func_152612_a(var1);
         if(var2 == null) {
            throw new PlayerNotFoundException();
         } else {
            return var2;
         }
      }
   }

   public static String func_96332_d(ICommandSender var0, String var1) {
      EntityPlayerMP var2 = PlayerSelector.matchOnePlayer(var0, var1);
      if(var2 != null) {
         return var2.getCommandSenderName();
      } else if(PlayerSelector.hasArguments(var1)) {
         throw new PlayerNotFoundException();
      } else {
         return var1;
      }
   }

   public static IChatComponent func_147178_a(ICommandSender var0, String[] var1, int var2) {
      return func_147176_a(var0, var1, var2, false);
   }

   public static IChatComponent func_147176_a(ICommandSender var0, String[] var1, int var2, boolean var3) {
      ChatComponentText var4 = new ChatComponentText("");

      for(int var5 = var2; var5 < var1.length; ++var5) {
         if(var5 > var2) {
            var4.appendText(" ");
         }

         Object var6 = new ChatComponentText(var1[var5]);
         if(var3) {
            IChatComponent var7 = PlayerSelector.func_150869_b(var0, var1[var5]);
            if(var7 != null) {
               var6 = var7;
            } else if(PlayerSelector.hasArguments(var1[var5])) {
               throw new PlayerNotFoundException();
            }
         }

         var4.appendSibling((IChatComponent)var6);
      }

      return var4;
   }

   public static String func_82360_a(ICommandSender var0, String[] var1, int var2) {
      StringBuilder var3 = new StringBuilder();

      for(int var4 = var2; var4 < var1.length; ++var4) {
         if(var4 > var2) {
            var3.append(" ");
         }

         String var5 = var1[var4];
         var3.append(var5);
      }

      return var3.toString();
   }

   public static double func_110666_a(ICommandSender var0, double var1, String var3) {
      return func_110665_a(var0, var1, var3, -30000000, 30000000);
   }

   public static double func_110665_a(ICommandSender var0, double var1, String var3, int var4, int var5) {
      boolean var6 = var3.startsWith("~");
      if(var6 && Double.isNaN(var1)) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{Double.valueOf(var1)});
      } else {
         double var7 = var6?var1:0.0D;
         if(!var6 || var3.length() > 1) {
            boolean var9 = var3.contains(".");
            if(var6) {
               var3 = var3.substring(1);
            }

            var7 += parseDouble(var0, var3);
            if(!var9 && !var6) {
               var7 += 0.5D;
            }
         }

         if(var4 != 0 || var5 != 0) {
            if(var7 < (double)var4) {
               throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{Double.valueOf(var7), Integer.valueOf(var4)});
            }

            if(var7 > (double)var5) {
               throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{Double.valueOf(var7), Integer.valueOf(var5)});
            }
         }

         return var7;
      }
   }

   public static Item getItemByText(ICommandSender var0, String var1) {
      Item var2 = (Item)Item.itemRegistry.getObject(var1);
      if(var2 == null) {
         try {
            Item var3 = Item.getItemById(Integer.parseInt(var1));
            if(var3 != null) {
               ChatComponentTranslation var4 = new ChatComponentTranslation("commands.generic.deprecatedId", new Object[]{Item.itemRegistry.getNameForObject(var3)});
               var4.getChatStyle().setColor(EnumChatFormatting.GRAY);
               var0.addChatMessage(var4);
            }

            var2 = var3;
         } catch (NumberFormatException var5) {
            ;
         }
      }

      if(var2 == null) {
         throw new NumberInvalidException("commands.give.notFound", new Object[]{var1});
      } else {
         return var2;
      }
   }

   public static Block getBlockByText(ICommandSender var0, String var1) {
      if(Block.blockRegistry.containsKey(var1)) {
         return (Block)Block.blockRegistry.getObject(var1);
      } else {
         try {
            int var2 = Integer.parseInt(var1);
            if(Block.blockRegistry.containsId(var2)) {
               Block var3 = Block.getBlockById(var2);
               ChatComponentTranslation var4 = new ChatComponentTranslation("commands.generic.deprecatedId", new Object[]{Block.blockRegistry.getNameForObject(var3)});
               var4.getChatStyle().setColor(EnumChatFormatting.GRAY);
               var0.addChatMessage(var4);
               return var3;
            }
         } catch (NumberFormatException var5) {
            ;
         }

         throw new NumberInvalidException("commands.give.notFound", new Object[]{var1});
      }
   }

   public static String joinNiceString(Object[] var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 0; var2 < var0.length; ++var2) {
         String var3 = var0[var2].toString();
         if(var2 > 0) {
            if(var2 == var0.length - 1) {
               var1.append(" and ");
            } else {
               var1.append(", ");
            }
         }

         var1.append(var3);
      }

      return var1.toString();
   }

   public static IChatComponent joinNiceString(IChatComponent[] var0) {
      ChatComponentText var1 = new ChatComponentText("");

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if(var2 > 0) {
            if(var2 == var0.length - 1) {
               var1.appendText(" and ");
            } else if(var2 > 0) {
               var1.appendText(", ");
            }
         }

         var1.appendSibling(var0[var2]);
      }

      return var1;
   }

   public static String joinNiceStringFromCollection(Collection var0) {
      return joinNiceString(var0.toArray(new String[var0.size()]));
   }

   public static boolean doesStringStartWith(String var0, String var1) {
      return var1.regionMatches(true, 0, var0, 0, var0.length());
   }

   public static List getListOfStringsMatchingLastWord(String[] var0, String ... var1) {
      String var2 = var0[var0.length - 1];
      ArrayList var3 = new ArrayList();
      String[] var4 = var1;
      int var5 = var1.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String var7 = var4[var6];
         if(doesStringStartWith(var2, var7)) {
            var3.add(var7);
         }
      }

      return var3;
   }

   public static List getListOfStringsFromIterableMatchingLastWord(String[] var0, Iterable var1) {
      String var2 = var0[var0.length - 1];
      ArrayList var3 = new ArrayList();
      Iterator var4 = var1.iterator();

      while(var4.hasNext()) {
         String var5 = (String)var4.next();
         if(doesStringStartWith(var2, var5)) {
            var3.add(var5);
         }
      }

      return var3;
   }

   public boolean isUsernameIndex(String[] var1, int var2) {
      return false;
   }

   public static void func_152373_a(ICommandSender var0, ICommand var1, String var2, Object ... var3) {
      func_152374_a(var0, var1, 0, var2, var3);
   }

   public static void func_152374_a(ICommandSender var0, ICommand var1, int var2, String var3, Object ... var4) {
      if(theAdmin != null) {
         theAdmin.func_152372_a(var0, var1, var2, var3, var4);
      }

   }

   public static void setAdminCommander(IAdminCommand var0) {
      theAdmin = var0;
   }

   public int compareTo(ICommand var1) {
      return this.getCommandName().compareTo(var1.getCommandName());
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      return this.compareTo((ICommand)var1);
   }
}
