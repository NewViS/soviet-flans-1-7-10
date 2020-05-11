package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class RobotPlacer extends ExtendedItem {

   public static Minecraft mc;


   public RobotPlacer() {
      this.func_77655_b("SpawnIntellybot");
      this.func_111206_d("reallifemod:robotIcon");
      this.setPrice(2499.99D);
   }

   public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
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
         return itemStack;
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
               AxisAlignedBB Trailer = j.boundingBox.expand((double)k, (double)k, (double)k);
               if(Trailer.isVecInside(vec3)) {
                  flag = true;
               }
            }
         }

         if(flag) {
            return itemStack;
         } else {
            if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
               i = movingobjectposition.blockX;
               int var32 = movingobjectposition.blockY;
               int var33 = movingobjectposition.blockZ;
               if(world.getBlock(i, var32, var33) == Blocks.snow_layer) {
                  --var32;
               }

               EntityRobot var34 = new EntityRobot(world, (double)((float)i + 0.5F), (double)((float)var32 + 1.0F), (double)((float)var33 + 0.5F));
               if(!world.getCollidingBoundingBoxes(var34, var34.field_70121_D.expand(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                  return itemStack;
               }

               if(!world.isRemote) {
                  world.spawnEntityInWorld(var34);
               }

               if(!player.capabilities.isCreativeMode) {
                  --itemStack.stackSize;
               }
            }

            return itemStack;
         }
      }
   }

   public double price() {
      return 50.0D;
   }

   public int percentage() {
      return 0;
   }
}
