package net.minecraft.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.init.Blocks;

public class RenderMinecartMobSpawner extends RenderMinecart {

   protected void func_147910_a(EntityMinecartMobSpawner var1, float var2, Block var3, int var4) {
      super.func_147910_a(var1, var2, var3, var4);
      if(var3 == Blocks.mob_spawner) {
         TileEntityMobSpawnerRenderer.func_147517_a(var1.func_98039_d(), var1.posX, var1.posY, var1.posZ, var2);
      }

   }
}
