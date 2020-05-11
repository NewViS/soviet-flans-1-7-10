package net.minecraft.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRecord extends Item {

   private static final Map field_150928_b = new HashMap();
   public final String recordName;


   protected ItemRecord(String var1) {
      this.recordName = var1;
      super.maxStackSize = 1;
      this.setCreativeTab(CreativeTabs.tabMisc);
      field_150928_b.put(var1, this);
   }

   public IIcon getIconFromDamage(int var1) {
      return super.itemIcon;
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      if(var3.getBlock(var4, var5, var6) == Blocks.jukebox && var3.getBlockMetadata(var4, var5, var6) == 0) {
         if(var3.isRemote) {
            return true;
         } else {
            ((BlockJukebox)Blocks.jukebox).func_149926_b(var3, var4, var5, var6, var1);
            var3.playAuxSFXAtEntity((EntityPlayer)null, 1005, var4, var5, var6, Item.getIdFromItem(this));
            --var1.stackSize;
            return true;
         }
      } else {
         return false;
      }
   }

   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      var3.add(this.getRecordNameLocal());
   }

   public String getRecordNameLocal() {
      return StatCollector.translateToLocal("item.record." + this.recordName + ".desc");
   }

   public EnumRarity getRarity(ItemStack var1) {
      return EnumRarity.rare;
   }

   public static ItemRecord getRecord(String var0) {
      return (ItemRecord)field_150928_b.get(var0);
   }

}
