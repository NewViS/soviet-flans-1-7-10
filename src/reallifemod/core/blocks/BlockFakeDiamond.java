package de.ItsAMysterious.mods.reallifemod.core.blocks;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockFakeDiamond extends Block {

   public BlockFakeDiamond() {
      super(Material.rock);
      this.func_149663_c("diamond");
      this.func_149658_d("minecraft:diamond_ore");
      this.func_149647_a(RealLifeMod.Outdoor);
   }

   public void func_149664_b(World world, int x, int y, int z, int par4) {
      world.createExplosion((Entity)null, (double)x, (double)y, (double)z, 5.0F, true);
   }
}
