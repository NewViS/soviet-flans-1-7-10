package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.tiles.PillarTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class pillarBlock extends BlockContainer {

   public pillarBlock.pillarType Type;


   public pillarBlock(Material material) {
      super(material);
      this.Type = pillarBlock.pillarType.MARMOR;
      this.func_149647_a(RealLifeMod.Outdoor);
      this.func_149658_d(this.field_149768_d);
   }

   public pillarBlock(Material material, pillarBlock.pillarType mat) {
      this(material);
      this.func_149647_a(RealLifeMod.Outdoor);
      this.Type = mat;
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      PillarTE te;
      if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() != Item.getItemFromBlock(this)) {
         te = (PillarTE)world.getTileEntity(x, y, z);
         ++te.scale;
         if(te.scale == 9.7F) {
            te.scale = 0.7F;
         }

         world.markBlockForUpdate(x, y, z);
         return true;
      } else if(player.inventory.getCurrentItem() == null) {
         te = (PillarTE)world.getTileEntity(x, y, z);
         ++te.scale;
         if(te.scale == 9.7F) {
            te.scale = 0.7F;
         }

         world.markBlockForUpdate(x, y, z);
         return true;
      } else if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(this)) {
         world.setBlock(x, y + 1, z, Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()));
         return true;
      } else {
         return false;
      }
   }

   public TileEntity func_149915_a(World world, int id) {
      return new PillarTE(this.field_149768_d, this.Type);
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public String func_149641_N() {
      return this.field_149768_d;
   }

   public int func_149645_b() {
      return -1;
   }

   public static enum pillarType {

      MARMOR("MARMOR", 0),
      ROCK("ROCK", 1),
      SANDSTONE("SANDSTONE", 2),
      IRON("IRON", 3);
      // $FF: synthetic field
      private static final pillarBlock.pillarType[] $VALUES = new pillarBlock.pillarType[]{MARMOR, ROCK, SANDSTONE, IRON};


      private pillarType(String var1, int var2) {}

   }
}
