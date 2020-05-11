package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.world.World;

public class EntityBlockDustFX extends EntityDiggingFX {

   public EntityBlockDustFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12, Block var14, int var15) {
      super(var1, var2, var4, var6, var8, var10, var12, var14, var15);
      super.motionX = var8;
      super.motionY = var10;
      super.motionZ = var12;
   }
}
