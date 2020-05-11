package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import de.ItsAMysterious.mods.reallifemod.core.tiles.CrashbarrierTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class crashbarrierBl extends BlockContainer implements ITileEntityProvider {

   public crashbarrierBl() {
      super(Material.iron);
      this.func_149663_c("crashbarrier");
      this.func_149658_d("reallifemod:crashbarrier");
      this.func_149672_a(field_149777_j);
   }

   public TileEntity func_149915_a(World world, int id) {
      return new CrashbarrierTE();
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

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
      world.setBlockMetadataWithNotify(x, y, z, l, 2);
   }
}
