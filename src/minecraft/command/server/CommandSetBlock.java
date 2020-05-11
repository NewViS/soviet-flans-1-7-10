package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CommandSetBlock extends CommandBase {

   public String getCommandName() {
      return "setblock";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.setblock.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length >= 4) {
         int var3 = var1.getPlayerCoordinates().posX;
         int var4 = var1.getPlayerCoordinates().posY;
         int var5 = var1.getPlayerCoordinates().posZ;
         var3 = MathHelper.floor_double(func_110666_a(var1, (double)var3, var2[0]));
         var4 = MathHelper.floor_double(func_110666_a(var1, (double)var4, var2[1]));
         var5 = MathHelper.floor_double(func_110666_a(var1, (double)var5, var2[2]));
         Block var6 = CommandBase.getBlockByText(var1, var2[3]);
         int var7 = 0;
         if(var2.length >= 5) {
            var7 = parseIntBounded(var1, var2[4], 0, 15);
         }

         World var8 = var1.getEntityWorld();
         if(!var8.blockExists(var3, var4, var5)) {
            throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
         } else {
            NBTTagCompound var9 = new NBTTagCompound();
            boolean var10 = false;
            if(var2.length >= 7 && var6.hasTileEntity()) {
               String var11 = func_147178_a(var1, var2, 6).getUnformattedText();

               try {
                  NBTBase var12 = JsonToNBT.func_150315_a(var11);
                  if(!(var12 instanceof NBTTagCompound)) {
                     throw new CommandException("commands.setblock.tagError", new Object[]{"Not a valid tag"});
                  }

                  var9 = (NBTTagCompound)var12;
                  var10 = true;
               } catch (NBTException var13) {
                  throw new CommandException("commands.setblock.tagError", new Object[]{var13.getMessage()});
               }
            }

            if(var2.length >= 6) {
               if(var2[5].equals("destroy")) {
                  var8.func_147480_a(var3, var4, var5, true);
               } else if(var2[5].equals("keep") && !var8.isAirBlock(var3, var4, var5)) {
                  throw new CommandException("commands.setblock.noChange", new Object[0]);
               }
            }

            if(!var8.setBlock(var3, var4, var5, var6, var7, 3)) {
               throw new CommandException("commands.setblock.noChange", new Object[0]);
            } else {
               if(var10) {
                  TileEntity var14 = var8.getTileEntity(var3, var4, var5);
                  if(var14 != null) {
                     var9.setInteger("x", var3);
                     var9.setInteger("y", var4);
                     var9.setInteger("z", var5);
                     var14.readFromNBT(var9);
                  }
               }

               func_152373_a(var1, this, "commands.setblock.success", new Object[0]);
            }
         }
      } else {
         throw new WrongUsageException("commands.setblock.usage", new Object[0]);
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 4?getListOfStringsFromIterableMatchingLastWord(var2, Block.blockRegistry.getKeys()):(var2.length == 6?getListOfStringsMatchingLastWord(var2, new String[]{"replace", "destroy", "keep"}):null);
   }
}
