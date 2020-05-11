package de.ItsAMysterious.mods.reallifemod.core.tiles;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityBaby;
import de.ItsAMysterious.mods.reallifemod.core.items.EntityStoreItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class BabybedTE extends TileEntity {

   public EntityBaby lyingbaby;


   public void func_145845_h() {
      super.updateEntity();
      if(this.lyingbaby != null) {
         this.lyingbaby.isLying = true;
         this.lyingbaby.func_70107_b((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.35D, (double)this.field_145849_e + 0.5D);
         this.lyingbaby.field_70125_A = 909.0F;
         this.lyingbaby.func_70034_d(0.0F);
      }

   }

   public void laydown(EntityPlayer player, EntityBaby baby) {
      this.lyingbaby = baby;
   }

   public void standup(EntityPlayer player) {
      if(this.lyingbaby != null && this.lyingbaby instanceof EntityBaby) {
         EntityBaby instance = this.lyingbaby;
         this.lyingbaby = null;
         instance.isLying = false;
         if(!this.field_145850_b.isRemote) {
            player.func_145747_a(new ChatComponentText(EnumChatFormatting.BOLD + "Your baby now is on your arm(and in your inventory!!"));
         }

         EntityStoreItem babyEntityItem = new EntityStoreItem(instance);
         player.inventory.addItemStackToInventory(new ItemStack(babyEntityItem));
      }

   }

   public int func_145832_p() {
      if(this.field_145850_b != null) {
         if(this.field_145847_g == -1) {
            this.field_145847_g = this.field_145850_b.getBlockMetadata(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         }

         return this.field_145847_g;
      } else {
         return 1;
      }
   }
}
