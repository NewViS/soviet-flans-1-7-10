package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.api.entity.EntitySeat;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityVehicle;
import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class ChairTE extends TileEntity {

   public boolean hasSitter = false;


   public void func_145839_a(NBTTagCompound tag) {}

   public void func_145841_b(NBTTagCompound tag) {}

   public boolean canUpdate() {
      return true;
   }

   public void onUpdate() {
      if(!this.hasSitter) {
         this.scanForEntities();
      }

   }

   public void scanForEntities() {
      AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((double)this.field_145851_c - 2.0D, (double)this.field_145848_d - 1.0D, (double)this.field_145849_e - 2.0D, (double)this.field_145851_c + 3.0D, (double)this.field_145848_d + 2.0D, (double)this.field_145849_e + 3.0D);
      List checkedEntities = this.field_145850_b.getEntitiesWithinAABB(EntityCreature.class, bb);

      for(int x = 0; x < checkedEntities.size(); ++x) {
         EntityCreature guy = (EntityCreature)checkedEntities.get(x);
         if(!this.hasSitter) {
            this.sitdown(guy);
         }
      }

   }

   public void sitdown(EntityLiving guy) {
      EntitySeat seatEntity = new EntitySeat(0, this.field_145850_b, (EntityVehicle)null, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e);
      this.field_145850_b.spawnEntityInWorld(seatEntity);
      guy.func_70078_a(seatEntity);
      this.hasSitter = true;
   }

   public int func_145832_p() {
      if(this.field_145850_b != null) {
         if(this.field_145847_g == -1) {
            this.field_145847_g = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }

         return this.field_145847_g;
      } else {
         return 1;
      }
   }
}
