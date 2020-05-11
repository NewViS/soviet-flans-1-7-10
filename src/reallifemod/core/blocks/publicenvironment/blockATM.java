package de.ItsAMysterious.mods.reallifemod.core.blocks.publicenvironment;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.core.tiles.atmTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockATM extends BlockContainer {

   public blockATM() {
      super(Material.wood);
      this.func_149663_c("atm");
      this.func_149658_d("reallifemod:atmitem");
      this.field_149758_A = true;
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

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      super.func_149727_a(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
      if(player.inventory.getCurrentItem() != null) {
         financialProps var10000;
         if(player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(Blocks.emerald_ore)) {
            var10000 = (financialProps)player.getExtendedProperties("financialProps");
            financialProps.Cash += 2000.0D;
            --player.inventory.getCurrentItem().stackSize;
         }

         if(player.inventory.getCurrentItem().getItem() == Items.diamond) {
            var10000 = (financialProps)player.getExtendedProperties("financialProps");
            financialProps.Cash += 1000.0D;
            --player.inventory.getCurrentItem().stackSize;
         }

         if(player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(Blocks.gold_ore)) {
            var10000 = (financialProps)player.getExtendedProperties("financialProps");
            financialProps.Cash += 500.0D;
            --player.inventory.getCurrentItem().stackSize;
         }

         if(player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(Blocks.iron_ore)) {
            var10000 = (financialProps)player.getExtendedProperties("financialProps");
            financialProps.Cash += 100.0D;
            --player.inventory.getCurrentItem().stackSize;
         }

         if(player.inventory.getCurrentItem().getItem() == Item.getItemFromBlock(Blocks.coal_ore)) {
            var10000 = (financialProps)player.getExtendedProperties("financialProps");
            financialProps.Cash += 50.0D;
            --player.inventory.getCurrentItem().stackSize;
         }

         if(player.inventory.getCurrentItem().getItem() == Items.coal) {
            var10000 = (financialProps)player.getExtendedProperties("financialProps");
            financialProps.Cash += 17.0D;
            --player.inventory.getCurrentItem().stackSize;
         }

         return true;
      } else {
         return false;
      }
   }

   public TileEntity func_149915_a(World world, int id) {
      return new atmTE();
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
}
