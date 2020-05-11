package net.minecraft.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSpreadPlayers$Position;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CommandSpreadPlayers extends CommandBase {

   public String getCommandName() {
      return "spreadplayers";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.spreadplayers.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length < 6) {
         throw new WrongUsageException("commands.spreadplayers.usage", new Object[0]);
      } else {
         byte var3 = 0;
         int var16 = var3 + 1;
         double var4 = func_110666_a(var1, Double.NaN, var2[var3]);
         double var6 = func_110666_a(var1, Double.NaN, var2[var16++]);
         double var8 = parseDoubleWithMin(var1, var2[var16++], 0.0D);
         double var10 = parseDoubleWithMin(var1, var2[var16++], var8 + 1.0D);
         boolean var12 = parseBoolean(var1, var2[var16++]);
         ArrayList var13 = Lists.newArrayList();

         while(true) {
            while(var16 < var2.length) {
               String var14 = var2[var16++];
               if(PlayerSelector.hasArguments(var14)) {
                  EntityPlayerMP[] var17 = PlayerSelector.matchPlayers(var1, var14);
                  if(var17 == null || var17.length == 0) {
                     throw new PlayerNotFoundException();
                  }

                  Collections.addAll(var13, var17);
               } else {
                  EntityPlayerMP var15 = MinecraftServer.getServer().getConfigurationManager().func_152612_a(var14);
                  if(var15 == null) {
                     throw new PlayerNotFoundException();
                  }

                  var13.add(var15);
               }
            }

            if(var13.isEmpty()) {
               throw new PlayerNotFoundException();
            }

            var1.addChatMessage(new ChatComponentTranslation("commands.spreadplayers.spreading." + (var12?"teams":"players"), new Object[]{Integer.valueOf(var13.size()), Double.valueOf(var10), Double.valueOf(var4), Double.valueOf(var6), Double.valueOf(var8)}));
            this.func_110669_a(var1, var13, new CommandSpreadPlayers$Position(var4, var6), var8, var10, ((EntityLivingBase)var13.get(0)).worldObj, var12);
            return;
         }
      }
   }

   private void func_110669_a(ICommandSender var1, List var2, CommandSpreadPlayers$Position var3, double var4, double var6, World var8, boolean var9) {
      Random var10 = new Random();
      double var11 = var3.field_111101_a - var6;
      double var13 = var3.field_111100_b - var6;
      double var15 = var3.field_111101_a + var6;
      double var17 = var3.field_111100_b + var6;
      CommandSpreadPlayers$Position[] var19 = this.func_110670_a(var10, var9?this.func_110667_a(var2):var2.size(), var11, var13, var15, var17);
      int var20 = this.func_110668_a(var3, var4, var8, var10, var11, var13, var15, var17, var19, var9);
      double var21 = this.func_110671_a(var2, var8, var19, var9);
      func_152373_a(var1, this, "commands.spreadplayers.success." + (var9?"teams":"players"), new Object[]{Integer.valueOf(var19.length), Double.valueOf(var3.field_111101_a), Double.valueOf(var3.field_111100_b)});
      if(var19.length > 1) {
         var1.addChatMessage(new ChatComponentTranslation("commands.spreadplayers.info." + (var9?"teams":"players"), new Object[]{String.format("%.2f", new Object[]{Double.valueOf(var21)}), Integer.valueOf(var20)}));
      }

   }

   private int func_110667_a(List var1) {
      HashSet var2 = Sets.newHashSet();
      Iterator var3 = var1.iterator();

      while(var3.hasNext()) {
         EntityLivingBase var4 = (EntityLivingBase)var3.next();
         if(var4 instanceof EntityPlayer) {
            var2.add(var4.getTeam());
         } else {
            var2.add((Object)null);
         }
      }

      return var2.size();
   }

   private int func_110668_a(CommandSpreadPlayers$Position var1, double var2, World var4, Random var5, double var6, double var8, double var10, double var12, CommandSpreadPlayers$Position[] var14, boolean var15) {
      boolean var16 = true;
      double var18 = 3.4028234663852886E38D;

      int var17;
      for(var17 = 0; var17 < 10000 && var16; ++var17) {
         var16 = false;
         var18 = 3.4028234663852886E38D;

         int var22;
         CommandSpreadPlayers$Position var23;
         for(int var20 = 0; var20 < var14.length; ++var20) {
            CommandSpreadPlayers$Position var21 = var14[var20];
            var22 = 0;
            var23 = new CommandSpreadPlayers$Position();

            for(int var24 = 0; var24 < var14.length; ++var24) {
               if(var20 != var24) {
                  CommandSpreadPlayers$Position var25 = var14[var24];
                  double var26 = var21.func_111099_a(var25);
                  var18 = Math.min(var26, var18);
                  if(var26 < var2) {
                     ++var22;
                     var23.field_111101_a += var25.field_111101_a - var21.field_111101_a;
                     var23.field_111100_b += var25.field_111100_b - var21.field_111100_b;
                  }
               }
            }

            if(var22 > 0) {
               var23.field_111101_a /= (double)var22;
               var23.field_111100_b /= (double)var22;
               double var30 = (double)var23.func_111096_b();
               if(var30 > 0.0D) {
                  var23.func_111095_a();
                  var21.func_111094_b(var23);
               } else {
                  var21.func_111097_a(var5, var6, var8, var10, var12);
               }

               var16 = true;
            }

            if(var21.func_111093_a(var6, var8, var10, var12)) {
               var16 = true;
            }
         }

         if(!var16) {
            CommandSpreadPlayers$Position[] var28 = var14;
            int var29 = var14.length;

            for(var22 = 0; var22 < var29; ++var22) {
               var23 = var28[var22];
               if(!var23.func_111098_b(var4)) {
                  var23.func_111097_a(var5, var6, var8, var10, var12);
                  var16 = true;
               }
            }
         }
      }

      if(var17 >= 10000) {
         throw new CommandException("commands.spreadplayers.failure." + (var15?"teams":"players"), new Object[]{Integer.valueOf(var14.length), Double.valueOf(var1.field_111101_a), Double.valueOf(var1.field_111100_b), String.format("%.2f", new Object[]{Double.valueOf(var18)})});
      } else {
         return var17;
      }
   }

   private double func_110671_a(List var1, World var2, CommandSpreadPlayers$Position[] var3, boolean var4) {
      double var5 = 0.0D;
      int var7 = 0;
      HashMap var8 = Maps.newHashMap();

      for(int var9 = 0; var9 < var1.size(); ++var9) {
         EntityLivingBase var10 = (EntityLivingBase)var1.get(var9);
         CommandSpreadPlayers$Position var11;
         if(var4) {
            Team var12 = var10 instanceof EntityPlayer?var10.getTeam():null;
            if(!var8.containsKey(var12)) {
               var8.put(var12, var3[var7++]);
            }

            var11 = (CommandSpreadPlayers$Position)var8.get(var12);
         } else {
            var11 = var3[var7++];
         }

         var10.setPositionAndUpdate((double)((float)MathHelper.floor_double(var11.field_111101_a) + 0.5F), (double)var11.func_111092_a(var2), (double)MathHelper.floor_double(var11.field_111100_b) + 0.5D);
         double var17 = Double.MAX_VALUE;

         for(int var14 = 0; var14 < var3.length; ++var14) {
            if(var11 != var3[var14]) {
               double var15 = var11.func_111099_a(var3[var14]);
               var17 = Math.min(var15, var17);
            }
         }

         var5 += var17;
      }

      var5 /= (double)var1.size();
      return var5;
   }

   private CommandSpreadPlayers$Position[] func_110670_a(Random var1, int var2, double var3, double var5, double var7, double var9) {
      CommandSpreadPlayers$Position[] var11 = new CommandSpreadPlayers$Position[var2];

      for(int var12 = 0; var12 < var11.length; ++var12) {
         CommandSpreadPlayers$Position var13 = new CommandSpreadPlayers$Position();
         var13.func_111097_a(var1, var3, var5, var7, var9);
         var11[var12] = var13;
      }

      return var11;
   }
}
