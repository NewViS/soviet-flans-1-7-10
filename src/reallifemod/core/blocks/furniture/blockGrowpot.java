package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.tiles.GrowpotTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class blockGrowpot extends BlockContainer implements ITileEntityProvider {

   public blockGrowpot(Material material) {
      super(material);
      this.func_149711_c(5.0F);
      this.func_149752_b(5.0F);
      this.func_149672_a(field_149769_e);
      this.func_149647_a(RealLifeMod.Furniture);
      this.func_149663_c("growpot");
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.field_149785_s = true;
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

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      if(world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof GrowpotTE) {
         GrowpotTE tile = (GrowpotTE)world.getTileEntity(x, y, z);
         if(!tile.isFilled) {
            if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(Blocks.dirt)) {
               tile.isFilled = true;
               --player.inventory.getCurrentItem().stackSize;
            }
         } else if(tile.isFilled && player.inventory.getCurrentItem() != null) {
            if(!(player.inventory.getCurrentItem().getItem() instanceof ItemSpade)) {
               return false;
            }

            player.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(Blocks.dirt)));
            tile.isFilled = false;
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isFertile(World world, int x, int y, int z) {
      return world.getBlockMetadata(x, y, z) > 0;
   }

   public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
      if(world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof GrowpotTE) {
         GrowpotTE pot = (GrowpotTE)world.getTileEntity(x, y, z);
         return pot.isFilled;
      } else {
         return false;
      }
   }

   public TileEntity func_149915_a(World var1, int var2) {
      return new GrowpotTE();
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister iconRegister) {
      this.field_149761_L = iconRegister.registerIcon("reallifemod:" + this.func_149739_a().substring(5));
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
