package de.ItsAMysterious.mods.reallifemod.core.driveables;

import de.ItsAMysterious.mods.reallifemod.core.driveables.DriveableData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDriveable extends Entity {

   public DriveableData data;


   public EntityDriveable(World p_i1582_1_) {
      super(p_i1582_1_);
   }

   protected void func_70088_a() {}

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   protected void checkInput() {}
}
