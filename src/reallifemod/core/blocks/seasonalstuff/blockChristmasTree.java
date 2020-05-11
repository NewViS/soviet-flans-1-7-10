package de.ItsAMysterious.mods.reallifemod.core.blocks.seasonalstuff;

import de.ItsAMysterious.mods.reallifemod.core.tiles.ChristmasTreeTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockChristmasTree extends BlockContainer implements ITileEntityProvider {

   public blockChristmasTree() {
      super(Material.leaves);
      this.func_149663_c("christmastree");
   }

   public TileEntity func_149915_a(World world, int id) {
      return new ChristmasTreeTE();
   }

   public boolean func_149721_r() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public int func_149645_b() {
      return -1;
   }

   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
      byte newFacing = 0;
      if(facing == 0) {
         newFacing = 2;
      }

      if(facing == 1) {
         newFacing = 5;
      }

      if(facing == 2) {
         newFacing = 3;
      }

      if(facing == 3) {
         newFacing = 4;
      }

      TileEntity te = world.getTileEntity(i, j, k);
      if(te != null && te instanceof ChristmasTreeTE) {
         ChristmasTreeTE tet = (ChristmasTreeTE)te;
         tet.setFacingDirection(newFacing);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      float light = 0.0F;
      ChristmasTreeTE tile = (ChristmasTreeTE)world.getTileEntity(x, y, z);
      if(tile instanceof ChristmasTreeTE) {
         if(tile.isActive) {
            tile.isActive = false;
            light = 0.0F;
         } else if(!tile.isActive) {
            tile.isActive = true;
            light = 0.7F;
         }

         tile.func_145831_w().getBlock(x, y, z).setLightLevel(light);
      }

      world.markBlockForUpdate(x, y, z);
      return true;
   }
}
