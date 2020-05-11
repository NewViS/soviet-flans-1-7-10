package net.minecraft.item;

import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.BlockSkull;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSkull extends Item {

   private static final String[] skullTypes = new String[]{"skeleton", "wither", "zombie", "char", "creeper"};
   public static final String[] field_94587_a = new String[]{"skeleton", "wither", "zombie", "steve", "creeper"};
   private IIcon[] field_94586_c;


   public ItemSkull() {
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var7 == 0) {
         return false;
      } else if(!var3.getBlock(var4, var5, var6).getMaterial().isSolid()) {
         return false;
      } else {
         if(var7 == 1) {
            ++var5;
         }

         if(var7 == 2) {
            --var6;
         }

         if(var7 == 3) {
            ++var6;
         }

         if(var7 == 4) {
            --var4;
         }

         if(var7 == 5) {
            ++var4;
         }

         if(!var3.isRemote) {
            var3.setBlock(var4, var5, var6, Blocks.skull, var7, 2);
            int var11 = 0;
            if(var7 == 1) {
               var11 = MathHelper.floor_double((double)(var2.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
            }

            TileEntity var12 = var3.getTileEntity(var4, var5, var6);
            if(var12 != null && var12 instanceof TileEntitySkull) {
               if(var1.getItemDamage() == 3) {
                  GameProfile var13 = null;
                  if(var1.hasTagCompound()) {
                     NBTTagCompound var14 = var1.getTagCompound();
                     if(var14.hasKey("SkullOwner", 10)) {
                        var13 = NBTUtil.func_152459_a(var14.getCompoundTag("SkullOwner"));
                     } else if(var14.hasKey("SkullOwner", 8) && var14.getString("SkullOwner").length() > 0) {
                        var13 = new GameProfile((UUID)null, var14.getString("SkullOwner"));
                     }
                  }

                  ((TileEntitySkull)var12).func_152106_a(var13);
               } else {
                  ((TileEntitySkull)var12).func_152107_a(var1.getItemDamage());
               }

               ((TileEntitySkull)var12).func_145903_a(var11);
               ((BlockSkull)Blocks.skull).func_149965_a(var3, var4, var5, var6, (TileEntitySkull)var12);
            }

            --var1.stackSize;
         }

         return true;
      }
   }

   public void getSubItems(Item var1, CreativeTabs var2, List var3) {
      for(int var4 = 0; var4 < skullTypes.length; ++var4) {
         var3.add(new ItemStack(var1, 1, var4));
      }

   }

   public IIcon getIconFromDamage(int var1) {
      if(var1 < 0 || var1 >= skullTypes.length) {
         var1 = 0;
      }

      return this.field_94586_c[var1];
   }

   public int getMetadata(int var1) {
      return var1;
   }

   public String getUnlocalizedName(ItemStack var1) {
      int var2 = var1.getItemDamage();
      if(var2 < 0 || var2 >= skullTypes.length) {
         var2 = 0;
      }

      return super.getUnlocalizedName() + "." + skullTypes[var2];
   }

   public String getItemStackDisplayName(ItemStack var1) {
      if(var1.getItemDamage() == 3 && var1.hasTagCompound()) {
         if(var1.getTagCompound().hasKey("SkullOwner", 10)) {
            return StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[]{NBTUtil.func_152459_a(var1.getTagCompound().getCompoundTag("SkullOwner")).getName()});
         }

         if(var1.getTagCompound().hasKey("SkullOwner", 8)) {
            return StatCollector.translateToLocalFormatted("item.skull.player.name", new Object[]{var1.getTagCompound().getString("SkullOwner")});
         }
      }

      return super.getItemStackDisplayName(var1);
   }

   public void registerIcons(IIconRegister var1) {
      this.field_94586_c = new IIcon[field_94587_a.length];

      for(int var2 = 0; var2 < field_94587_a.length; ++var2) {
         this.field_94586_c[var2] = var1.registerIcon(this.getIconString() + "_" + field_94587_a[var2]);
      }

   }

}
