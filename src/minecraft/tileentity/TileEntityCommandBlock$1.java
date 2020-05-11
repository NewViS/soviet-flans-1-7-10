package net.minecraft.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

class TileEntityCommandBlock$1 extends CommandBlockLogic {

   // $FF: synthetic field
   final TileEntityCommandBlock field_145767_a;


   TileEntityCommandBlock$1(TileEntityCommandBlock var1) {
      this.field_145767_a = var1;
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return new ChunkCoordinates(this.field_145767_a.xCoord, this.field_145767_a.yCoord, this.field_145767_a.zCoord);
   }

   public World getEntityWorld() {
      return this.field_145767_a.getWorldObj();
   }

   public void func_145752_a(String var1) {
      super.func_145752_a(var1);
      this.field_145767_a.markDirty();
   }

   public void func_145756_e() {
      this.field_145767_a.getWorldObj().markBlockForUpdate(this.field_145767_a.xCoord, this.field_145767_a.yCoord, this.field_145767_a.zCoord);
   }

   public int func_145751_f() {
      return 0;
   }

   public void func_145757_a(ByteBuf var1) {
      var1.writeInt(this.field_145767_a.xCoord);
      var1.writeInt(this.field_145767_a.yCoord);
      var1.writeInt(this.field_145767_a.zCoord);
   }
}
