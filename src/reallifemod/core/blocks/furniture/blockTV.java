package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityTV;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class blockTV extends BlockContainer implements ITileEntityProvider {

   public blockTV() {
      super(Materials.plastic);
      this.func_149663_c("tv");
      this.func_149658_d("reallifemod:iconTV");
   }

   public void func_149719_a(IBlockAccess world, int x, int y, int z) {
      int metadata = world.getBlockMetadata(x, y, z);
      float offset = (float)(this.isBlockAirOrSmall(world, x, y - 1, z)?0.25D:0.0D);
      switch(metadata) {
      case 0:
         this.func_149676_a(-0.5F, 0.0F, 0.55F + offset, 1.5F, 1.0F, 0.75F + offset);
         break;
      case 1:
         this.func_149676_a(0.25F - offset, 0.0F, -0.5F, 0.5F - offset, 1.15F, 1.5F);
         break;
      case 2:
         this.func_149676_a(-0.5F, 0.0F, 0.25F - offset, 1.5F, 1.0F, 0.5F - offset);
         break;
      case 3:
         this.func_149676_a(0.55F + offset, 0.0F, -0.5F, 0.75F + offset, 1.0F, 1.5F);
      }

   }

   public boolean isBlockAirOrSmall(IBlockAccess world, int x, int y, int z) {
      return world.getBlock(x, y, z) == Blocks.air || world.getBlock(x, y, z).getBlockBoundsMaxY() < 1.0D;
   }

   public void func_149664_b(World world, int par1, int par2, int par3, int par4) {
      this.func_149642_a(world, par1, par2, par3, new ItemStack(this));
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new TileEntityTV();
   }

   public int func_149645_b() {
      return -1;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
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
}
