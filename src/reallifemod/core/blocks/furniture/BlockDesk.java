package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.core.tiles.DeskTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDesk extends BlockContainer {

   public BlockDesk() {
      super(Material.iron);
      this.func_149663_c("desk");
      this.func_149658_d("reallifemod:desk");
   }

   public void func_149719_a(IBlockAccess world, int x, int y, int z) {
      super.func_149719_a(world, x, y, z);
      switch(world.getBlockMetadata(x, y, z)) {
      case 0:
         this.func_149676_a(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         break;
      case 1:
         this.func_149676_a(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F);
         break;
      case 2:
         this.func_149676_a(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F);
         break;
      case 3:
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F);
      }

   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
      TileEntity tileEntity = world.getTileEntity(x, y, z);
      if(tileEntity != null && tileEntity instanceof DeskTE) {
         DeskTE freezer = (DeskTE)tileEntity;
         return true;
      } else {
         return true;
      }
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new DeskTE();
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
