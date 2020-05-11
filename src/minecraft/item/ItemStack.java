package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public final class ItemStack {

   public static final DecimalFormat field_111284_a = new DecimalFormat("#.###");
   public int stackSize;
   public int animationsToGo;
   private Item field_151002_e;
   public NBTTagCompound stackTagCompound;
   private int itemDamage;
   private EntityItemFrame itemFrame;


   public ItemStack(Block var1) {
      this(var1, 1);
   }

   public ItemStack(Block var1, int var2) {
      this(var1, var2, 0);
   }

   public ItemStack(Block var1, int var2, int var3) {
      this(Item.getItemFromBlock(var1), var2, var3);
   }

   public ItemStack(Item var1) {
      this(var1, 1);
   }

   public ItemStack(Item var1, int var2) {
      this(var1, var2, 0);
   }

   public ItemStack(Item var1, int var2, int var3) {
      this.field_151002_e = var1;
      this.stackSize = var2;
      this.itemDamage = var3;
      if(this.itemDamage < 0) {
         this.itemDamage = 0;
      }

   }

   public static ItemStack loadItemStackFromNBT(NBTTagCompound var0) {
      ItemStack var1 = new ItemStack();
      var1.readFromNBT(var0);
      return var1.getItem() != null?var1:null;
   }

   private ItemStack() {}

   public ItemStack splitStack(int var1) {
      ItemStack var2 = new ItemStack(this.field_151002_e, var1, this.itemDamage);
      if(this.stackTagCompound != null) {
         var2.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
      }

      this.stackSize -= var1;
      return var2;
   }

   public Item getItem() {
      return this.field_151002_e;
   }

   public IIcon getIconIndex() {
      return this.getItem().getIconIndex(this);
   }

   public int getItemSpriteNumber() {
      return this.getItem().getSpriteNumber();
   }

   public boolean tryPlaceItemIntoWorld(EntityPlayer var1, World var2, int var3, int var4, int var5, int var6, float var7, float var8, float var9) {
      boolean var10 = this.getItem().onItemUse(this, var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if(var10) {
         var1.addStat(StatList.objectUseStats[Item.getIdFromItem(this.field_151002_e)], 1);
      }

      return var10;
   }

   public float func_150997_a(Block var1) {
      return this.getItem().func_150893_a(this, var1);
   }

   public ItemStack useItemRightClick(World var1, EntityPlayer var2) {
      return this.getItem().onItemRightClick(this, var1, var2);
   }

   public ItemStack onFoodEaten(World var1, EntityPlayer var2) {
      return this.getItem().onEaten(this, var1, var2);
   }

   public NBTTagCompound writeToNBT(NBTTagCompound var1) {
      var1.setShort("id", (short)Item.getIdFromItem(this.field_151002_e));
      var1.setByte("Count", (byte)this.stackSize);
      var1.setShort("Damage", (short)this.itemDamage);
      if(this.stackTagCompound != null) {
         var1.setTag("tag", this.stackTagCompound);
      }

      return var1;
   }

   public void readFromNBT(NBTTagCompound var1) {
      this.field_151002_e = Item.getItemById(var1.getShort("id"));
      this.stackSize = var1.getByte("Count");
      this.itemDamage = var1.getShort("Damage");
      if(this.itemDamage < 0) {
         this.itemDamage = 0;
      }

      if(var1.hasKey("tag", 10)) {
         this.stackTagCompound = var1.getCompoundTag("tag");
      }

   }

   public int getMaxStackSize() {
      return this.getItem().getItemStackLimit();
   }

   public boolean isStackable() {
      return this.getMaxStackSize() > 1 && (!this.isItemStackDamageable() || !this.isItemDamaged());
   }

   public boolean isItemStackDamageable() {
      return this.field_151002_e.getMaxDamage() <= 0?false:!this.hasTagCompound() || !this.getTagCompound().getBoolean("Unbreakable");
   }

   public boolean getHasSubtypes() {
      return this.field_151002_e.getHasSubtypes();
   }

   public boolean isItemDamaged() {
      return this.isItemStackDamageable() && this.itemDamage > 0;
   }

   public int getItemDamageForDisplay() {
      return this.itemDamage;
   }

   public int getItemDamage() {
      return this.itemDamage;
   }

   public void setItemDamage(int var1) {
      this.itemDamage = var1;
      if(this.itemDamage < 0) {
         this.itemDamage = 0;
      }

   }

   public int getMaxDamage() {
      return this.field_151002_e.getMaxDamage();
   }

   public boolean attemptDamageItem(int var1, Random var2) {
      if(!this.isItemStackDamageable()) {
         return false;
      } else {
         if(var1 > 0) {
            int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, this);
            int var4 = 0;

            for(int var5 = 0; var3 > 0 && var5 < var1; ++var5) {
               if(EnchantmentDurability.negateDamage(this, var3, var2)) {
                  ++var4;
               }
            }

            var1 -= var4;
            if(var1 <= 0) {
               return false;
            }
         }

         this.itemDamage += var1;
         return this.itemDamage > this.getMaxDamage();
      }
   }

   public void damageItem(int var1, EntityLivingBase var2) {
      if(!(var2 instanceof EntityPlayer) || !((EntityPlayer)var2).capabilities.isCreativeMode) {
         if(this.isItemStackDamageable()) {
            if(this.attemptDamageItem(var1, var2.getRNG())) {
               var2.renderBrokenItemStack(this);
               --this.stackSize;
               if(var2 instanceof EntityPlayer) {
                  EntityPlayer var3 = (EntityPlayer)var2;
                  var3.addStat(StatList.objectBreakStats[Item.getIdFromItem(this.field_151002_e)], 1);
                  if(this.stackSize == 0 && this.getItem() instanceof ItemBow) {
                     var3.destroyCurrentEquippedItem();
                  }
               }

               if(this.stackSize < 0) {
                  this.stackSize = 0;
               }

               this.itemDamage = 0;
            }

         }
      }
   }

   public void hitEntity(EntityLivingBase var1, EntityPlayer var2) {
      boolean var3 = this.field_151002_e.hitEntity(this, var1, var2);
      if(var3) {
         var2.addStat(StatList.objectUseStats[Item.getIdFromItem(this.field_151002_e)], 1);
      }

   }

   public void func_150999_a(World var1, Block var2, int var3, int var4, int var5, EntityPlayer var6) {
      boolean var7 = this.field_151002_e.onBlockDestroyed(this, var1, var2, var3, var4, var5, var6);
      if(var7) {
         var6.addStat(StatList.objectUseStats[Item.getIdFromItem(this.field_151002_e)], 1);
      }

   }

   public boolean func_150998_b(Block var1) {
      return this.field_151002_e.func_150897_b(var1);
   }

   public boolean interactWithEntity(EntityPlayer var1, EntityLivingBase var2) {
      return this.field_151002_e.itemInteractionForEntity(this, var1, var2);
   }

   public ItemStack copy() {
      ItemStack var1 = new ItemStack(this.field_151002_e, this.stackSize, this.itemDamage);
      if(this.stackTagCompound != null) {
         var1.stackTagCompound = (NBTTagCompound)this.stackTagCompound.copy();
      }

      return var1;
   }

   public static boolean areItemStackTagsEqual(ItemStack var0, ItemStack var1) {
      return var0 == null && var1 == null?true:(var0 != null && var1 != null?(var0.stackTagCompound == null && var1.stackTagCompound != null?false:var0.stackTagCompound == null || var0.stackTagCompound.equals(var1.stackTagCompound)):false);
   }

   public static boolean areItemStacksEqual(ItemStack var0, ItemStack var1) {
      return var0 == null && var1 == null?true:(var0 != null && var1 != null?var0.isItemStackEqual(var1):false);
   }

   private boolean isItemStackEqual(ItemStack var1) {
      return this.stackSize != var1.stackSize?false:(this.field_151002_e != var1.field_151002_e?false:(this.itemDamage != var1.itemDamage?false:(this.stackTagCompound == null && var1.stackTagCompound != null?false:this.stackTagCompound == null || this.stackTagCompound.equals(var1.stackTagCompound))));
   }

   public boolean isItemEqual(ItemStack var1) {
      return this.field_151002_e == var1.field_151002_e && this.itemDamage == var1.itemDamage;
   }

   public String getUnlocalizedName() {
      return this.field_151002_e.getUnlocalizedName(this);
   }

   public static ItemStack copyItemStack(ItemStack var0) {
      return var0 == null?null:var0.copy();
   }

   public String toString() {
      return this.stackSize + "x" + this.field_151002_e.getUnlocalizedName() + "@" + this.itemDamage;
   }

   public void updateAnimation(World var1, Entity var2, int var3, boolean var4) {
      if(this.animationsToGo > 0) {
         --this.animationsToGo;
      }

      this.field_151002_e.onUpdate(this, var1, var2, var3, var4);
   }

   public void onCrafting(World var1, EntityPlayer var2, int var3) {
      var2.addStat(StatList.objectCraftStats[Item.getIdFromItem(this.field_151002_e)], var3);
      this.field_151002_e.onCreated(this, var1, var2);
   }

   public int getMaxItemUseDuration() {
      return this.getItem().getMaxItemUseDuration(this);
   }

   public EnumAction getItemUseAction() {
      return this.getItem().getItemUseAction(this);
   }

   public void onPlayerStoppedUsing(World var1, EntityPlayer var2, int var3) {
      this.getItem().onPlayerStoppedUsing(this, var1, var2, var3);
   }

   public boolean hasTagCompound() {
      return this.stackTagCompound != null;
   }

   public NBTTagCompound getTagCompound() {
      return this.stackTagCompound;
   }

   public NBTTagList getEnchantmentTagList() {
      return this.stackTagCompound == null?null:this.stackTagCompound.getTagList("ench", 10);
   }

   public void setTagCompound(NBTTagCompound var1) {
      this.stackTagCompound = var1;
   }

   public String getDisplayName() {
      String var1 = this.getItem().getItemStackDisplayName(this);
      if(this.stackTagCompound != null && this.stackTagCompound.hasKey("display", 10)) {
         NBTTagCompound var2 = this.stackTagCompound.getCompoundTag("display");
         if(var2.hasKey("Name", 8)) {
            var1 = var2.getString("Name");
         }
      }

      return var1;
   }

   public ItemStack setStackDisplayName(String var1) {
      if(this.stackTagCompound == null) {
         this.stackTagCompound = new NBTTagCompound();
      }

      if(!this.stackTagCompound.hasKey("display", 10)) {
         this.stackTagCompound.setTag("display", new NBTTagCompound());
      }

      this.stackTagCompound.getCompoundTag("display").setString("Name", var1);
      return this;
   }

   public void func_135074_t() {
      if(this.stackTagCompound != null) {
         if(this.stackTagCompound.hasKey("display", 10)) {
            NBTTagCompound var1 = this.stackTagCompound.getCompoundTag("display");
            var1.removeTag("Name");
            if(var1.hasNoTags()) {
               this.stackTagCompound.removeTag("display");
               if(this.stackTagCompound.hasNoTags()) {
                  this.setTagCompound((NBTTagCompound)null);
               }
            }

         }
      }
   }

   public boolean hasDisplayName() {
      return this.stackTagCompound == null?false:(!this.stackTagCompound.hasKey("display", 10)?false:this.stackTagCompound.getCompoundTag("display").hasKey("Name", 8));
   }

   public List getTooltip(EntityPlayer var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      String var4 = this.getDisplayName();
      if(this.hasDisplayName()) {
         var4 = EnumChatFormatting.ITALIC + var4 + EnumChatFormatting.RESET;
      }

      int var6;
      if(var2) {
         String var5 = "";
         if(var4.length() > 0) {
            var4 = var4 + " (";
            var5 = ")";
         }

         var6 = Item.getIdFromItem(this.field_151002_e);
         if(this.getHasSubtypes()) {
            var4 = var4 + String.format("#%04d/%d%s", new Object[]{Integer.valueOf(var6), Integer.valueOf(this.itemDamage), var5});
         } else {
            var4 = var4 + String.format("#%04d%s", new Object[]{Integer.valueOf(var6), var5});
         }
      } else if(!this.hasDisplayName() && this.field_151002_e == Items.filled_map) {
         var4 = var4 + " #" + this.itemDamage;
      }

      var3.add(var4);
      this.field_151002_e.addInformation(this, var1, var3, var2);
      if(this.hasTagCompound()) {
         NBTTagList var13 = this.getEnchantmentTagList();
         if(var13 != null) {
            for(var6 = 0; var6 < var13.tagCount(); ++var6) {
               short var7 = var13.getCompoundTagAt(var6).getShort("id");
               short var8 = var13.getCompoundTagAt(var6).getShort("lvl");
               if(Enchantment.enchantmentsList[var7] != null) {
                  var3.add(Enchantment.enchantmentsList[var7].getTranslatedName(var8));
               }
            }
         }

         if(this.stackTagCompound.hasKey("display", 10)) {
            NBTTagCompound var15 = this.stackTagCompound.getCompoundTag("display");
            if(var15.hasKey("color", 3)) {
               if(var2) {
                  var3.add("Color: #" + Integer.toHexString(var15.getInteger("color")).toUpperCase());
               } else {
                  var3.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("item.dyed"));
               }
            }

            if(var15.func_150299_b("Lore") == 9) {
               NBTTagList var17 = var15.getTagList("Lore", 8);
               if(var17.tagCount() > 0) {
                  for(int var19 = 0; var19 < var17.tagCount(); ++var19) {
                     var3.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + var17.getStringTagAt(var19));
                  }
               }
            }
         }
      }

      Multimap var14 = this.getAttributeModifiers();
      if(!var14.isEmpty()) {
         var3.add("");
         Iterator var16 = var14.entries().iterator();

         while(var16.hasNext()) {
            Entry var18 = (Entry)var16.next();
            AttributeModifier var20 = (AttributeModifier)var18.getValue();
            double var9 = var20.getAmount();
            if(var20.getID() == Item.field_111210_e) {
               var9 += (double)EnchantmentHelper.func_152377_a(this, EnumCreatureAttribute.UNDEFINED);
            }

            double var11;
            if(var20.getOperation() != 1 && var20.getOperation() != 2) {
               var11 = var9;
            } else {
               var11 = var9 * 100.0D;
            }

            if(var9 > 0.0D) {
               var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + var20.getOperation(), new Object[]{field_111284_a.format(var11), StatCollector.translateToLocal("attribute.name." + (String)var18.getKey())}));
            } else if(var9 < 0.0D) {
               var11 *= -1.0D;
               var3.add(EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + var20.getOperation(), new Object[]{field_111284_a.format(var11), StatCollector.translateToLocal("attribute.name." + (String)var18.getKey())}));
            }
         }
      }

      if(this.hasTagCompound() && this.getTagCompound().getBoolean("Unbreakable")) {
         var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.unbreakable"));
      }

      if(var2 && this.isItemDamaged()) {
         var3.add("Durability: " + (this.getMaxDamage() - this.getItemDamageForDisplay()) + " / " + this.getMaxDamage());
      }

      return var3;
   }

   public boolean hasEffect() {
      return this.getItem().hasEffect(this);
   }

   public EnumRarity getRarity() {
      return this.getItem().getRarity(this);
   }

   public boolean isItemEnchantable() {
      return !this.getItem().isItemTool(this)?false:!this.isItemEnchanted();
   }

   public void addEnchantment(Enchantment var1, int var2) {
      if(this.stackTagCompound == null) {
         this.setTagCompound(new NBTTagCompound());
      }

      if(!this.stackTagCompound.hasKey("ench", 9)) {
         this.stackTagCompound.setTag("ench", new NBTTagList());
      }

      NBTTagList var3 = this.stackTagCompound.getTagList("ench", 10);
      NBTTagCompound var4 = new NBTTagCompound();
      var4.setShort("id", (short)var1.effectId);
      var4.setShort("lvl", (short)((byte)var2));
      var3.appendTag(var4);
   }

   public boolean isItemEnchanted() {
      return this.stackTagCompound != null && this.stackTagCompound.hasKey("ench", 9);
   }

   public void setTagInfo(String var1, NBTBase var2) {
      if(this.stackTagCompound == null) {
         this.setTagCompound(new NBTTagCompound());
      }

      this.stackTagCompound.setTag(var1, var2);
   }

   public boolean canEditBlocks() {
      return this.getItem().canItemEditBlocks();
   }

   public boolean isOnItemFrame() {
      return this.itemFrame != null;
   }

   public void setItemFrame(EntityItemFrame var1) {
      this.itemFrame = var1;
   }

   public EntityItemFrame getItemFrame() {
      return this.itemFrame;
   }

   public int getRepairCost() {
      return this.hasTagCompound() && this.stackTagCompound.hasKey("RepairCost", 3)?this.stackTagCompound.getInteger("RepairCost"):0;
   }

   public void setRepairCost(int var1) {
      if(!this.hasTagCompound()) {
         this.stackTagCompound = new NBTTagCompound();
      }

      this.stackTagCompound.setInteger("RepairCost", var1);
   }

   public Multimap getAttributeModifiers() {
      Object var1;
      if(this.hasTagCompound() && this.stackTagCompound.hasKey("AttributeModifiers", 9)) {
         var1 = HashMultimap.create();
         NBTTagList var2 = this.stackTagCompound.getTagList("AttributeModifiers", 10);

         for(int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            AttributeModifier var5 = SharedMonsterAttributes.readAttributeModifierFromNBT(var4);
            if(var5.getID().getLeastSignificantBits() != 0L && var5.getID().getMostSignificantBits() != 0L) {
               ((Multimap)var1).put(var4.getString("AttributeName"), var5);
            }
         }
      } else {
         var1 = this.getItem().getItemAttributeModifiers();
      }

      return (Multimap)var1;
   }

   public void func_150996_a(Item var1) {
      this.field_151002_e = var1;
   }

   public IChatComponent func_151000_E() {
      IChatComponent var1 = (new ChatComponentText("[")).appendText(this.getDisplayName()).appendText("]");
      if(this.field_151002_e != null) {
         NBTTagCompound var2 = new NBTTagCompound();
         this.writeToNBT(var2);
         var1.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent$Action.SHOW_ITEM, new ChatComponentText(var2.toString())));
         var1.getChatStyle().setColor(this.getRarity().rarityColor);
      }

      return var1;
   }

}
