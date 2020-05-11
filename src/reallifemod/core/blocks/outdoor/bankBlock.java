package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.core.tiles.BankTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class bankBlock extends BlockContainer implements ITileEntityProvider {

   public bankBlock() {
      super(Material.wood);
      this.func_149663_c("bank");
      this.func_149658_d("reallifemod:bank");
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      BankTE te = (BankTE)world.getTileEntity(x, y, z);
      if(!te.isSitting()) {
         te.sitdown(player, 1.0D);
      } else {
         te.dismountEntity();
      }

      player.func_145747_a(new ChatComponentText("Press L_SHIFT to stand up!"));
      world.markBlockForUpdate(x, y, z);
      return true;
   }

   public TileEntity func_149915_a(World world, int id) {
      return new BankTE();
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
