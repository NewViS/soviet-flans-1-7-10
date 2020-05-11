package net.minecraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMinecartChest extends EntityMinecartContainer {

   public EntityMinecartChest(World var1) {
      super(var1);
   }

   public EntityMinecartChest(World var1, double var2, double var4, double var6) {
      super(var1, var2, var4, var6);
   }

   public void killMinecart(DamageSource var1) {
      super.killMinecart(var1);
      this.func_145778_a(Item.getItemFromBlock(Blocks.chest), 1, 0.0F);
   }

   public int getSizeInventory() {
      return 27;
   }

   public int getMinecartType() {
      return 1;
   }

   public Block func_145817_o() {
      return Blocks.chest;
   }

   public int getDefaultDisplayTileOffset() {
      return 8;
   }
}
