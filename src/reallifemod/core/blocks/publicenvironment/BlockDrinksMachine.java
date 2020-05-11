package de.ItsAMysterious.mods.reallifemod.core.blocks.publicenvironment;

import de.ItsAMysterious.mods.reallifemod.core.tiles.DrinksmachineTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDrinksMachine extends BlockContainer implements ITileEntityProvider {

   public BlockDrinksMachine() {
      super(Material.rock);
      this.func_149663_c("drinksmachine");
      this.func_149658_d("reallifemod:iconDrinksmachine");
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new DrinksmachineTE();
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

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int x2, float x3, float y3, float z3) {
      return true;
   }

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
      world.setBlockMetadataWithNotify(x, y, z, l, 2);
   }
}
