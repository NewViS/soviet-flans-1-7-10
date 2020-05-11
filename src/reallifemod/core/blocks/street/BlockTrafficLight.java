package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TrafficlightTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTrafficLight extends BlockContainer implements ITileEntityProvider {

   public BlockTrafficLight() {
      super(Material.iron);
      this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 3.0F, 0.75F);
      this.func_149663_c("trafficlight");
      this.func_149658_d("reallifemod:iconTrafficlight");
   }

   public void func_149664_b(World world, int par1, int par2, int par3, int par4) {
      this.func_149642_a(world, par1, par2, par3, new ItemStack(this));
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new TrafficlightTE();
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

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      TrafficlightTE tl = (TrafficlightTE)world.getTileEntity(x, y, z);
      switch(null.$SwitchMap$de$ItsAMysterious$mods$reallifemod$core$tiles$TrafficlightTE$State[tl.state.ordinal()]) {
      case 1:
         tl.state = TrafficlightTE.State.ORANGE;
         break;
      case 2:
         tl.state = TrafficlightTE.State.RED;
         break;
      case 3:
         tl.state = TrafficlightTE.State.INACTIVE;
         break;
      case 4:
         tl.state = TrafficlightTE.State.GREEN;
      }

      tl.delta = 0;
      world.markBlockForUpdate(x, y, z);
      return true;
   }

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      int l = BlockPistonBase.determineOrientation(world, x, y, z, entity);
      world.setBlockMetadataWithNotify(x, y, z, l, 2);
   }
}
