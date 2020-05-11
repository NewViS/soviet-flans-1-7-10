package net.minecraft.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

class TileEntityMobSpawner$1 extends MobSpawnerBaseLogic {

   // $FF: synthetic field
   final TileEntityMobSpawner field_150825_a;


   TileEntityMobSpawner$1(TileEntityMobSpawner var1) {
      this.field_150825_a = var1;
   }

   public void func_98267_a(int var1) {
      this.field_150825_a.worldObj.addBlockEvent(this.field_150825_a.xCoord, this.field_150825_a.yCoord, this.field_150825_a.zCoord, Blocks.mob_spawner, var1, 0);
   }

   public World getSpawnerWorld() {
      return this.field_150825_a.worldObj;
   }

   public int getSpawnerX() {
      return this.field_150825_a.xCoord;
   }

   public int getSpawnerY() {
      return this.field_150825_a.yCoord;
   }

   public int getSpawnerZ() {
      return this.field_150825_a.zCoord;
   }

   public void setRandomEntity(MobSpawnerBaseLogic$WeightedRandomMinecart var1) {
      super.setRandomEntity(var1);
      if(this.getSpawnerWorld() != null) {
         this.getSpawnerWorld().markBlockForUpdate(this.field_150825_a.xCoord, this.field_150825_a.yCoord, this.field_150825_a.zCoord);
      }

   }
}
