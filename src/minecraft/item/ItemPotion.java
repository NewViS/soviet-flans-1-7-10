package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemPotion extends Item {

   private HashMap effectCache = new HashMap();
   private static final Map field_77835_b = new LinkedHashMap();
   private IIcon field_94591_c;
   private IIcon field_94590_d;
   private IIcon field_94592_ct;


   public ItemPotion() {
      this.setMaxStackSize(1);
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(CreativeTabs.tabBrewing);
   }

   public List getEffects(ItemStack var1) {
      if(var1.hasTagCompound() && var1.getTagCompound().hasKey("CustomPotionEffects", 9)) {
         ArrayList var7 = new ArrayList();
         NBTTagList var3 = var1.getTagCompound().getTagList("CustomPotionEffects", 10);

         for(int var4 = 0; var4 < var3.tagCount(); ++var4) {
            NBTTagCompound var5 = var3.getCompoundTagAt(var4);
            PotionEffect var6 = PotionEffect.readCustomPotionEffectFromNBT(var5);
            if(var6 != null) {
               var7.add(var6);
            }
         }

         return var7;
      } else {
         List var2 = (List)this.effectCache.get(Integer.valueOf(var1.getItemDamage()));
         if(var2 == null) {
            var2 = PotionHelper.getPotionEffects(var1.getItemDamage(), false);
            this.effectCache.put(Integer.valueOf(var1.getItemDamage()), var2);
         }

         return var2;
      }
   }

   public List getEffects(int var1) {
      List var2 = (List)this.effectCache.get(Integer.valueOf(var1));
      if(var2 == null) {
         var2 = PotionHelper.getPotionEffects(var1, false);
         this.effectCache.put(Integer.valueOf(var1), var2);
      }

      return var2;
   }

   public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3) {
      if(!var3.capabilities.isCreativeMode) {
         --var1.stackSize;
      }

      if(!var2.isRemote) {
         List var4 = this.getEffects(var1);
         if(var4 != null) {
            Iterator var5 = var4.iterator();

            while(var5.hasNext()) {
               PotionEffect var6 = (PotionEffect)var5.next();
               var3.addPotionEffect(new PotionEffect(var6));
            }
         }
      }

      if(!var3.capabilities.isCreativeMode) {
         if(var1.stackSize <= 0) {
            return new ItemStack(Items.glass_bottle);
         }

         var3.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
      }

      return var1;
   }

   public int getMaxItemUseDuration(ItemStack var1) {
      return 32;
   }

   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.drink;
   }

   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if(isSplash(var1.getItemDamage())) {
         if(!var3.capabilities.isCreativeMode) {
            --var1.stackSize;
         }

         var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));
         if(!var2.isRemote) {
            var2.spawnEntityInWorld(new EntityPotion(var2, var3, var1));
         }

         return var1;
      } else {
         var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
         return var1;
      }
   }

   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10) {
      return false;
   }

   public IIcon getIconFromDamage(int var1) {
      return isSplash(var1)?this.field_94591_c:this.field_94590_d;
   }

   public IIcon getIconFromDamageForRenderPass(int var1, int var2) {
      return var2 == 0?this.field_94592_ct:super.getIconFromDamageForRenderPass(var1, var2);
   }

   public static boolean isSplash(int var0) {
      return (var0 & 16384) != 0;
   }

   public int getColorFromDamage(int var1) {
      return PotionHelper.func_77915_a(var1, false);
   }

   public int getColorFromItemStack(ItemStack var1, int var2) {
      return var2 > 0?16777215:this.getColorFromDamage(var1.getItemDamage());
   }

   public boolean requiresMultipleRenderPasses() {
      return true;
   }

   public boolean isEffectInstant(int var1) {
      List var2 = this.getEffects(var1);
      if(var2 != null && !var2.isEmpty()) {
         Iterator var3 = var2.iterator();

         PotionEffect var4;
         do {
            if(!var3.hasNext()) {
               return false;
            }

            var4 = (PotionEffect)var3.next();
         } while(!Potion.potionTypes[var4.getPotionID()].isInstant());

         return true;
      } else {
         return false;
      }
   }

   public String getItemStackDisplayName(ItemStack var1) {
      if(var1.getItemDamage() == 0) {
         return StatCollector.translateToLocal("item.emptyPotion.name").trim();
      } else {
         String var2 = "";
         if(isSplash(var1.getItemDamage())) {
            var2 = StatCollector.translateToLocal("potion.prefix.grenade").trim() + " ";
         }

         List var3 = Items.potionitem.getEffects(var1);
         String var4;
         if(var3 != null && !var3.isEmpty()) {
            var4 = ((PotionEffect)var3.get(0)).getEffectName();
            var4 = var4 + ".postfix";
            return var2 + StatCollector.translateToLocal(var4).trim();
         } else {
            var4 = PotionHelper.func_77905_c(var1.getItemDamage());
            return StatCollector.translateToLocal(var4).trim() + " " + super.getItemStackDisplayName(var1);
         }
      }
   }

   public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4) {
      if(var1.getItemDamage() != 0) {
         List var5 = Items.potionitem.getEffects(var1);
         HashMultimap var6 = HashMultimap.create();
         Iterator var16;
         if(var5 != null && !var5.isEmpty()) {
            var16 = var5.iterator();

            while(var16.hasNext()) {
               PotionEffect var8 = (PotionEffect)var16.next();
               String var9 = StatCollector.translateToLocal(var8.getEffectName()).trim();
               Potion var10 = Potion.potionTypes[var8.getPotionID()];
               Map var11 = var10.func_111186_k();
               if(var11 != null && var11.size() > 0) {
                  Iterator var12 = var11.entrySet().iterator();

                  while(var12.hasNext()) {
                     Entry var13 = (Entry)var12.next();
                     AttributeModifier var14 = (AttributeModifier)var13.getValue();
                     AttributeModifier var15 = new AttributeModifier(var14.getName(), var10.func_111183_a(var8.getAmplifier(), var14), var14.getOperation());
                     var6.put(((IAttribute)var13.getKey()).getAttributeUnlocalizedName(), var15);
                  }
               }

               if(var8.getAmplifier() > 0) {
                  var9 = var9 + " " + StatCollector.translateToLocal("potion.potency." + var8.getAmplifier()).trim();
               }

               if(var8.getDuration() > 20) {
                  var9 = var9 + " (" + Potion.getDurationString(var8) + ")";
               }

               if(var10.isBadEffect()) {
                  var3.add(EnumChatFormatting.RED + var9);
               } else {
                  var3.add(EnumChatFormatting.GRAY + var9);
               }
            }
         } else {
            String var7 = StatCollector.translateToLocal("potion.empty").trim();
            var3.add(EnumChatFormatting.GRAY + var7);
         }

         if(!var6.isEmpty()) {
            var3.add("");
            var3.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("potion.effects.whenDrank"));
            var16 = var6.entries().iterator();

            while(var16.hasNext()) {
               Entry var17 = (Entry)var16.next();
               AttributeModifier var19 = (AttributeModifier)var17.getValue();
               double var18 = var19.getAmount();
               double var20;
               if(var19.getOperation() != 1 && var19.getOperation() != 2) {
                  var20 = var19.getAmount();
               } else {
                  var20 = var19.getAmount() * 100.0D;
               }

               if(var18 > 0.0D) {
                  var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + var19.getOperation(), new Object[]{ItemStack.field_111284_a.format(var20), StatCollector.translateToLocal("attribute.name." + (String)var17.getKey())}));
               } else if(var18 < 0.0D) {
                  var20 *= -1.0D;
                  var3.add(EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + var19.getOperation(), new Object[]{ItemStack.field_111284_a.format(var20), StatCollector.translateToLocal("attribute.name." + (String)var17.getKey())}));
               }
            }
         }

      }
   }

   public boolean hasEffect(ItemStack var1) {
      List var2 = this.getEffects(var1);
      return var2 != null && !var2.isEmpty();
   }

   public void getSubItems(Item var1, CreativeTabs var2, List var3) {
      super.getSubItems(var1, var2, var3);
      int var5;
      if(field_77835_b.isEmpty()) {
         for(int var4 = 0; var4 <= 15; ++var4) {
            for(var5 = 0; var5 <= 1; ++var5) {
               int var6;
               if(var5 == 0) {
                  var6 = var4 | 8192;
               } else {
                  var6 = var4 | 16384;
               }

               for(int var7 = 0; var7 <= 2; ++var7) {
                  int var8 = var6;
                  if(var7 != 0) {
                     if(var7 == 1) {
                        var8 = var6 | 32;
                     } else if(var7 == 2) {
                        var8 = var6 | 64;
                     }
                  }

                  List var9 = PotionHelper.getPotionEffects(var8, false);
                  if(var9 != null && !var9.isEmpty()) {
                     field_77835_b.put(var9, Integer.valueOf(var8));
                  }
               }
            }
         }
      }

      Iterator var10 = field_77835_b.values().iterator();

      while(var10.hasNext()) {
         var5 = ((Integer)var10.next()).intValue();
         var3.add(new ItemStack(var1, 1, var5));
      }

   }

   public void registerIcons(IIconRegister var1) {
      this.field_94590_d = var1.registerIcon(this.getIconString() + "_" + "bottle_drinkable");
      this.field_94591_c = var1.registerIcon(this.getIconString() + "_" + "bottle_splash");
      this.field_94592_ct = var1.registerIcon(this.getIconString() + "_" + "overlay");
   }

   public static IIcon func_94589_d(String var0) {
      return var0.equals("bottle_drinkable")?Items.potionitem.field_94590_d:(var0.equals("bottle_splash")?Items.potionitem.field_94591_c:(var0.equals("overlay")?Items.potionitem.field_94592_ct:null));
   }

}
