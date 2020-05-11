package de.ItsAMysterious.mods.reallifemod.api.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityVehicle;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySeat extends Entity implements IEntityAdditionalSpawnData {

   public float offsetX;
   public float offsetY;
   public float offsetZ;
   public static EntityVehicle driveable;
   private int driveableID;
   private int seatID;


   public EntitySeat(World world) {
      super(world);
      this.func_70105_a(1.0F, 1.0F);
   }

   public EntitySeat(int id, World world, EntityVehicle jeep, double xCoord, double yCoord, double zCoord) {
      this(world);
      driveable = jeep;
      this.seatID = id;
      this.offsetX = (float)xCoord;
      this.offsetY = (float)yCoord;
      this.offsetZ = (float)zCoord;
   }

   public void setSeatPosition() {}

   public void func_70071_h_() {
      super.onUpdate();
   }

   protected void func_70088_a() {
      this.field_70180_af.addObject(17, new Integer(0));
      this.field_70180_af.addObject(18, new Integer(1));
      this.field_70180_af.addObject(19, new Float(0.0F));
   }

   public void updatePosition() {
      boolean hasParent = driveable != null;
      if(hasParent) {
         driveable.seats[this.seatID] = this;
         System.out.println("This seat has no parent!!");
      }
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {}

   protected void func_70014_b(NBTTagCompound p_70014_1_) {}

   public void func_70043_V() {
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
         this.field_70153_n.setPosition(this.field_70165_t, this.field_70163_u + 2.0D, this.field_70161_v);
      }

   }

   public boolean func_130002_c(EntityPlayer player) {
      super.interactFirst(player);
      player.mountEntity(this);
      return true;
   }

   public void writeSpawnData(ByteBuf buffer) {}

   public void readSpawnData(ByteBuf additionalData) {}
}
