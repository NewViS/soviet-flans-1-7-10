package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.tiles.RadioTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockRadio extends BlockContainer {

   private RadioTE tile;


   public blockRadio() {
      super(Materials.plastic);
      this.func_149663_c("radio");
      this.func_149658_d("reallifemod:radio");
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      RadioTE tile = new RadioTE();
      this.tile = tile;
      return this.tile;
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
      return true;
   }

   public void func_149664_b(World world, int par1, int par2, int par3, int par4) {
      this.func_149655_b(world, (int)((double)par1 + 0.1D), (int)((double)par2 + 0.5D), (int)((double)par3 + 0.1D));
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
