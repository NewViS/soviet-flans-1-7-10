package de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.tiles.WashbasinTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockWashingbasin extends BlockContainer {

   public BlockWashingbasin() {
      super(Materials.marmor);
      this.func_149663_c("washbasin");
      this.func_149658_d("reallifemod:iconWashbasin");
   }

   public TileEntity func_149915_a(World world, int var2) {
      return new WashbasinTE();
   }

   public int func_149645_b() {
      return -1;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149721_r() {
      return false;
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int x2, float x3, float y3, float z3) {
      if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == RealLifeMod_Items.emptybottle) {
         player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(RealLifeMod_Items.bottle));
         return true;
      } else {
         return false;
      }
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
