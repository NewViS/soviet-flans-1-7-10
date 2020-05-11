package de.ItsAMysterious.mods.reallifemod.core.streets.entitys;

import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTruck;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTrailer extends Entity {

   protected EntityTruck parent;


   public EntityTrailer(World world) {
      super(world);
   }

   public EntityTrailer(World world, double x, double y, double z) {
      super(world);
      this.func_70107_b(x, y, z);
      this.field_70158_ak = true;
   }

   public EntityTrailer(World world, EntityTruck entityTruck, double x, double y, double z) {
      super(world);
   }

   protected void func_70088_a() {}

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   public void func_70071_h_() {}
}
