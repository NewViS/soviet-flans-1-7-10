package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.core.tiles.TableTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockTable extends BlockContainer implements ITileEntityProvider {

   public TableTE tile;
   private boolean setuptable;


   public blockTable() {
      super(Material.wood);
      this.func_149663_c("woodenleg");
      this.func_149658_d("reallifemod:WIP");
   }

   public TileEntity func_149915_a(World world, int id) {
      return new TableTE();
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public int func_149645_b() {
      return -1;
   }

   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
      if(entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entity;
         if(player != null && world != null) {
            int le = MathHelper.floor_double((double)(player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
            world.setBlockMetadataWithNotify(x, y, z, le, 2);
         }

         world.markBlockForUpdate(x, y, z);
      }

   }
}
