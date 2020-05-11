package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.api.entity.EntitySeat;
import de.ItsAMysterious.mods.reallifemod.core.entities.cars.EntityVehicle;
import de.ItsAMysterious.mods.reallifemod.core.tiles.ChairTE;
import java.util.List;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockChair extends BlockContainer implements ITileEntityProvider {

   private static blockChair theBlock;
   private boolean inUse = false;


   public blockChair() {
      super(Material.wood);
      this.func_149663_c("chair");
      this.func_149658_d("reallifemod:iconChair");
   }

   public TileEntity func_149915_a(World world, int id) {
      return new ChairTE();
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public int func_149645_b() {
      return -1;
   }

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      if(entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if(player != null && world != null) {
            int le = MathHelper.floor_double((double)(player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
            world.setBlockMetadataWithNotify(x, y, z, le, 2);
         }

         world.markBlockForUpdate(x, y, z);
      }

   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      ChairTE chair = (ChairTE)world.getTileEntity(x, y, z);
      if(chair != null && !world.isRemote) {
         this.sitDown(player, world, x, y, z, chair);
      }

      world.markBlockForUpdate(x, y, z);
      return true;
   }

   public void sitDown(EntityPlayer player, World world, int i, int j, int k, ChairTE tile) {
      EntitySeat seatEntity = new EntitySeat(k, world, (EntityVehicle)null, (double)i, (double)((float)j + 1.75F), (double)k);
      world.spawnEntityInWorld(seatEntity);
      player.mountEntity(seatEntity);
   }

   public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB bounds, List list, Entity entity) {
      bounds.setBounds(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
      super.func_149743_a(world, x, y, z, bounds, list, entity);
      bounds.setBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
      super.func_149743_a(world, x, y, z, bounds, list, entity);
      bounds.setBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 0.0625D);
      super.func_149743_a(world, x, y, z, bounds, list, entity);
      bounds.setBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0625D, 1.0D);
      super.func_149743_a(world, x, y, z, bounds, list, entity);
      bounds.setBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
   }
}
