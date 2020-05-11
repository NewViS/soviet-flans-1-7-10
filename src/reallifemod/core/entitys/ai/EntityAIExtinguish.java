package de.ItsAMysterious.mods.reallifemod.core.entitys.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityAIExtinguish extends EntityAIBase {

   public int interactionradius;
   public Entity theEntity;
   public World theWorld;
   public Block currentBlock;


   public EntityAIExtinguish(Entity entity, int blockRadius) {
      this.theEntity = entity;
      this.theWorld = entity.worldObj;
      this.interactionradius = blockRadius;
   }

   public boolean func_75250_a() {
      return this.theWorld.getBlock((int)this.theEntity.posX + 1, (int)this.theEntity.posY, (int)this.theEntity.posZ) == Blocks.fire;
   }

   public boolean func_75253_b() {
      if(this.theWorld.getBlock((int)this.theEntity.posX, (int)this.theEntity.posY - 1, (int)this.theEntity.posZ) == Blocks.fire) {
         ;
      }

      return this.func_75250_a();
   }

   public boolean func_75252_g() {
      return false;
   }

   public void func_75249_e() {
      this.theWorld.setBlockToAir((int)this.theEntity.posX, (int)this.theEntity.posY, (int)this.theEntity.posZ);
   }

   public void func_75251_c() {}

   public void func_75246_d() {
      this.theWorld.spawnParticle("splash", this.theEntity.posX + 1.0D, this.theEntity.posY, this.theEntity.posZ, 0.1D, -10.1D, 0.1D);
   }
}
