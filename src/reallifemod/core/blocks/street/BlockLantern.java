package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import de.ItsAMysterious.mods.reallifemod.core.tiles.LanternTE;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLantern extends BlockContainer implements ITileEntityProvider {

   public BlockLantern() {
      super(Material.iron);
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F);
      this.func_149663_c("lantern");
   }

   public boolean func_149744_f() {
      return true;
   }

   public void func_149664_b(World world, int par1, int par2, int par3, int par4) {
      this.func_149642_a(world, par1, par2, par3, new ItemStack(this));
   }

   public void func_149674_a(World world, int x, int y, int z, Random rand) {
      super.func_149674_a(world, x, y, z, rand);
   }

   public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
      return this.func_149744_f() && side != -1;
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new LanternTE();
   }

   public int func_149645_b() {
      return -1;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149721_r() {
      return false;
   }

   public void func_149695_a(World world, int x, int y, int z, Block block) {
      if(world.isBlockIndirectlyGettingPowered(x, y, z)) {
         LanternTE tl = (LanternTE)world.getTileEntity(x, y, z);
         tl.active = true;
         world.markBlockForUpdate(x, y, z);
      }

   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      TileEntity tl = world.getTileEntity(x, y, z);
      if(tl != null && tl instanceof LanternTE) {
         LanternTE tile = (LanternTE)world.getTileEntity(x, y, z);
         tile.active = !tile.active;
         world.markBlockForUpdate(x, y, z);
      }

      return true;
   }

   public int func_149738_a(World p_149738_1_) {
      return 10;
   }

   public void func_149726_b(World world, int x, int y, int z) {
      world.scheduleBlockUpdate(x, y, z, this, this.func_149738_a(world) + world.rand.nextInt(10));
   }

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
      world.setBlockMetadataWithNotify(x, y, z, l, 2);
   }

   private boolean func_150107_m(World p_150107_1_, int p_150107_2_, int p_150107_3_, int p_150107_4_) {
      return World.doesBlockHaveSolidTopSurface(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_);
   }

   public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
      return this.func_150107_m(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_);
   }
}
