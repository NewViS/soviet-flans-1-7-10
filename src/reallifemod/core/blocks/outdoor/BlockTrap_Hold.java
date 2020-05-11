package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class BlockTrap_Hold extends Block {

   public BlockTrap_Hold(Material p_i45394_1_) {
      super(p_i45394_1_);
      this.func_149658_d("minecraft:grass_top");
      this.func_149663_c("snaretrap");
   }

   public void func_149724_b(World world, int x, int y, int z, Entity entity) {
      if(entity instanceof EntityLivingBase) {
         EntityLivingBase entityLB = (EntityLivingBase)entity;
         entityLB.canBePushed();
         entity.motionX = 0.0D;
         entity.motionY = 0.0D;
         entity.motionZ = 0.0D;
         entity.entityCollisionReduction = 1.0F;
      }

   }
}
