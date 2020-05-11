package net.minecraft.item;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemLead extends Item {

   public ItemLead() {
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      Block var11 = var3.getBlock(var4, var5, var6);
      if(var11.getRenderType() == 11) {
         if(var3.isRemote) {
            return true;
         } else {
            func_150909_a(var2, var3, var4, var5, var6);
            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean func_150909_a(EntityPlayer var0, World var1, int var2, int var3, int var4) {
      EntityLeashKnot var5 = EntityLeashKnot.getKnotForBlock(var1, var2, var3, var4);
      boolean var6 = false;
      double var7 = 7.0D;
      List var9 = var1.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox((double)var2 - var7, (double)var3 - var7, (double)var4 - var7, (double)var2 + var7, (double)var3 + var7, (double)var4 + var7));
      if(var9 != null) {
         Iterator var10 = var9.iterator();

         while(var10.hasNext()) {
            EntityLiving var11 = (EntityLiving)var10.next();
            if(var11.getLeashed() && var11.getLeashedToEntity() == var0) {
               if(var5 == null) {
                  var5 = EntityLeashKnot.func_110129_a(var1, var2, var3, var4);
               }

               var11.setLeashedToEntity(var5, true);
               var6 = true;
            }
         }
      }

      return var6;
   }
}
