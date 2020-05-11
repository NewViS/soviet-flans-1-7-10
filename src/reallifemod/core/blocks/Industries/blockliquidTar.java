package de.ItsAMysterious.mods.reallifemod.core.blocks.Industries;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class blockliquidTar extends BlockFluidClassic {

   @SideOnly(Side.CLIENT)
   protected IIcon stillIcon;
   @SideOnly(Side.CLIENT)
   protected IIcon flowingIcon;


   public blockliquidTar(Fluid fluid) {
      super(fluid, Material.lava);
      this.func_149647_a(RealLifeMod.RLMTechnologie);
   }

   public IIcon func_149691_a(int side, int meta) {
      return side != 0 && side != 1?this.flowingIcon:this.stillIcon;
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister register) {
      this.stillIcon = register.registerIcon("reallifemod:tar");
      this.flowingIcon = register.registerIcon("reallifemod:tarflowing");
   }

   public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
      return world.getBlock(x, y, z).getMaterial().isLiquid()?true:super.canDisplace(world, x, y, z);
   }

   public boolean displaceIfPossible(World world, int x, int y, int z) {
      return world.getBlock(x, y, z).getMaterial().isLiquid()?true:super.displaceIfPossible(world, x, y, z);
   }
}
