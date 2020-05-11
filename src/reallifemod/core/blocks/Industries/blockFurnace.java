package de.ItsAMysterious.mods.reallifemod.core.blocks.Industries;

import de.ItsAMysterious.mods.reallifemod.core.tiles.BlastfurnaceTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockFurnace extends BlockContainer implements ITileEntityProvider {

   private static boolean burns;
   public static Minecraft mc;


   public blockFurnace() {
      super(Material.rock);
      this.func_149676_a(-1.0F, 0.0F, -1.0F, 1.0F, 3.0F, 0.0F);
      this.func_149663_c("blastfurnace");
   }

   public void func_149664_b(World world, int par1, int par2, int par3, int par4) {
      this.func_149642_a(world, par1, par2, par3, new ItemStack(this));
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new BlastfurnaceTE();
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

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int x2, float x3, float y3, float z3) {
      return true;
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
