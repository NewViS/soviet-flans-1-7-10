package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.core.entitys.bullets.EntityBullet;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class ItemUzi extends ExtendedItem {

   public boolean aiming = false;
   private int shootcounter = 0;
   private int ammo = 30;


   public ItemUzi() {
      this.func_77664_n();
      this.func_111206_d("reallifemod:uzi");
      this.func_77655_b("uzi");
      this.setPrice(1500.0D);
   }

   public boolean func_82788_x() {
      return false;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.none;
   }

   public void func_77663_a(ItemStack stack, World world, Entity player, int p_77663_4_, boolean p_77663_5_) {
      EntityPlayer p = (EntityPlayer)player;
      if(p.getCurrentEquippedItem() == stack) {
         if(Mouse.isButtonDown(1)) {
            this.aiming = true;
         } else {
            this.aiming = false;
         }

         if(Mouse.isButtonDown(0)) {
            this.shoot(player, world);
         }
      } else {
         this.aiming = false;
      }

   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer player) {
      super.func_77659_a(p_77659_1_, p_77659_2_, player);
      return p_77659_1_;
   }

   public void shoot(Entity player, World world) {
      if(this.shootcounter > 1) {
         this.shootcounter = 0;
      }

      if(this.shootcounter == 0 && this.ammo > 0) {
         player.playSound("reallifemod:uzi_shoot", 1.0F, 1.0F);
         world.spawnEntityInWorld(new EntityBullet(world, (EntityLivingBase)player, 10.0F));
      }

      ++this.shootcounter;
   }
}
