package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityDirectional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;

public class TileEntitySitting extends TileEntityDirectional {

   private boolean sitting = false;
   private EntityPlayer EntitySitting = null;
   private double yOffset;


   public void sitdown(EntityPlayer entity, double yOfset) {
      this.yOffset = yOfset;
      this.EntitySitting = entity;
      this.setSitting(true);
   }

   public void func_145845_h() {
      super.func_145845_h();
      if(this.setSitting(this.EntitySitting != null)) {
         this.EntitySitting.func_70107_b((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + this.yOffset, (double)this.field_145849_e + 0.5D);
         this.EntitySitting.field_70159_w = 0.0D;
         this.EntitySitting.field_70179_y = 0.0D;
         this.EntitySitting.mountEntity((Entity)null);
      } else {
         if(this.EntitySitting != null) {
            this.dismountEntity();
         }

         this.func_70296_d();
      }

      if(Keyboard.isKeyDown(42) && this.EntitySitting != null) {
         this.dismountEntity();
      }

   }

   public void func_145839_a(NBTTagCompound nbttagcompound) {
      super.func_145839_a(nbttagcompound);
      this.setSitting(nbttagcompound.getBoolean("sitting"));
   }

   public void func_145841_b(NBTTagCompound nbttagcompound) {
      super.func_145841_b(nbttagcompound);
      nbttagcompound.setBoolean("sitting", this.isSitting());
   }

   public void dismountEntity() {
      this.EntitySitting.func_70107_b((double)this.field_145851_c, (double)(this.field_145848_d + 3), (double)this.field_145849_e);
      this.EntitySitting.field_70145_X = false;
      this.EntitySitting = null;
   }

   public boolean isSitting() {
      return this.sitting;
   }

   public boolean setSitting(boolean sitting) {
      this.sitting = sitting;
      return sitting;
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
