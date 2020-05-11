package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;

public class TileEntityRendererChestHelper {

   public static TileEntityRendererChestHelper instance = new TileEntityRendererChestHelper();
   private TileEntityChest field_147717_b = new TileEntityChest(0);
   private TileEntityChest field_147718_c = new TileEntityChest(1);
   private TileEntityEnderChest field_147716_d = new TileEntityEnderChest();


   public void renderChest(Block var1, int var2, float var3) {
      if(var1 == Blocks.ender_chest) {
         TileEntityRendererDispatcher.instance.renderTileEntityAt(this.field_147716_d, 0.0D, 0.0D, 0.0D, 0.0F);
      } else if(var1 == Blocks.trapped_chest) {
         TileEntityRendererDispatcher.instance.renderTileEntityAt(this.field_147718_c, 0.0D, 0.0D, 0.0D, 0.0F);
      } else {
         TileEntityRendererDispatcher.instance.renderTileEntityAt(this.field_147717_b, 0.0D, 0.0D, 0.0D, 0.0F);
      }

   }

}
