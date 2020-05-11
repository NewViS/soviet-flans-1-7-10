package de.ItsAMysterious.mods.reallifemod.core.entitys;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFirFalling extends Entity {

   private int timelying;


   public EntityFirFalling(World world) {
      super(world);
      this.field_70158_ak = true;
   }

   public EntityFirFalling(World world, double x, double y, double z) {
      this(world);
      this.func_70107_b(x, y, z);
      this.func_70105_a(0.5F, 0.5F);
      this.func_70101_b(0.0F, 0.0F);
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
   }

   public void func_70071_h_() {
      super.onUpdate();
      if(this.field_70177_z > 90.0F) {
         ;
      }

      if(this.field_70170_p.getBlock((int)(this.field_70165_t + Math.sin((double)this.field_70177_z * 3.141592653589793D / 180.0D)), (int)(this.field_70163_u - 1.0D + Math.cos((double)this.field_70125_A * 3.141592653589793D / 180.0D) * 2.0D), (int)((double)((int)this.field_70161_v) + Math.cos((double)this.field_70177_z * 3.141592653589793D / 180.0D))) == Blocks.air) {
         this.field_70125_A = (float)((double)this.field_70125_A + 0.25D + Math.sqrt((double)(this.field_70125_A * this.field_70125_A) * 3.141592653589793D / 180.0D) / 2.0D);
         this.field_70163_u += Math.cos((double)this.field_70125_A * 3.141592653589793D / 180.0D) * 0.0035D;
      } else {
         ++this.timelying;
         if(this.timelying > 60) {
            if(!this.field_70170_p.isRemote) {
               this.func_70099_a(new ItemStack(Blocks.log, 12), 0.0F);
            }

            this.timelying = 0;
            this.func_70106_y();
         }
      }

   }

   protected void func_70037_a(NBTTagCompound compound) {}

   protected void func_70014_b(NBTTagCompound compound) {}
}
