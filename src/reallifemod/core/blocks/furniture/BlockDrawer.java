package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.tiles.DrawerTE;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDrawer extends BlockContainer {

   private final Random random = new Random();
   public static BlockDrawer instance = new BlockDrawer();


   public BlockDrawer() {
      super(Material.iron);
      this.func_149663_c("drawer");
      this.func_149658_d("reallifemod:drawer");
   }

   public void func_149719_a(IBlockAccess world, int x, int y, int z) {
      super.func_149719_a(world, x, y, z);
      switch(world.getBlockMetadata(x, y, z)) {
      case 0:
         this.func_149676_a(-0.5F, 0.0F, 0.0F, 1.5F, 1.0F, 1.0F);
         break;
      case 1:
         this.func_149676_a(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.5F);
         break;
      case 2:
         this.func_149676_a(-0.5F, 0.0F, 0.0F, 1.5F, 1.0F, 1.0F);
         break;
      case 3:
         this.func_149676_a(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.5F);
      }

   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
      player.openGui(RealLifeMod.instance, 50, world, x, y, z);
      return true;
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new DrawerTE();
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

   public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
      DrawerTE DrawerTE = (DrawerTE)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
      if(DrawerTE != null) {
         for(int i1 = 0; i1 < DrawerTE.func_70302_i_(); ++i1) {
            ItemStack itemstack = DrawerTE.func_70301_a(i1);
            if(itemstack != null) {
               float f = this.random.nextFloat() * 0.8F + 0.1F;
               float f1 = this.random.nextFloat() * 0.8F + 0.1F;

               EntityItem entityitem;
               for(float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_.spawnEntityInWorld(entityitem)) {
                  int j1 = this.random.nextInt(21) + 10;
                  if(j1 > itemstack.stackSize) {
                     j1 = itemstack.stackSize;
                  }

                  itemstack.stackSize -= j1;
                  entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                  float f3 = 0.05F;
                  entityitem.field_70159_w = (double)((float)this.random.nextGaussian() * f3);
                  entityitem.field_70181_x = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                  entityitem.field_70179_y = (double)((float)this.random.nextGaussian() * f3);
                  if(itemstack.hasTagCompound()) {
                     entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                  }
               }
            }
         }

         p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
      }

   }

}
