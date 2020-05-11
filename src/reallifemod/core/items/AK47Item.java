package de.ItsAMysterious.mods.reallifemod.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.core.entitys.bullets.EntityBullet;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class AK47Item extends ExtendedItem {

   public boolean aiming = false;
   private double shootcounter = 0.0D;
   private int ammo = 30;


   public AK47Item() {
      this.func_77664_n();
      this.func_111206_d("reallifemod:AK47");
      this.func_77655_b("AK47");
      this.setPrice(1700.0D);
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

         if(Mouse.isButtonDown(0) && !world.isRemote) {
            this.shoot(player, world);
         }
      } else {
         this.aiming = false;
      }

   }

   public void shoot(Entity player, World world) {
      double x = player.posX + Math.sin((double)player.getRotationYawHead() * 3.141592653589793D / 180.0D);
      double z = player.posZ + Math.cos((double)player.getRotationYawHead() * 3.141592653589793D / 180.0D);
      if(this.shootcounter > 1.0D) {
         this.shootcounter = 0.0D;
      }

      if(this.shootcounter == 0.0D && this.ammo > 0 && !world.isRemote) {
         world.playSoundAtEntity(player, "reallifemod:AK47_Shoot", 1.0F, 1.0F);
         world.spawnEntityInWorld(new EntityBullet(world, (EntityLivingBase)player, 10.0F));
      }

      this.shootcounter += 0.55D;
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
      list.add(EnumChatFormatting.GOLD + "Function:");
      list.add(EnumChatFormatting.RED + "Shoot players and mobs fast");
   }
}
