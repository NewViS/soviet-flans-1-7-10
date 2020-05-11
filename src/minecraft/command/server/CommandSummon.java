package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CommandSummon extends CommandBase {

   public String getCommandName() {
      return "summon";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender var1) {
      return "commands.summon.usage";
   }

   public void processCommand(ICommandSender var1, String[] var2) {
      if(var2.length < 1) {
         throw new WrongUsageException("commands.summon.usage", new Object[0]);
      } else {
         String var3 = var2[0];
         double var4 = (double)var1.getPlayerCoordinates().posX + 0.5D;
         double var6 = (double)var1.getPlayerCoordinates().posY;
         double var8 = (double)var1.getPlayerCoordinates().posZ + 0.5D;
         if(var2.length >= 4) {
            var4 = func_110666_a(var1, var4, var2[1]);
            var6 = func_110666_a(var1, var6, var2[2]);
            var8 = func_110666_a(var1, var8, var2[3]);
         }

         World var10 = var1.getEntityWorld();
         if(!var10.blockExists((int)var4, (int)var6, (int)var8)) {
            func_152373_a(var1, this, "commands.summon.outOfWorld", new Object[0]);
         } else {
            NBTTagCompound var11 = new NBTTagCompound();
            boolean var12 = false;
            if(var2.length >= 5) {
               IChatComponent var13 = func_147178_a(var1, var2, 4);

               try {
                  NBTBase var14 = JsonToNBT.func_150315_a(var13.getUnformattedText());
                  if(!(var14 instanceof NBTTagCompound)) {
                     func_152373_a(var1, this, "commands.summon.tagError", new Object[]{"Not a valid tag"});
                     return;
                  }

                  var11 = (NBTTagCompound)var14;
                  var12 = true;
               } catch (NBTException var17) {
                  func_152373_a(var1, this, "commands.summon.tagError", new Object[]{var17.getMessage()});
                  return;
               }
            }

            var11.setString("id", var3);
            Entity var18 = EntityList.createEntityFromNBT(var11, var10);
            if(var18 == null) {
               func_152373_a(var1, this, "commands.summon.failed", new Object[0]);
            } else {
               var18.setLocationAndAngles(var4, var6, var8, var18.rotationYaw, var18.rotationPitch);
               if(!var12 && var18 instanceof EntityLiving) {
                  ((EntityLiving)var18).onSpawnWithEgg((IEntityLivingData)null);
               }

               var10.spawnEntityInWorld(var18);
               Entity var19 = var18;

               for(NBTTagCompound var15 = var11; var19 != null && var15.hasKey("Riding", 10); var15 = var15.getCompoundTag("Riding")) {
                  Entity var16 = EntityList.createEntityFromNBT(var15.getCompoundTag("Riding"), var10);
                  if(var16 != null) {
                     var16.setLocationAndAngles(var4, var6, var8, var16.rotationYaw, var16.rotationPitch);
                     var10.spawnEntityInWorld(var16);
                     var19.mountEntity(var16);
                  }

                  var19 = var16;
               }

               func_152373_a(var1, this, "commands.summon.success", new Object[0]);
            }
         }
      }
   }

   public List addTabCompletionOptions(ICommandSender var1, String[] var2) {
      return var2.length == 1?getListOfStringsMatchingLastWord(var2, this.func_147182_d()):null;
   }

   protected String[] func_147182_d() {
      return (String[])EntityList.func_151515_b().toArray(new String[0]);
   }
}
