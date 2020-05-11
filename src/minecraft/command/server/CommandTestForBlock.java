package net.minecraft.command.server;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CommandTestForBlock extends CommandBase {

   public String getCommandName() {
      return "testforblock";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.testforblock.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length >= 4) {
         int var3 = var1.getPlayerCoordinates().posX;
         int var4 = var1.getPlayerCoordinates().posY;
         int var5 = var1.getPlayerCoordinates().posZ;
         var3 = MathHelper.floor_double(func_110666_a(var1, (double)var3, var2[0]));
         var4 = MathHelper.floor_double(func_110666_a(var1, (double)var4, var2[1]));
         var5 = MathHelper.floor_double(func_110666_a(var1, (double)var5, var2[2]));
         Block var6 = Block.getBlockFromName(var2[3]);
         if(var6 == null) {
            throw new NumberInvalidException("commands.setblock.notFound", new Object[]{var2[3]});
         } else {
            int var7 = -1;
            if(var2.length >= 5) {
               var7 = parseIntBounded(var1, var2[4], -1, 15);
            }

            World var8 = var1.getEntityWorld();
            if(!var8.blockExists(var3, var4, var5)) {
               throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
            } else {
               NBTTagCompound var9 = new NBTTagCompound();
               boolean var10 = false;
               if(var2.length >= 6 && var6.hasTileEntity()) {
                  String var11 = func_147178_a(var1, var2, 5).getUnformattedText();

                  try {
                     NBTBase var12 = JsonToNBT.func_150315_a(var11);
                     if(!(var12 instanceof NBTTagCompound)) {
                        throw new CommandException("commands.setblock.tagError", new Object[]{"Not a valid tag"});
                     }

                     var9 = (NBTTagCompound)var12;
                     var10 = true;
                  } catch (NBTException var14) {
                     throw new CommandException("commands.setblock.tagError", new Object[]{var14.getMessage()});
                  }
               }

               Block var15 = var8.getBlock(var3, var4, var5);
               if(var15 != var6) {
                  throw new CommandException("commands.testforblock.failed.tile", new Object[]{Integer.valueOf(var3), Integer.valueOf(var4), Integer.valueOf(var5), var15.getLocalizedName(), var6.getLocalizedName()});
               } else {
                  if(var7 > -1) {
                     int var16 = var8.getBlockMetadata(var3, var4, var5);
                     if(var16 != var7) {
                        throw new CommandException("commands.testforblock.failed.data", new Object[]{Integer.valueOf(var3), Integer.valueOf(var4), Integer.valueOf(var5), Integer.valueOf(var16), Integer.valueOf(var7)});
                     }
                  }

                  if(var10) {
                     TileEntity var17 = var8.getTileEntity(var3, var4, var5);
                     if(var17 == null) {
                        throw new CommandException("commands.testforblock.failed.tileEntity", new Object[]{Integer.valueOf(var3), Integer.valueOf(var4), Integer.valueOf(var5)});
                     }

                     NBTTagCompound var13 = new NBTTagCompound();
                     var17.writeToNBT(var13);
                     if(!this.func_147181_a(var9, var13)) {
                        throw new CommandException("commands.testforblock.failed.nbt", new Object[]{Integer.valueOf(var3), Integer.valueOf(var4), Integer.valueOf(var5)});
                     }
                  }

                  var1.addChatMessage(new ChatComponentTranslation("commands.testforblock.success", new Object[]{Integer.valueOf(var3), Integer.valueOf(var4), Integer.valueOf(var5)}));
               }
            }
         }
      } else {
         throw new WrongUsageException("commands.testforblock.usage", new Object[0]);
      }
   }

   public boolean func_147181_a(NBTBase var1, NBTBase var2) {
      if(var1 == var2) {
         return true;
      } else if(var1 == null) {
         return true;
      } else if(var2 == null) {
         return false;
      } else if(!var1.getClass().equals(var2.getClass())) {
         return false;
      } else if(var1 instanceof NBTTagCompound) {
         NBTTagCompound var3 = (NBTTagCompound)var1;
         NBTTagCompound var4 = (NBTTagCompound)var2;
         Iterator var5 = var3.func_150296_c().iterator();

         String var6;
         NBTBase var7;
         do {
            if(!var5.hasNext()) {
               return true;
            }

            var6 = (String)var5.next();
            var7 = var3.getTag(var6);
         } while(this.func_147181_a(var7, var4.getTag(var6)));

         return false;
      } else {
         return var1.equals(var2);
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 4?getListOfStringsFromIterableMatchingLastWord(var2, Block.blockRegistry.getKeys()):null;
   }
}
