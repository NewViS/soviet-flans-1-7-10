package net.minecraft.entity.item;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMinecartEmpty extends EntityMinecart {

   public EntityMinecartEmpty(World var1) {
      super(var1);
   }

   public EntityMinecartEmpty(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   public boolean interactFirst(EntityPlayer var1) {
      if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityPlayer && super.riddenByEntity != var1) {
         return true;
      } else if(super.riddenByEntity != null && super.riddenByEntity != var1) {
         return false;
      } else {
         if(!super.worldObj.isRemote) {
            var1.mountEntity(this);
         }

         return true;
      }
   }

   public int getMinecartType() {
      return 0;
   }
}
