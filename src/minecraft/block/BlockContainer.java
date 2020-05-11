package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainer extends Block implements ITileEntityProvider {

   protected BlockContainer(Material var1) {
      super(var1);
      super.isBlockContainer = true;
   }

   public void onBlockAdded(World var1, int var2, int var3, int var4) {
      super.onBlockAdded(var1, var2, var3, var4);
   }

   public void breakBlock(World var1, int var2, int var3, int var4, Block var5, int var6) {
      super.breakBlock(var1, var2, var3, var4, var5, var6);
      var1.removeTileEntity(var2, var3, var4);
   }

   public boolean onBlockEventReceived(World var1, int var2, int var3, int var4, int var5, int var6) {
      super.onBlockEventReceived(var1, var2, var3, var4, var5, var6);
      TileEntity var7 = var1.getTileEntity(var2, var3, var4);
      return var7 != null?var7.receiveClientEvent(var5, var6):false;
   }
}
