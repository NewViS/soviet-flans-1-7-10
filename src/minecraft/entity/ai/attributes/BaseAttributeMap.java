package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.server.management.LowerStringMap;

public abstract class BaseAttributeMap {

   protected final Map attributes = new HashMap();
   protected final Map attributesByName = new LowerStringMap();


   public IAttributeInstance getAttributeInstance(IAttribute var1) {
      return (IAttributeInstance)this.attributes.get(var1);
   }

   public IAttributeInstance getAttributeInstanceByName(String var1) {
      return (IAttributeInstance)this.attributesByName.get(var1);
   }

   public abstract IAttributeInstance registerAttribute(IAttribute var1);

   public Collection getAllAttributes() {
      return this.attributesByName.values();
   }

   public void addAttributeInstance(ModifiableAttributeInstance var1) {}

   public void removeAttributeModifiers(Multimap var1) {
      Iterator var2 = var1.entries().iterator();

      while(var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         IAttributeInstance var4 = this.getAttributeInstanceByName((String)var3.getKey());
         if(var4 != null) {
            var4.removeModifier((AttributeModifier)var3.getValue());
         }
      }

   }

   public void applyAttributeModifiers(Multimap var1) {
      Iterator var2 = var1.entries().iterator();

      while(var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         IAttributeInstance var4 = this.getAttributeInstanceByName((String)var3.getKey());
         if(var4 != null) {
            var4.removeModifier((AttributeModifier)var3.getValue());
            var4.applyModifier((AttributeModifier)var3.getValue());
         }
      }

   }
}
