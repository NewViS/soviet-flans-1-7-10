package net.minecraft.item;

import com.google.common.collect.Multimap;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.Item$ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTool extends Item {

   private Set field_150914_c;
   protected float efficiencyOnProperMaterial = 4.0F;
   private float damageVsEntity;
   protected Item$ToolMaterial toolMaterial;


   protected ItemTool(float var1, Item$ToolMaterial var2, Set var3) {
      this.toolMaterial = var2;
      this.field_150914_c = var3;
      super.maxStackSize = 1;
      this.setMaxDamage(var2.getMaxUses());
      this.efficiencyOnProperMaterial = var2.getEfficiencyOnProperMaterial();
      this.damageVsEntity = var1 + var2.getDamageVsEntity();
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   public float func_150893_a(ItemStack var1, Block var2) {
      return this.field_150914_c.contains(var2)?this.efficiencyOnProperMaterial:1.0F;
   }

   public boolean hitEntity(ItemStack var1, EntityLivingBase var2, EntityLivingBase var3) {
      var1.damageItem(2, var3);
      return true;
   }

   public boolean onBlockDestroyed(ItemStack var1, World var2, Block var3, int var4, int var5, int var6, EntityLivingBase var7) {
      if((double)var3.getBlockHardness(var2, var4, var5, var6) != 0.0D) {
         var1.damageItem(1, var7);
      }

      return true;
   }

   public boolean isFull3D() {
      return true;
   }

   public Item$ToolMaterial func_150913_i() {
      return this.toolMaterial;
   }

   public int getItemEnchantability() {
      return this.toolMaterial.getEnchantability();
   }

   public String getToolMaterialName() {
      return this.toolMaterial.toString();
   }

   public boolean getIsRepairable(ItemStack var1, ItemStack var2) {
      return this.toolMaterial.func_150995_f() == var2.getItem()?true:super.getIsRepairable(var1, var2);
   }

   public Multimap getItemAttributeModifiers() {
      Multimap var1 = super.getItemAttributeModifiers();
      var1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(Item.field_111210_e, "Tool modifier", (double)this.damageVsEntity, 0));
      return var1;
   }
}
