package de.ItsAMysterious.mods.reallifemod.core.items.clothes;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.World;

public class fireprotectionsuite extends ItemArmor {

   public fireprotectionsuite(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
      super(material, 25, type);
      this.func_77655_b(unlocalizedName);
      this.func_111206_d("reallifemod:" + unlocalizedName);
   }

   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
      return "reallifemod:textures/models/armor/fireprotectionsuite_" + (this.field_77881_a == 2?"2":"1") + ".png";
   }

   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
      if(player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem().getClass() == this.getClass()) {
         player.func_70066_B();
         player.field_70172_ad = 1000;
         player.field_70174_ab = 1000;
         if(player.func_70055_a(Material.lava)) {
            player.func_70606_j(player.func_110138_aP());
         }
      }

      super.onArmorTick(world, player, itemStack);
   }
}
