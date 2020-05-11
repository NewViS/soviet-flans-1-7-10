package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.entitys.particles.EntityDrop;
import net.minecraft.tileentity.TileEntity;

public class WashbasinTE extends TileEntity {

   public void func_145845_h() {
      if(this.field_145850_b != null && this.field_145850_b.isRemote) {
         if(this.func_145832_p() == 3) {
            this.field_145850_b.spawnEntityInWorld(new EntityDrop(this.field_145850_b, (double)this.field_145851_c + 0.7D, (double)this.field_145848_d + 1.125D, (double)this.field_145849_e + 0.525D, 0.0D, -0.025D, 0.0D, 0.25D));
         }

         if(this.func_145832_p() == 2) {
            this.field_145850_b.spawnEntityInWorld(new EntityDrop(this.field_145850_b, (double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 1.125D, (double)this.field_145849_e + 0.3D, 0.0D, -0.025D, 0.0D, 0.25D));
         }

         if(this.func_145832_p() == 1) {
            this.field_145850_b.spawnEntityInWorld(new EntityDrop(this.field_145850_b, (double)this.field_145851_c + 0.3D, (double)this.field_145848_d + 1.125D, (double)this.field_145849_e + 0.5D, 0.0D, -0.025D, 0.0D, 0.25D));
         }

         if(this.func_145832_p() == 0) {
            this.field_145850_b.spawnEntityInWorld(new EntityDrop(this.field_145850_b, (double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 1.125D, (double)this.field_145849_e + 0.7D, 0.0D, -0.025D, 0.0D, 0.25D));
         }
      }

      super.updateEntity();
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
