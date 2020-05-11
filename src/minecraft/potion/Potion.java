package net.minecraft.potion;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionAbsoption;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.potion.PotionHealthBoost;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StringUtils;

public class Potion {

   public static final Potion[] potionTypes = new Potion[32];
   public static final Potion field_76423_b = null;
   public static final Potion moveSpeed = (new Potion(1, false, 8171462)).setPotionName("potion.moveSpeed").setIconIndex(0, 0).func_111184_a(SharedMonsterAttributes.movementSpeed, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
   public static final Potion moveSlowdown = (new Potion(2, true, 5926017)).setPotionName("potion.moveSlowdown").setIconIndex(1, 0).func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
   public static final Potion digSpeed = (new Potion(3, false, 14270531)).setPotionName("potion.digSpeed").setIconIndex(2, 0).setEffectiveness(1.5D);
   public static final Potion digSlowdown = (new Potion(4, true, 4866583)).setPotionName("potion.digSlowDown").setIconIndex(3, 0);
   public static final Potion damageBoost = (new PotionAttackDamage(5, false, 9643043)).setPotionName("potion.damageBoost").setIconIndex(4, 0).func_111184_a(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 3.0D, 2);
   public static final Potion heal = (new PotionHealth(6, false, 16262179)).setPotionName("potion.heal");
   public static final Potion harm = (new PotionHealth(7, true, 4393481)).setPotionName("potion.harm");
   public static final Potion jump = (new Potion(8, false, 7889559)).setPotionName("potion.jump").setIconIndex(2, 1);
   public static final Potion confusion = (new Potion(9, true, 5578058)).setPotionName("potion.confusion").setIconIndex(3, 1).setEffectiveness(0.25D);
   public static final Potion regeneration = (new Potion(10, false, 13458603)).setPotionName("potion.regeneration").setIconIndex(7, 0).setEffectiveness(0.25D);
   public static final Potion resistance = (new Potion(11, false, 10044730)).setPotionName("potion.resistance").setIconIndex(6, 1);
   public static final Potion fireResistance = (new Potion(12, false, 14981690)).setPotionName("potion.fireResistance").setIconIndex(7, 1);
   public static final Potion waterBreathing = (new Potion(13, false, 3035801)).setPotionName("potion.waterBreathing").setIconIndex(0, 2);
   public static final Potion invisibility = (new Potion(14, false, 8356754)).setPotionName("potion.invisibility").setIconIndex(0, 1);
   public static final Potion blindness = (new Potion(15, true, 2039587)).setPotionName("potion.blindness").setIconIndex(5, 1).setEffectiveness(0.25D);
   public static final Potion nightVision = (new Potion(16, false, 2039713)).setPotionName("potion.nightVision").setIconIndex(4, 1);
   public static final Potion hunger = (new Potion(17, true, 5797459)).setPotionName("potion.hunger").setIconIndex(1, 1);
   public static final Potion weakness = (new PotionAttackDamage(18, true, 4738376)).setPotionName("potion.weakness").setIconIndex(5, 0).func_111184_a(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
   public static final Potion poison = (new Potion(19, true, 5149489)).setPotionName("potion.poison").setIconIndex(6, 0).setEffectiveness(0.25D);
   public static final Potion wither = (new Potion(20, true, 3484199)).setPotionName("potion.wither").setIconIndex(1, 2).setEffectiveness(0.25D);
   public static final Potion field_76434_w = (new PotionHealthBoost(21, false, 16284963)).setPotionName("potion.healthBoost").setIconIndex(2, 2).func_111184_a(SharedMonsterAttributes.maxHealth, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0);
   public static final Potion field_76444_x = (new PotionAbsoption(22, false, 2445989)).setPotionName("potion.absorption").setIconIndex(2, 2);
   public static final Potion field_76443_y = (new PotionHealth(23, false, 16262179)).setPotionName("potion.saturation");
   public static final Potion field_76442_z = null;
   public static final Potion field_76409_A = null;
   public static final Potion field_76410_B = null;
   public static final Potion field_76411_C = null;
   public static final Potion field_76405_D = null;
   public static final Potion field_76406_E = null;
   public static final Potion field_76407_F = null;
   public static final Potion field_76408_G = null;
   public final int id;
   private final Map field_111188_I = Maps.newHashMap();
   private final boolean isBadEffect;
   private final int liquidColor;
   private String name = "";
   private int statusIconIndex = -1;
   private double effectiveness;
   private boolean usable;


   protected Potion(int var1, boolean var2, int var3) {
      this.id = var1;
      potionTypes[var1] = this;
      this.isBadEffect = var2;
      if(var2) {
         this.effectiveness = 0.5D;
      } else {
         this.effectiveness = 1.0D;
      }

      this.liquidColor = var3;
   }

   protected Potion setIconIndex(int var1, int var2) {
      this.statusIconIndex = var1 + var2 * 8;
      return this;
   }

   public int getId() {
      return this.id;
   }

   public void performEffect(EntityLivingBase var1, int var2) {
      if(this.id == regeneration.id) {
         if(var1.getHealth() < var1.getMaxHealth()) {
            var1.heal(1.0F);
         }
      } else if(this.id == poison.id) {
         if(var1.getHealth() > 1.0F) {
            var1.attackEntityFrom(DamageSource.magic, 1.0F);
         }
      } else if(this.id == wither.id) {
         var1.attackEntityFrom(DamageSource.wither, 1.0F);
      } else if(this.id == hunger.id && var1 instanceof EntityPlayer) {
         ((EntityPlayer)var1).addExhaustion(0.025F * (float)(var2 + 1));
      } else if(this.id == field_76443_y.id && var1 instanceof EntityPlayer) {
         if(!var1.worldObj.isRemote) {
            ((EntityPlayer)var1).getFoodStats().addStats(var2 + 1, 1.0F);
         }
      } else if((this.id != heal.id || var1.isEntityUndead()) && (this.id != harm.id || !var1.isEntityUndead())) {
         if(this.id == harm.id && !var1.isEntityUndead() || this.id == heal.id && var1.isEntityUndead()) {
            var1.attackEntityFrom(DamageSource.magic, (float)(6 << var2));
         }
      } else {
         var1.heal((float)Math.max(4 << var2, 0));
      }

   }

   public void affectEntity(EntityLivingBase var1, EntityLivingBase var2, int var3, double var4) {
      int var6;
      if((this.id != heal.id || var2.isEntityUndead()) && (this.id != harm.id || !var2.isEntityUndead())) {
         if(this.id == harm.id && !var2.isEntityUndead() || this.id == heal.id && var2.isEntityUndead()) {
            var6 = (int)(var4 * (double)(6 << var3) + 0.5D);
            if(var1 == null) {
               var2.attackEntityFrom(DamageSource.magic, (float)var6);
            } else {
               var2.attackEntityFrom(DamageSource.causeIndirectMagicDamage(var2, var1), (float)var6);
            }
         }
      } else {
         var6 = (int)(var4 * (double)(4 << var3) + 0.5D);
         var2.heal((float)var6);
      }

   }

   public boolean isInstant() {
      return false;
   }

   public boolean isReady(int var1, int var2) {
      int var3;
      if(this.id == regeneration.id) {
         var3 = 50 >> var2;
         return var3 > 0?var1 % var3 == 0:true;
      } else if(this.id == poison.id) {
         var3 = 25 >> var2;
         return var3 > 0?var1 % var3 == 0:true;
      } else if(this.id == wither.id) {
         var3 = 40 >> var2;
         return var3 > 0?var1 % var3 == 0:true;
      } else {
         return this.id == hunger.id;
      }
   }

   public Potion setPotionName(String var1) {
      this.name = var1;
      return this;
   }

   public String getName() {
      return this.name;
   }

   public boolean hasStatusIcon() {
      return this.statusIconIndex >= 0;
   }

   public int getStatusIconIndex() {
      return this.statusIconIndex;
   }

   public boolean isBadEffect() {
      return this.isBadEffect;
   }

   public static String getDurationString(PotionEffect var0) {
      if(var0.getIsPotionDurationMax()) {
         return "**:**";
      } else {
         int var1 = var0.getDuration();
         return StringUtils.ticksToElapsedTime(var1);
      }
   }

   protected Potion setEffectiveness(double var1) {
      this.effectiveness = var1;
      return this;
   }

   public double getEffectiveness() {
      return this.effectiveness;
   }

   public boolean isUsable() {
      return this.usable;
   }

   public int getLiquidColor() {
      return this.liquidColor;
   }

   public Potion func_111184_a(IAttribute var1, String var2, double var3, int var5) {
      AttributeModifier var6 = new AttributeModifier(UUID.fromString(var2), this.getName(), var3, var5);
      this.field_111188_I.put(var1, var6);
      return this;
   }

   public Map func_111186_k() {
      return this.field_111188_I;
   }

   public void removeAttributesModifiersFromEntity(EntityLivingBase var1, BaseAttributeMap var2, int var3) {
      Iterator var4 = this.field_111188_I.entrySet().iterator();

      while(var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         IAttributeInstance var6 = var2.getAttributeInstance((IAttribute)var5.getKey());
         if(var6 != null) {
            var6.removeModifier((AttributeModifier)var5.getValue());
         }
      }

   }

   public void applyAttributesModifiersToEntity(EntityLivingBase var1, BaseAttributeMap var2, int var3) {
      Iterator var4 = this.field_111188_I.entrySet().iterator();

      while(var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         IAttributeInstance var6 = var2.getAttributeInstance((IAttribute)var5.getKey());
         if(var6 != null) {
            AttributeModifier var7 = (AttributeModifier)var5.getValue();
            var6.removeModifier(var7);
            var6.applyModifier(new AttributeModifier(var7.getID(), this.getName() + " " + var3, this.func_111183_a(var3, var7), var7.getOperation()));
         }
      }

   }

   public double func_111183_a(int var1, AttributeModifier var2) {
      return var2.getAmount() * (double)(var1 + 1);
   }

}
