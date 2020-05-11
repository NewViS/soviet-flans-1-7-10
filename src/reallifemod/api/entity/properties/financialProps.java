package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class financialProps implements IExtendedEntityProperties {

   public static double Cash;
   public static EntityPlayer player;


   public financialProps(EntityPlayer player) {
      player = player;
   }

   public void saveNBTData(NBTTagCompound parCompound) {
      NBTTagCompound compound = new NBTTagCompound();
      parCompound.setTag("Financial", compound);
      compound.setDouble("Cash", Cash);
      player.writeEntityToNBT(parCompound);
   }

   public void loadNBTData(NBTTagCompound compound) {
      player.readEntityFromNBT(compound);
      NBTTagCompound comp = compound.getCompoundTag("Financial");
      Cash = comp.getDouble("Cash");
   }

   public void init(Entity entity, World world) {
      if(entity instanceof EntityPlayer) {
         player = (EntityPlayer)entity;
      }

   }
}
