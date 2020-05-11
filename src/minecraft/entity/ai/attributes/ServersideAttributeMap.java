package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.server.management.LowerStringMap;

public class ServersideAttributeMap extends BaseAttributeMap {

   private final Set attributeInstanceSet = Sets.newHashSet();
   protected final Map descriptionToAttributeInstanceMap = new LowerStringMap();


   public ModifiableAttributeInstance getAttributeInstance(IAttribute var1) {
      return (ModifiableAttributeInstance)super.getAttributeInstance(var1);
   }

   public ModifiableAttributeInstance getAttributeInstanceByName(String var1) {
      IAttributeInstance var2 = super.getAttributeInstanceByName(var1);
      if(var2 == null) {
         var2 = (IAttributeInstance)this.descriptionToAttributeInstanceMap.get(var1);
      }

      return (ModifiableAttributeInstance)var2;
   }

   public IAttributeInstance registerAttribute(IAttribute var1) {
      if(super.attributesByName.containsKey(var1.getAttributeUnlocalizedName())) {
         throw new IllegalArgumentException("Attribute is already registered!");
      } else {
         ModifiableAttributeInstance var2 = new ModifiableAttributeInstance(this, var1);
         super.attributesByName.put(var1.getAttributeUnlocalizedName(), var2);
         if(var1 instanceof RangedAttribute && ((RangedAttribute)var1).getDescription() != null) {
            this.descriptionToAttributeInstanceMap.put(((RangedAttribute)var1).getDescription(), var2);
         }

         super.attributes.put(var1, var2);
         return var2;
      }
   }

   public void addAttributeInstance(ModifiableAttributeInstance var1) {
      if(var1.getAttribute().getShouldWatch()) {
         this.attributeInstanceSet.add(var1);
      }

   }

   public Set getAttributeInstanceSet() {
      return this.attributeInstanceSet;
   }

   public Collection getWatchedAttributes() {
      HashSet var1 = Sets.newHashSet();
      Iterator var2 = this.getAllAttributes().iterator();

      while(var2.hasNext()) {
         IAttributeInstance var3 = (IAttributeInstance)var2.next();
         if(var3.getAttribute().getShouldWatch()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   // $FF: synthetic method
   public IAttributeInstance getAttributeInstanceByName(String var1) {
      return this.getAttributeInstanceByName(var1);
   }

   // $FF: synthetic method
   public IAttributeInstance getAttributeInstance(IAttribute var1) {
      return this.getAttributeInstance(var1);
   }
}
