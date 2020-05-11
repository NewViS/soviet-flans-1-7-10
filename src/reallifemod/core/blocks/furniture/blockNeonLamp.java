package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.tiles.NeonlampTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockNeonLamp extends BlockContainer implements ITileEntityProvider {

   public blockNeonLamp() {
      super(Materials.plastic);
      this.func_149663_c("neonlamp");
      this.func_149658_d("reallifemod:iconNeonlamp");
      this.func_149676_a(-0.3F, 0.7F, 0.3F, 1.3F, 1.0F, 0.7F);
      this.func_149715_a(0.9375F);
      this.func_149713_g(0);
   }

   public void func_149664_b(World world, int par1, int par2, int par3, int par4) {
      this.func_149642_a(world, par1, par2, par3, new ItemStack(this));
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new NeonlampTE();
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      TileEntity te = world.getTileEntity(x, y, z);
      if(te != null && te instanceof NeonlampTE) {
         NeonlampTE tet = (NeonlampTE)te;
         tet.isActive = !tet.isActive;
         world.markBlockForUpdate(x, y, z);
         return true;
      } else {
         return false;
      }
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
