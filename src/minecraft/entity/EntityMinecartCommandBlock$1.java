package net.minecraft.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

class EntityMinecartCommandBlock$1 extends CommandBlockLogic {

   // $FF: synthetic field
   final EntityMinecartCommandBlock field_145768_a;


   EntityMinecartCommandBlock$1(EntityMinecartCommandBlock var1) {
      this.field_145768_a = var1;
   }

   public void func_145756_e() {
      this.field_145768_a.getDataWatcher().updateObject(23, this.func_145753_i());
      this.field_145768_a.getDataWatcher().updateObject(24, IChatComponent$Serializer.func_150696_a(this.func_145749_h()));
   }

   public int func_145751_f() {
      return 1;
   }

   public void func_145757_a(ByteBuf var1) {
      var1.writeInt(this.field_145768_a.getEntityId());
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return new ChunkCoordinates(MathHelper.floor_double(this.field_145768_a.posX), MathHelper.floor_double(this.field_145768_a.posY + 0.5D), MathHelper.floor_double(this.field_145768_a.posZ));
   }

   public World getEntityWorld() {
      return this.field_145768_a.worldObj;
   }
}
