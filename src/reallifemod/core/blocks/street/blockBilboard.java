package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiAdvert;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BilboardTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class blockBilboard extends BlockContainer {

   public blockBilboard(Material material) {
      super(Material.iron);
      this.func_149658_d("RealLifeMod:shield");
      this.func_149663_c("advertBilboard");
      this.func_149676_a(-2.0F, 0.0F, 0.0F, 2.0F, 3.0F, 0.4F);
   }

   public void func_149664_b(World world, int par1, int par2, int par3, int par4) {
      this.func_149642_a(world, par1, par2, par3, new ItemStack(this));
      this.func_149655_b(world, (int)((double)par1 + 0.1D), (int)((double)par2 + 0.5D), (int)((double)par3 + 0.1D));
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new BilboardTE();
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

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int id, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      if(world.isRemote) {
         player.openGui(RealLifeMod.instance, GuiAdvert.GUI_ID, world, x, y, z);
      }

      return true;
   }

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
      world.setBlockMetadataWithNotify(x, y, z, l, 2);
   }
}
