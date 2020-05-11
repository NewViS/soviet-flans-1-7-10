package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.api.audio.OGGPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Vector3f;

public class ItemSaw extends Item {

   public static Vector3f table;
   public OGGPlayer radioplayer;


   public ItemSaw() {
      this.func_111206_d("reallifemod:saw");
      this.func_77655_b("saw");
   }

   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
      return stack;
   }
}
