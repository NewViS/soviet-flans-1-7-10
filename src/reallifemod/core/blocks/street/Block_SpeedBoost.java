package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Block_SpeedBoost extends Block {

   public Block_SpeedBoost(Material p_i45394_1_) {
      super(p_i45394_1_);
      this.func_149663_c("Speedboost-Block");
      this.func_149658_d("reallifemod:speedbooster");
   }

   public void func_149746_a(World world, int x, int y, int z, Entity entity, float par1) {
      entity.setLocationAndAngles((double)x + 0.5D, (double)(y + 10), (double)z + 0.5D, entity.getRotationYawHead(), entity.rotationPitch);
   }
}
