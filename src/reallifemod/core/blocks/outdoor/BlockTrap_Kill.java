package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockTrap_Kill extends Block {

   public BlockTrap_Kill(Material p_i45394_1_) {
      super(p_i45394_1_);
      this.func_149658_d("minecraft:iron_block");
      this.func_149663_c("deadfalltrap");
   }

   public void func_149724_b(World world, int x, int y, int z, Entity entity) {
      if(entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
         EntityLivingBase entityLB = (EntityLivingBase)entity;
         entityLB.addPotionEffect(new PotionEffect(Potion.harm.getId(), 1000));
      }

   }

   public int func_149660_a(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
      this.field_149761_L = world.getBlock(x, y, z + 1).getBlockTextureFromSide(1);
      return p_149660_9_;
   }
}
