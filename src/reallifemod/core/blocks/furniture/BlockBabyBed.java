package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import de.ItsAMysterious.mods.reallifemod.core.tiles.BabybedTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBabyBed extends BlockContainer {

   public BlockBabyBed() {
      super(Materials.plastic);
      this.func_149663_c("BabyBed");
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new BabybedTE();
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
      BabybedTE tile = (BabybedTE)world.getTileEntity(x, y, z);
      if(player.field_70153_n != null && player.field_70153_n instanceof EntityBaby) {
         tile.laydown(player, (EntityBaby)player.field_70153_n);
      } else if(tile.lyingbaby != null && player.field_70153_n == null) {
         tile.lyingbaby.func_70050_g(1);
         tile.lyingbaby.func_70078_a(player);
      }

      return true;
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
