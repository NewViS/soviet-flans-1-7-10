package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class ModifiableAttributeInstance implements IAttributeInstance {

   private final BaseAttributeMap attributeMap;
   private final IAttribute genericAttribute;
   private final Map mapByOperation = Maps.newHashMap();
   private final Map mapByName = Maps.newHashMap();
   private final Map mapByUUID = Maps.newHashMap();
   private double baseValue;
   private boolean needsUpdate = true;
   private double cachedValue;


   public ModifiableAttributeInstance(BaseAttributeMap var1, IAttribute var2) {
      this.attributeMap = var1;
      this.genericAttribute = var2;
      this.baseValue = var2.getDefaultValue();

      for(int var3 = 0; var3 < 3; ++var3) {
         this.mapByOperation.put(Integer.valueOf(var3), new HashSet());
      }

   }

   public IAttribute getAttribute() {
      return this.genericAttribute;
   }

   public double getBaseValue() {
      return this.baseValue;
   }

   public void setBaseValue(double var1) {
      if(var1 != this.getBaseValue()) {
         this.baseValue = var1;
         this.flagForUpdate();
      }
   }

   public Collection getModifiersByOperation(int var1) {
      return (Collection)this.mapByOperation.get(Integer.valueOf(var1));
   }

   public Collection func_111122_c() {
      HashSet var1 = new HashSet();

      for(int var2 = 0; var2 < 3; ++var2) {
         var1.addAll(this.getModifiersByOperation(var2));
      }

      return var1;
   }

   public AttributeModifier getModifier(UUID var1) {
      return (AttributeModifier)this.mapByUUID.get(var1);
   }

   public void applyModifier(AttributeModifier var1) {
      if(this.getModifier(var1.getID()) != null) {
         throw new IllegalArgumentException("Modifier is already applied on this attribute!");
      } else {
         Object var2 = (Set)this.mapByName.get(var1.getName());
         if(var2 == null) {
            var2 = new HashSet();
            this.mapByName.put(var1.getName(), var2);
         }

         ((Set)this.mapByOperation.get(Integer.valueOf(var1.getOperation()))).add(var1);
         ((Set)var2).add(var1);
         this.mapByUUID.put(var1.getID(), var1);
         this.flagForUpdate();
      }
   }

   private void flagForUpdate() {
      this.needsUpdate = true;
      this.attributeMap.addAttributeInstance(this);
   }

   public void removeModifier(AttributeModifier var1) {
      for(int var2 = 0; var2 < 3; ++var2) {
         Set var3 = (Set)this.mapByOperation.get(Integer.valueOf(var2));
         var3.remove(var1);
      }

      Set var4 = (Set)this.mapByName.get(var1.getName());
      if(var4 != null) {
         var4.remove(var1);
         if(var4.isEmpty()) {
            this.mapByName.remove(var1.getName());
         }
      }

      this.mapByUUID.remove(var1.getID());
      this.flagForUpdate();
   }

   public void removeAllModifiers() {
      Collection var1 = this.func_111122_c();
      if(var1 != null) {
         ArrayList var4 = new ArrayList(var1);
         Iterator var2 = var4.iterator();

         while(var2.hasNext()) {
            AttributeModifier var3 = (AttributeModifier)var2.next();
            this.removeModifier(var3);
         }

      }
   }

   public double getAttributeValue() {
      if(this.needsUpdate) {
         this.cachedValue = this.computeValue();
         this.needsUpdate = false;
      }

      return this.cachedValue;
   }

   private double computeValue() {
      double var1 = this.getBaseValue();

      AttributeModifier var4;
      for(Iterator var3 = this.getModifiersByOperation(0).iterator(); var3.hasNext(); var1 += var4.getAmount()) {
         var4 = (AttributeModifier)var3.next();
      }

      double var7 = var1;

      Iterator var5;
      AttributeModifier var6;
      for(var5 = this.getModifiersByOperation(1).iterator(); var5.hasNext(); var7 += var1 * var6.getAmount()) {
         var6 = (AttributeModifier)var5.next();
      }

      for(var5 = this.getModifiersByOperation(2).iterator(); var5.hasNext(); var7 *= 1.0D + var6.getAmount()) {
         var6 = (AttributeModifier)var5.next();
      }

      return this.genericAttribute.clampValue(var7);
   }
}
