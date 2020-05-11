package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.tiles.CookingRodTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BlockCookingRod extends BlockContainer {

   public BlockCookingRod() {
      super(Material.wood);
      this.func_149663_c("CookingRod");
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new CookingRodTE();
   }

   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4) {
      if(!world.isRemote) {
         if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemFood) {
            CookingRodTE tile = (CookingRodTE)world.getTileEntity(x, y, z);
            if(tile.getNextEmptySlot() != -1) {
               tile.func_70299_a(tile.getNextEmptySlot(), player.getCurrentEquippedItem());
               player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            } else {
               player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "This rod is full!"));
            }
         } else if(player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem() != null && !(player.getCurrentEquippedItem().getItem() instanceof ItemFood)) {
            player.openGui(RealLifeMod.instance, 56, world, x, y, z);
         }
      }

      return true;
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
}
