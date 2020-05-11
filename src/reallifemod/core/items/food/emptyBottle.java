package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Blocks;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class emptyBottle extends ExtendedItem {

   private boolean showMessage = true;


   public emptyBottle() {
      this.func_77655_b("bottle");
      this.func_111206_d("reallifemod:bottle");
      this.func_77637_a(RealLifeMod.rlmfood);
      this.func_77625_d(1);
      this.setPrice(0.25D);
   }

   public EnumAction func_77661_b(ItemStack par1ItemStack) {
      return EnumAction.drink;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World world, EntityPlayer player) {
      Minecraft mc = Minecraft.getMinecraft();
      if(world.getBlock(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ) != Blocks.water && world.getBlock(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ) != RealLifeMod_Blocks.sink) {
         if(this.showMessage) {
            player.func_145747_a(new ChatComponentText("You have to fill up your Bottle with some fresh " + EnumChatFormatting.AQUA + "Water" + EnumChatFormatting.WHITE + "!"));
            this.showMessage = false;
         }
      } else {
         int slot = player.inventory.currentItem;
         player.inventory.setInventorySlotContents(slot, new ItemStack(RealLifeMod_Items.bottle));
      }

      return par1ItemStack;
   }
}
