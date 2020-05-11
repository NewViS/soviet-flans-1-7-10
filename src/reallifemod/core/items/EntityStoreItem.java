package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityStoreItem extends Item {

   public Entity entity;


   public EntityStoreItem() {
      this.func_77655_b("EntityStoreItem");
      this.func_111206_d("reallifemod:itemBaby");
   }

   public EntityStoreItem(Entity entity) {
      this.entity = entity;
   }

   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
      if(this.entity != null) {
         float f = 1.0F;
         float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
         float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
         double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)f;
         double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)f + 1.62D - (double)player.field_70129_M;
         double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)f;
         Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
         float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
         float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
         float f5 = -MathHelper.cos(-f1 * 0.017453292F);
         float f6 = MathHelper.sin(-f1 * 0.017453292F);
         float f7 = f4 * f5;
         float f8 = f3 * f5;
         double d3 = 5.0D;
         Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
         MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, true);
         if(movingobjectposition == null) {
            return stack;
         } else {
            Vec3 vec32 = player.func_70676_i(f);
            boolean flag = false;
            float f9 = 1.0F;
            List list = world.getEntitiesWithinAABBExcludingEntity(player, player.field_70121_D.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand((double)f9, (double)f9, (double)f9));

            int i;
            for(i = 0; i < list.size(); ++i) {
               Entity j = (Entity)list.get(i);
               if(j.canBeCollidedWith()) {
                  float k = j.getCollisionBorderSize();
                  AxisAlignedBB b = j.boundingBox.expand((double)k, (double)k, (double)k);
                  if(b.isVecInside(vec3)) {
                     flag = true;
                  }
               }
            }

            if(flag) {
               return stack;
            } else {
               if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
                  i = movingobjectposition.blockX;
                  int var32 = movingobjectposition.blockY;
                  int var33 = movingobjectposition.blockZ;
                  if(world.getBlock(i, var32, var33) == Blocks.snow_layer) {
                     --var32;
                  }

                  EntityBaby var34 = (EntityBaby)this.entity;
                  var34.func_70107_b((double)i + 0.5D, (double)((float)var32 + 1.0F), (double)var33 + 0.5D);
                  if(!world.getCollidingBoundingBoxes(var34, this.entity.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                     return stack;
                  }

                  if(!world.isRemote) {
                     world.spawnEntityInWorld(var34);
                  }

                  if(!player.capabilities.isCreativeMode) {
                     --stack.stackSize;
                  }
               }

               return stack;
            }
         }
      } else {
         return stack;
      }
   }
}
