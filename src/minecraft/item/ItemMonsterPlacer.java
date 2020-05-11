package net.minecraft.item;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList$EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMonsterPlacer extends Item {

   private IIcon theIcon;


   public ItemMonsterPlacer() {
      this.setHasSubtypes(true);
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   public String getItemStackDisplayName(ItemStack var1) {
      String var2 = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
      String var3 = EntityList.getStringFromID(var1.getItemDamage());
      if(var3 != null) {
         var2 = var2 + " " + StatCollector.translateToLocal("entity." + var3 + ".name");
      }

      return var2;
   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      EntityList$EntityEggInfo var3 = (EntityList$EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(var1.getItemDamage()));
      return var3 != null?(var2 == 0?var3.primaryColor:var3.secondaryColor):16777215;
   }

   public boolean requiresMultipleRenderPasses() {
      return true;
   }

   public IIcon getIconFromDamageForRenderPass(int var1, int var2) {
      return var2 > 0?this.theIcon:super.getIconFromDamageForRenderPass(var1, var2);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var3.isRemote) {
         return true;
      } else {
         Block var11 = var3.getBlock(var4, var5, var6);
         var4 += Facing.offsetsXForSide[var7];
         var5 += Facing.offsetsYForSide[var7];
         var6 += Facing.offsetsZForSide[var7];
         double var12 = 0.0D;
         if(var7 == 1 && var11.getRenderType() == 11) {
            var12 = 0.5D;
         }

         Entity var14 = spawnCreature(var3, var1.getItemDamage(), (double)var4 + 0.5D, (double)var5 + var12, (double)var6 + 0.5D);
         if(var14 != null) {
            if(var14 instanceof EntityLivingBase && var1.hasDisplayName()) {
               ((EntityLiving)var14).setCustomNameTag(var1.getDisplayName());
            }

            if(!var2.capabilities.isCreativeMode) {
               --var1.stackSize;
            }
         }

         return true;
      }
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if(var2.isRemote) {
         return var1;
      } else {
         MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(var2, var3, true);
         if(var4 == null) {
            return var1;
         } else {
            if(var4.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
               int var5 = var4.blockX;
               int var6 = var4.blockY;
               int var7 = var4.blockZ;
               if(!var2.canMineBlock(var3, var5, var6, var7)) {
                  return var1;
               }

               if(!var3.canPlayerEdit(var5, var6, var7, var4.sideHit, var1)) {
                  return var1;
               }

               if(var2.getBlock(var5, var6, var7) instanceof BlockLiquid) {
                  Entity var8 = spawnCreature(var2, var1.getItemDamage(), (double)var5, (double)var6, (double)var7);
                  if(var8 != null) {
                     if(var8 instanceof EntityLivingBase && var1.hasDisplayName()) {
                        ((EntityLiving)var8).setCustomNameTag(var1.getDisplayName());
                     }

                     if(!var3.capabilities.isCreativeMode) {
                        --var1.stackSize;
                     }
                  }
               }
            }

            return var1;
         }
      }
   }

   public static Entity spawnCreature(World var0, int var1, double var2, double var4, double var6) {
      if(!EntityList.entityEggs.containsKey(Integer.valueOf(var1))) {
         return null;
      } else {
         Entity var8 = null;

         for(int var9 = 0; var9 < 1; ++var9) {
            var8 = EntityList.createEntityByID(var1, var0);
            if(var8 != null && var8 instanceof EntityLivingBase) {
               EntityLiving var10 = (EntityLiving)var8;
               var8.setLocationAndAngles(var2, var4, var6, MathHelper.wrapAngleTo180_float(var0.rand.nextFloat() * 360.0F), 0.0F);
               var10.rotationYawHead = var10.rotationYaw;
               var10.renderYawOffset = var10.rotationYaw;
               var10.onSpawnWithEgg((IEntityLivingData)null);
               var0.spawnEntityInWorld(var8);
               var10.playLivingSound();
            }
         }

         return var8;
      }
   }

   public void getSubItems(Item var1, CreativeTabs var2, List var3) {
      Iterator var4 = EntityList.entityEggs.values().iterator();

      while(var4.hasNext()) {
         EntityList$EntityEggInfo var5 = (EntityList$EntityEggInfo)var4.next();
         var3.add(new ItemStack(var1, 1, var5.spawnedID));
      }

   }

   public void registerIcons(IIconRegister var1) {
      super.registerIcons(var1);
      this.theIcon = var1.registerIcon(this.getIconString() + "_overlay");
   }
}
