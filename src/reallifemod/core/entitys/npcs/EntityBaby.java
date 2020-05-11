package de.ItsAMysterious.mods.reallifemod.core.entitys.npcs;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod_Items;
import de.ItsAMysterious.mods.reallifemod.core.toys.ItemRattle;
import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class EntityBaby extends EntityCreature {

   public boolean isLying;
   public boolean canWalk;
   public float saturation;
   public boolean isSleeping;
   public int onArmCounter;


   public EntityBaby(World world) {
      super(world);
      this.func_70105_a(0.5F, 1.0F);
      this.field_70714_bg.addTask(0, new EntityAIWander(this, 0.5D));
      this.field_70714_bg.addTask(1, new EntityAIPanic(this, 0.5D));
      this.field_70714_bg.addTask(2, new EntityAITempt(this, 0.5D, RealLifeMod_Items.rattle, false));
      this.onArmCounter = 0;
      this.field_70158_ak = true;
   }

   public EntityBaby(World world, double x, double y, double z) {
      this(world);
      this.func_70107_b(x, y, z);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_70154_o != null) {
         this.canWalk = false;
      } else {
         this.field_70145_X = false;
      }

   }

   protected boolean func_70780_i() {
      return this.field_70154_o != null;
   }

   protected boolean func_70085_c(EntityPlayer player) {
      if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemRattle) {
         Random random = new Random();
         if(!this.field_70170_p.isRemote) {
            this.func_85030_a("reallifemod:Giggle" + random.nextInt(2), 1.0F, 1.0F);
         }
      } else if(Keyboard.isKeyDown(29)) {
         if(!this.field_70170_p.isRemote) {
            player.func_145747_a(new ChatComponentText(EnumChatFormatting.BOLD + "Your baby now is on your arm! CTRL+SHIFT+RIGHTCLICK to put it back on the ground!"));
            this.isLying = true;
         }

         this.func_70078_a(player);
      }

      return true;
   }

   public void func_70098_U() {
      super.func_70098_U();
      if(this.field_70154_o instanceof EntityPlayer) {
         this.func_70101_b(this.field_70154_o.rotationYaw, this.field_70125_A);
         this.field_70145_X = true;
         this.field_70165_t = this.field_70154_o.posX;
         this.field_70163_u = this.field_70154_o.posY;
         this.field_70161_v = this.field_70154_o.posZ;
         this.isLying = true;
      }

   }
}
