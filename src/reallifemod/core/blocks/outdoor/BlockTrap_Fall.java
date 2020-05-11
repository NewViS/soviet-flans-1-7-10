package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockTrap_Fall extends Block {

   public BlockTrap_Fall(Material p_i45394_1_) {
      super(p_i45394_1_);
      this.func_149658_d("minecraft:grass_top");
      this.func_149663_c("trappingpit");
   }

   public void func_149724_b(World world, int x, int y, int z, Entity entity) {
      if(!world.isRemote) {
         entity.motionY = 0.0D;
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         entity.setPosition(entity.posX, (double)(y - 2), entity.posZ);
      }

   }
}
