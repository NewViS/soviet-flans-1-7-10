package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemIdentityCard extends ExtendedItem {

   public ItemIdentityCard() {
      this.func_77655_b("identitycard");
      this.func_111206_d("reallifemod:itemIdentityCard");
      this.setPrice(5.0D);
   }

   public void func_77622_d(ItemStack itemStack, World world, EntityPlayer player) {
      if(itemStack.getItem().getClass() == this.getClass()) {
         itemStack.stackTagCompound = new NBTTagCompound();
         NBTTagCompound var10000 = itemStack.stackTagCompound;
         RealLifeProperties var10002 = (RealLifeProperties)player.getExtendedProperties("RealLifeProperties");
         var10000.setString("owner", RealLifeProperties.name);
      }

      super.func_77622_d(itemStack, world, player);
   }

   public void func_77624_a(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
      if(itemStack.stackTagCompound != null) {
         String owner = itemStack.stackTagCompound.getString("owner");
         list.add("owner: " + owner);
         if(owner.equals(player.getDisplayName())) {
            list.add(EnumChatFormatting.GREEN + owner);
         }
      }

   }

   public static int getCodeIfValid(EntityPlayer player) {
      ItemStack hold = player.inventory.getCurrentItem();
      return hold.stackTagCompound.getInteger("user");
   }
}
