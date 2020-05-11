package net.minecraft.entity;

import net.minecraft.block.Block;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.EntityMinecartCommandBlock$1;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.world.World;

public class EntityMinecartCommandBlock extends EntityMinecart {

   private final CommandBlockLogic field_145824_a = new EntityMinecartCommandBlock$1(this);
   private int field_145823_b = 0;


   public EntityMinecartCommandBlock(World var1) {
      super(var1);
   }

   public EntityMinecartCommandBlock(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataWatcher().addObject(23, "");
      this.getDataWatcher().addObject(24, "");
   }

   protected void readEntityFromNBT(NBTTagCompound var1) {
      super.readEntityFromNBT(var1);
      this.field_145824_a.func_145759_b(var1);
      this.getDataWatcher().updateObject(23, this.func_145822_e().func_145753_i());
      this.getDataWatcher().updateObject(24, IChatComponent$Serializer.func_150696_a(this.func_145822_e().func_145749_h()));
   }

   protected void writeEntityToNBT(NBTTagCompound var1) {
      super.writeEntityToNBT(var1);
      this.field_145824_a.func_145758_a(var1);
   }

   public int getMinecartType() {
      return 6;
   }

   public Block func_145817_o() {
      return Blocks.command_block;
   }

   public CommandBlockLogic func_145822_e() {
      return this.field_145824_a;
   }

   public void onActivatorRailPass(int var1, int var2, int var3, boolean var4) {
      if(var4 && super.ticksExisted - this.field_145823_b >= 4) {
         this.func_145822_e().func_145755_a(super.worldObj);
         this.field_145823_b = super.ticksExisted;
      }

   }

   public boolean interactFirst(EntityPlayer var1) {
      if(super.worldObj.isRemote) {
         var1.func_146095_a(this.func_145822_e());
      }

      return super.interactFirst(var1);
   }

   public void func_145781_i(int var1) {
      super.func_145781_i(var1);
      if(var1 == 24) {
         try {
            this.field_145824_a.func_145750_b(IChatComponent$Serializer.func_150699_a(this.getDataWatcher().getWatchableObjectString(24)));
         } catch (Throwable var3) {
            ;
         }
      } else if(var1 == 23) {
         this.field_145824_a.func_145752_a(this.getDataWatcher().getWatchableObjectString(23));
      }

   }
}
