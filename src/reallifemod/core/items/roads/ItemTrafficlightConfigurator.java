package de.ItsAMysterious.mods.reallifemod.core.items.roads;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTrafficlightConfigurator extends Item {

   public ItemTrafficlightConfigurator() {
      this.func_77655_b("trafficlightconfigurator");
      this.func_111206_d("reallifemod:configurator");
      this.func_77637_a(RealLifeMod.Streets);
   }

   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
      if(world.isRemote) {
         RealLifeMod.proxy.ConfigModeTrafficLights = !RealLifeMod.proxy.ConfigModeTrafficLights;
      }

      return stack;
   }
}
